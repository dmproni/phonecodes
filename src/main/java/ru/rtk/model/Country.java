package ru.rtk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Document("countries")
public class Country {
    @JsonProperty("name")
    String shortCode;
    @JsonProperty("country")
    String name;
    @JsonProperty("code")
    String phoneCode;
}
