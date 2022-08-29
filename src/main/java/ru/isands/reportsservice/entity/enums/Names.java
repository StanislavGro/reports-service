package ru.isands.reportsservice.entity.enums;

public enum Names {

    NUMBER("Числовое поле"),
    STRING("Текстовое поле");

    private final String value;

    Names(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
