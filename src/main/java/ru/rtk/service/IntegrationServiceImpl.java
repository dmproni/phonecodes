package ru.rtk.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rtk.model.Country;
import ru.rtk.repository.CountryRepository;

import java.util.*;

import static ru.rtk.utils.JsonUtils.jsonBodyToMap;

@Service
public class DefaultIntegrationService implements IntegrationService {
    private CountryRepository countryRepository;
    private CountryCacheService cacheService;

    @Value("${baseUri}")
    private String baseUri;
    @Value("${countriesJsonName}")
    private String countriesJsonName;
    @Value("${phoneCodesJsonName}")
    private String phoneCodesJsonName;

    public DefaultIntegrationService(CountryRepository countryRepository, CountryCacheService cacheService) {
        this.countryRepository = countryRepository;
        this.cacheService = cacheService;
    }

    @Override
    public void refresh() {
        final Collection<Country> countries = download();
        countryRepository.deleteAll();
        countryRepository.saveAll(countries);
        cacheService.setCache(countries);
    }

    private Collection<Country> download() {
        Map<String, String> phones = WebClient.create(baseUri)
                .get().uri(phoneCodesJsonName).retrieve()
                .bodyToMono(JsonNode.class)
                .map(node -> jsonBodyToMap(node))
                .block();

        Mono<Map<String, String>> countries = WebClient.create(baseUri)
                .get().uri(countriesJsonName).retrieve()
                .bodyToMono(JsonNode.class)
                .map(node -> jsonBodyToMap(node));

        Mono<Collection<Country>> phoneCodes = countries.map(
                rec -> {
                    Collection<Country> buff = new ArrayList<>();
                    rec.forEach((k, v) -> buff.add(
                            new Country(k, v, phones.getOrDefault(k, ""))));
                    return buff;
                });

        return phoneCodes.block();
    }

}
