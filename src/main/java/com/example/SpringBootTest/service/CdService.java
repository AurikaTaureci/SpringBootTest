package com.example.SpringBootTest.service;

import com.example.SpringBootTest.exception.CdNotFoundException;
import com.example.SpringBootTest.model.Artist;
import com.example.SpringBootTest.model.Cd;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CdService {
    private List<Cd> cdList = new ArrayList<>();

    public void addCd(Cd cd){
        cdList.add(cd);
    }

    public List<Cd> getCdList(){
        return Collections.unmodifiableList(cdList);
    }

    public Cd getLatestCdByArtist(Artist artist){
        return getCdList().stream()
                .filter(a->a.getArtist().equals(artist))
                .sorted((cd1,cd2)->cd2.getReleaseDate().compareTo(cd1.getReleaseDate()))
                .findFirst().orElseThrow(()->new CdNotFoundException("Couldn't find any CD's", new CdNotFoundException("Couldn't find any CD's")));

    }

}







