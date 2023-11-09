package ru.michaelarshinovhome.Template.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import ru.michaelarshinovhome.Template.service.LogService;

@EnableAsync
@Configuration
public class SchedulerConfig  {
	private Logger logger = LoggerFactory.getLogger(SchedulerConfig .class);
	@Value("${log.clean.up.older.than.days}")
	private int days;
	
	@Autowired
	LogService service;
	
    @Async
    @Scheduled(timeUnit = TimeUnit.HOURS, initialDelay = 0,
    fixedRateString = "${log.clean.up.every.hours}")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        logger.info("Очистка базы данных таблицы Log. Удаляются старые записи "
        		+ "старше " + days + " дней.");
        long count = service.count();
        service.deleteLogs(days);
        logger.info("Удалено " + (count - service.count()) + " записей.");
    }
}
