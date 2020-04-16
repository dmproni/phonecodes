package ru.rtk.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PhoneCode {
    @Id
    String name;
    String country;
    String code;
}
