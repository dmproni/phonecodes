package ru.rtk.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public final class PhoneCode {
    @Getter
    private String name;
    @Getter
    private String country;
    @Getter
    private String code;
}
