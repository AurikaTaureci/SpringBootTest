package com.example.SpringBootTest.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cd {

    private String title;
    private Artist artist;
    private LocalDate releaseDate;
}
