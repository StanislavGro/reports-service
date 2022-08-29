package ru.isands.reportsservice.entity.enums;

public enum Values {
    STRING("Значение текcтового поля ");

    private final String value;

    Values(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
