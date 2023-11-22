package com.example.SpringBootTest.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestConfiguration {

    public ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }
}
