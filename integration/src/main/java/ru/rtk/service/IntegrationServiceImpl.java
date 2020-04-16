package ru.rtk.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rtk.model.PhoneCode;
import ru.rtk.repository.PhoneCodesRepository;

import java.util.*;

@Service
public class IntegrationServiceImpl implements IntegrationService {
    @Value("${baseUri}")
    private String baseUri;
    @Value("${countriesJsonName}")
    private String countriesJsonName;
    @Value("${phoneCodesJsonName}")
    private String phoneCodesJsonName;

    private PhoneCodesRepository phoneCodesRepository;

    public IntegrationServiceImpl(PhoneCodesRepository phoneCodesRepository) {
        this.phoneCodesRepository = phoneCodesRepository;
    }

    private static Map<String, String> jsonBodyToMap(JsonNode json) {
        Map<String, String> buff = new HashMap<>();
        Iterator<String> names = json.fieldNames();
        while (names.hasNext()) {
            String name = names.next();
            buff.put(name, json.get(name).textValue());
        }
        return buff;
    }

    private Collection<PhoneCode> download() {
        Map<String, String> phones = WebClient.create(baseUri)
                .get().uri(phoneCodesJsonName).retrieve()
                .bodyToMono(JsonNode.class)
                .map(node -> jsonBodyToMap(node))
                .block();

        Mono<Map<String, String>> countries = WebClient.create(baseUri)
                .get().uri(countriesJsonName).retrieve()
                .bodyToMono(JsonNode.class)
                .map(node -> jsonBodyToMap(node));

        Mono<Collection<PhoneCode>> phoneCodes = countries.map(
                rec -> {
                    Collection<PhoneCode> buff = new ArrayList<>();
                    rec.forEach((k, v) -> buff.add(
                            new PhoneCode(k, v, phones.getOrDefault(k, ""))));
                    return buff;
                });

        return phoneCodes.block();
    }

    @Override
    public void refresh() {
        Collection<PhoneCode> newCache = download();
        //phoneCodesRepository.saveAll(newCache);
        WebClient.create("http://localhost:8080")
                .post().uri("/rest/refresh")
                .body(BodyInserters.fromValue(newCache))
                .exchange();
    }
}
