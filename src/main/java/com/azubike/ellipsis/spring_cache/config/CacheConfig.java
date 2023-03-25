package com.azubike.ellipsis.spring_cache.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
  @Bean
  CacheManagerCustomizer<ConcurrentMapCacheManager> customizer() {
    return new ConcurrentCustomizer();
  }

  public static class ConcurrentCustomizer
      implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
    @Override
    public void customize(final ConcurrentMapCacheManager cacheManager) {
      cacheManager.setAllowNullValues(false);
      cacheManager.setStoreByValue(true);
    }
  }
}
