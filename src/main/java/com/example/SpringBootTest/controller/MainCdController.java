package com.example.SpringBootTest.controller;

import com.example.SpringBootTest.model.Artist;
import com.example.SpringBootTest.model.Cd;
import com.example.SpringBootTest.service.CdService;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/cds")
public class MainCdController {

    final private CdService cdService;

    public MainCdController(CdService cdService) {
        this.cdService = cdService;
    }

    @GetMapping("/getLatestCdOfArtist")
    public Cd getLatestCdByArtist(@RequestBody Artist artist){

        final Cd latestCdOfArtist;
        cdService.addCd(new Cd("Origins", new Artist("Imagine Dragons",""), LocalDate.of(2019,1,9)));
        cdService.addCd(new Cd("All the lost sould", new Artist("James","Blunt"), LocalDate.of(2007,9,14)));
        cdService.addCd(new Cd("Once upon a mind", new Artist("James","Blunt"), LocalDate.of(2019,1,25)));
        cdService.addCd(new Cd("The afterlove", new Artist("James","Blunt"), LocalDate.of(2017,3,24)));

        latestCdOfArtist=cdService.getLatestCdByArtist(artist);
        return latestCdOfArtist;
    }

}



















