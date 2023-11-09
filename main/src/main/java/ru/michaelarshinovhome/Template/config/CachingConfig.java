package ru.michaelarshinovhome.Template.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
          new ConcurrentMapCache("example"), 
          new ConcurrentMapCache("accessIdTypesMap"),
          new ConcurrentMapCache("accessIdTypesRaw"),
          new ConcurrentMapCache("dictionaryStaticCache")));
        return cacheManager;
    }
    
    //Пример 1
    //@Cacheable(value = "example", key = "#customer.name")
    //public String getAddress(Customer customer) {
    
    //Пример 2
	//@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	//@Cacheable(cacheNames = {"dictionaryStaticCache"}, key = "#parentId")
	//public DictionaryStaticDtoWrapped findAllByParentId(int parentId) {

}
