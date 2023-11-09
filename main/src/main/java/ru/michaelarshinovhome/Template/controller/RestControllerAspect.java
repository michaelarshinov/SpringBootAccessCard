package ru.michaelarshinovhome.Template.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.michaelarshinovhome.Template.dto.wrapped.Wrapper;
import ru.michaelarshinovhome.Template.model.Log;
import ru.michaelarshinovhome.Template.service.LogService;

@Aspect
@Component
public class RestControllerAspect {
	private static final Logger logger = LoggerFactory.getLogger(RestControllerAspect.class);
	
	@Value(value="${module.name}")
	private String moduleName;
	
	@Autowired
	private LogService logService;
	
    @Autowired
    private ObjectMapper mapper;
    
    @Pointcut("within(ru.michaelarshinovhome.Template.controller..*) " +
            "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }
    @Pointcut("within(ru.michaelarshinovhome.Template.controller..*) " +
            "&& @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointcutPost() {
    }
    
    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        HashMap<String, Object> map = new HashMap<>();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }
        return map;
    }
    
    @Before("pointcut()")
    public void logMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);
        Map<String, Object> parameters = getParameters(joinPoint);
        // тут тоже можно логировать/ Шаблон
    }

    @AfterReturning(pointcut = "pointcut()", returning = "entity")
    public void logMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);
        Map<String, Object> parameters = getParameters(joinPoint);
        // тут тоже можно логировать/ Шаблон
    }

	@AfterThrowing(pointcut = "pointcut()", throwing = "ex")
	public void doRecoveryActions(JoinPoint joinPoint, Throwable ex) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);
        Map<String, Object> parameters = getParameters(joinPoint);
        logger.error("url(s): {},\n method(s): {},\n arguments: {},\n {},\n{},\n{}", 
				mapping.value(),mapping.method(), mapper.writeValueAsString(parameters), 
				joinPoint.getSignature().getDeclaringTypeName(), 
				joinPoint.getSignature().getName(), ex.getMessage()+" "+Arrays.toString(ex.getStackTrace()));
		logService.save(Log.builder()
        		.exception(ex.getMessage()+" "+Arrays.toString(ex.getStackTrace()))
        		.date(Timestamp.from(Instant.now()))
        		.message(" url: " + Arrays.toString(mapping.value()) + " arguments: " + 
        		mapper.writeValueAsString(parameters))
        		.methodName(joinPoint.getSignature().getName())
        		.logger(moduleName)
        		.level("error")
        		.meta(null)
        		.build());
	}
    
    @Around(value = "pointcut()")
    public Object executionTimeRequestMapping(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) point.getSignature();
        RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);
        Map<String, Object> parameters = getParameters(point);
		if (object.getClass() == ResponseEntity.class) {
			ResponseEntity<?> entity = (ResponseEntity<?>) object;
			String returning = null;
	        try {
	        	if (entity.getBody().getClass().getSuperclass() == Wrapper.class) {
	        		Wrapper<?> wrapper = (Wrapper<?>) entity.getBody();
	        		returning = mapper.writeValueAsString(wrapper.forLogger());
	        	} else {
	        		returning = mapper.writeValueAsString(mapper.writeValueAsString(entity));
	        	}
        		logger.info("url(s): {},\n method(s): {},\n arguments: {},\n retuning: {}, \n duration: {},\n {},\n{}", 
        				mapping.value(),mapping.method(), mapper.writeValueAsString(parameters), 
        				returning, (endtime-startTime) +"ms", point.getSignature().getDeclaringTypeName(), 
        				point.getSignature().getName()  );
	            logService.save(Log.builder()
	            		.exception(null)
	            		.date(Timestamp.from(Instant.now()))
	            		.message(" url: " + Arrays.toString(mapping.value()) + " arguments: " + 
	            		mapper.writeValueAsString(parameters))
	            		.methodName(point.getSignature().getName())
	            		.meta(returning)
	            		.logger(moduleName)
	            		.level("info")
	            		.execTimeInMilliseconds((int)(endtime-startTime))
	            		.build());
	        } catch (JsonProcessingException e) {
	            logger.error("Error while converting (AfterReturning)", e);
	            logService.save(Log.builder()
	            		.exception(e.getLocalizedMessage())
	            		.date(Timestamp.from(Instant.now()))
	            		.message(" url: " + Arrays.toString(mapping.value()) + " arguments: " + 
	            		mapper.writeValueAsString(parameters))
	            		.methodName(point.getSignature().getName())
	            		.message(returning)
	            		.logger(moduleName)
	            		.level("error")
	            		.meta((endtime-startTime) +"ms")
	            		.build());
	        }
		}

        return object;
    }
    
    @Around(value = "pointcutPost()")
    public Object executionTimePostMapping(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) point.getSignature();
        PostMapping mapping = signature.getMethod().getAnnotation(PostMapping.class);
        Map<String, Object> parameters = getParameters(point);
		if (object.getClass() == ResponseEntity.class) {
			ResponseEntity<?> entity = (ResponseEntity<?>) object;
			String returning = null;
	        try {
	        	if (entity.getBody().getClass().getSuperclass() == Wrapper.class) {
	        		Wrapper<?> wrapper = (Wrapper<?>) entity.getBody();
	        		returning = mapper.writeValueAsString(wrapper.forLogger());
	        	} else {
	        		returning = mapper.writeValueAsString(mapper.writeValueAsString(entity));
	        	}
        		logger.info("url(s): {},\n method(s): {},\n arguments: {},\n retuning: {}, \n duration: {},\n {},\n{}", 
        				mapping.value(),"post", mapper.writeValueAsString(parameters), 
        				returning, (endtime-startTime) +"ms", point.getSignature().getDeclaringTypeName(), 
        				point.getSignature().getName()  );
	            logService.save(Log.builder()
	            		.exception(null)
	            		.date(Timestamp.from(Instant.now()))
	            		.message(" url: " + Arrays.toString(mapping.value()) + " arguments: " + 
	            		mapper.writeValueAsString(parameters))
	            		.methodName(point.getSignature().getName())
	            		.meta(returning)
	            		.logger(moduleName)
	            		.level("info")
	            		.execTimeInMilliseconds((int)(endtime-startTime))
	            		.build());
	        } catch (JsonProcessingException e) {
	            logger.error("Error while converting (AfterReturning)", e);
	            logService.save(Log.builder()
	            		.exception(e.getLocalizedMessage())
	            		.date(Timestamp.from(Instant.now()))
	            		.message(" url: " + Arrays.toString(mapping.value()) + " arguments: " + 
	            		mapper.writeValueAsString(parameters))
	            		.methodName(point.getSignature().getName())
	            		.message(returning)
	            		.logger(moduleName)
	            		.level("error")
	            		.meta((endtime-startTime) +"ms")
	            		.build());
	        }
		}

        return object;
    }
}
