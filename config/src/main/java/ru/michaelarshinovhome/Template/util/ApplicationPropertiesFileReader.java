package ru.michaelarshinovhome.Template.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:application.properties")
public class ApplicationPropertiesFileReader {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationPropertiesFileReader.class);
	//static Scanner sc = new Scanner(new File("application.properties"));
	private File file;
	private String filePath;
	private String moduleName;
	private List<String> properties = new ArrayList<>();
	
	public ApplicationPropertiesFileReader() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
		Scanner sc = new Scanner(is);
		do {
			String key = sc.nextLine();
			if (key.startsWith("properties.file.path")) {
				filePath = key.split("=")[1];
			}
			if (key.startsWith("module.name")) {
				moduleName = key.split("=")[1];
			}
		} while(sc.hasNext());
		sc.close();
		file = new File(filePath);
		is = getClass().getClassLoader().getResourceAsStream("data.properties");
		sc = new Scanner(is);
		do {
			properties.add(sc.nextLine());
		} while(sc.hasNext());
		sc.close();
	}

	public boolean checkIfExists() {
		logger.info("Осуществляется проверка, существует ли файл");
		if (file.exists()) {
			logger.info("Файл найден");
			return true;
		} else {
			logger.info("Файл не найден");
			return false;
		}
	}
	public String getModuleName() {
		return moduleName;
	}
	
	public List<String> getProperties() {
		return properties;
	}

	public void update(Map<String, String> keyValue) {
		List<String> properties = new ArrayList<>();
		try (InputStream is = new FileInputStream(file); Scanner sc = new Scanner(is)) {
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String key = line.split("=")[0].trim();
				if (keyValue.containsKey(key)) {
					line = key + "=" + keyValue.get(key);
				}
				properties.add(line);
			}
			//sc.close();
			//is.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		try {
			new FileOutputStream(filePath).close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		for (String p: properties) {
			System.out.println(p);
		}
		
		try(FileWriter writer = new FileWriter(filePath, false)) {
			for (String p: properties) {
	            writer.write(p);
	            writer.append('\n');
			}
            writer.flush();
        } catch(IOException ex){
            logger.error(ex.getMessage());
        }
		logger.info("Файл application.properties обновлен.");
	}

}
