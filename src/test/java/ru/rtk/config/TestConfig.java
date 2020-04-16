package ru.rtk.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.rtk.service.CacheSearchService;
import ru.rtk.service.CountryCacheService;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    @Primary
    public CountryCacheService countryCacheService() {
        return Mockito.mock(CountryCacheService.class);
    }

}
