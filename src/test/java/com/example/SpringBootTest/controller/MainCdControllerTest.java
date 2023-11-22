package com.example.SpringBootTest.controller;

import com.example.SpringBootTest.exception.CdNotFoundException;
import com.example.SpringBootTest.model.Artist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MainCdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
   public void testGetLatestCdByArtist() throws Exception {
        final Artist artist = new Artist("James","Blunt");
    mockMvc.perform(MockMvcRequestBuilders.get("/cds/getLatestCdOfArtist")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(artist)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.title").value("Once upon a mind"));

   }

   @Test
    public void testGetLatestCdByArtistThrowsCdNotFoundException(){
        final Artist artist = new Artist("Jack","Blunt");

        assertThatThrownBy(()->mockMvc.perform(MockMvcRequestBuilders.get("/cds/getLatestCdOfArtist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist))))
                .hasCause(new CdNotFoundException("Couldn't find any CDs"))
                ;
   }


}