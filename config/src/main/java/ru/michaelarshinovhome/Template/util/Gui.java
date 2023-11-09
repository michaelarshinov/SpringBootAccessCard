package ru.michaelarshinovhome.Template.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.michaelarshinovhome.Template.TemplateModuleConfiguratorApplication;

@Component
public class Gui implements ActionListener {
	
	private static final Logger logger = LoggerFactory.getLogger(Gui.class);
	
    @Autowired
    ApplicationPropertiesFileReader reader;
    
    JButton btn1, btn2;
    JLabel l;
    List<JLabel> labels = new ArrayList<>();
    List<JTextField> textFields = new ArrayList<>();
    final int height = 30;
    final String delimeter = "==";
	public void run() {
		//labels=List.copyOf("123","345","3425");
		
		if (reader.checkIfExists()) {
			JFrame frame = new JFrame("Конфигуратор модуля " + reader.getModuleName());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800,700); 
	        frame.setLayout(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setTitle("Конфигуратор модуля " + reader.getModuleName());  
	        l = new JLabel("Введите данные конфигуратора модуля ");  
	        l.setForeground(Color.blue);  
	        l.setFont(new Font("Serif", Font.BOLD, 20));  
	        l.setBounds(200, 30, 400, 20);
	        frame.add(l);
	        int h=0;
	        for (String keyValLabel: reader.getProperties()) {
	        	JLabel label = new JLabel(keyValLabel.split(delimeter)[2].trim());
	        	label.setBounds(80, 60 + height*(h), 200, 20);
	        	labels.add(label);
	        	frame.add(label);
	        	
	        	JTextField textField = new JTextField(keyValLabel.split(delimeter)[1].trim());
	        	textField.setName(keyValLabel.split(delimeter)[0].trim());
    			textField.setBounds(450, 60 + height*(h++), 300, 20);
	        	textFields.add(textField);
	        	frame.add(textField);
	        }
	        
	        btn1 = new JButton("Submit");  
	        btn2 = new JButton("Сбросить по умолчанию");  
	        btn1.addActionListener(this);  
	        btn2.addActionListener(this);  
	        
	        btn1.setBounds(150, 60 + height*(h), 100, 20);  
	        btn2.setBounds(270, 60 + height*(h++), 250, 20);  
	        frame.add(btn1);  
	        frame.add(btn2);  
	        
	        //frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		logger.info("кнопка '" + e.getActionCommand() + "' нажата.");
		if (e.getSource() == btn1) {
			Map<String,String> keyValue = new HashMap<>();
			for (JTextField textField : textFields) {
				keyValue.put(textField.getName(), textField.getText());
			}
			reader.update(keyValue);
		} else if(e.getSource() == btn2) {
			int i=0;
			for (String keyValLabel: reader.getProperties()) {
				labels.get(i).setText(keyValLabel.split(delimeter)[2].trim());
				textFields.get(i).setText(keyValLabel.split(delimeter)[1].trim());
				textFields.get(i).setName(keyValLabel.split(delimeter)[0].trim());
				i++;
			}
		}
	}
	
}
