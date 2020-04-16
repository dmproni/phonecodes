package ru.rtk;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rtk.model.PhoneCode;

import java.util.*;

@SpringBootTest
public class Test1 {

    @Value("${baseUri}")
    private String baseUri;

    private static Map<String, String> jsonBodyToMap(JsonNode json) {
        Map<String, String> buff = new HashMap<>();
        Iterator<String> names = json.fieldNames();
        while (names.hasNext()) {
            String name = names.next();
            buff.put(name, json.get(name).textValue());
        }
        return buff;
    }

    @Test
    void test1() {
        Map<String, String> phones = WebClient.create(baseUri).get().uri("phone.json").retrieve()
                .bodyToMono(JsonNode.class)
                .map(node -> jsonBodyToMap(node))
                .block();

        Mono<Map<String, String>> countries = WebClient.create(baseUri).get().uri("names.json").retrieve()
                .bodyToMono(JsonNode.class)
                .map(node -> jsonBodyToMap(node));

        Mono<Collection<PhoneCode>> phoneCodes = countries.map(
                rec -> {
                    Collection<PhoneCode> buff = new ArrayList<>();
                    rec.forEach((k, v) -> buff.add(
                            new PhoneCode(k, v, phones.getOrDefault(k, ""))));
                    return buff;
                });

        phoneCodes.block().forEach(v -> System.out.println(v));
    }
}
