package ru.rtk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Country {
    @JsonProperty("name")
    String shortCode;
    @JsonProperty("country")
    String name;
    @JsonProperty("code")
    String phoneCode;
}
