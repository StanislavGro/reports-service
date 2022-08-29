package ru.isands.reportsservice.configuration.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseProperties {

    private String url;
    private String username;
    private String password;
    private String classDriver;


}
