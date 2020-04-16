package ru.rtk;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.rtk.service.PhoneCodesLocalCacheService;
import ru.rtk.service.PhoneCodesLocalCacheServiceImpl;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    @Primary
    public PhoneCodesLocalCacheService phoneCodesLocalCacheService() {
        return Mockito.mock(PhoneCodesLocalCacheService.class);
    }
}
