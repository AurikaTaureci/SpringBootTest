package com.example.SpringBootTest.service;

import com.example.SpringBootTest.exception.CdNotFoundException;
import com.example.SpringBootTest.model.Artist;
import com.example.SpringBootTest.model.Cd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import  static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
class CdServiceTest {

    @Test
    @DisplayName("givenNoCdForArtirst  metoda sa arunce exceptia CdNotFoundException  with Junit 5")
    // given when then
     public void givenNoCdForArtistWhenGetLatestCdByArtistThenThrowCdNotFoundException(){
        final CdService cdService = new CdService();

        cdService.addCd(new Cd("Origins", new Artist("Imagine Dragons",""), LocalDate.of(2019,1,9)));
        cdService.addCd(new Cd("All the lost sould", new Artist("James","Blunt"), LocalDate.of(2007,9,14)));
        cdService.addCd(new Cd("Once upon a mind", new Artist("James","Blunt"), LocalDate.of(2019,1,25)));
        cdService.addCd(new Cd("The afterlove", new Artist("James","Blunt"), LocalDate.of(2017,3,24)));

        final Throwable throwable =assertThrows(CdNotFoundException.class,()->cdService.getLatestCdByArtist(new Artist("Jam", "Blunt")));
                assertThat(throwable.getMessage(), is("Couldn't find any CD's"));
                assertThat(throwable.getCause(),instanceOf(CdNotFoundException.class)); // cauza erorii

    }

    @Test
    @DisplayName("givenNoCdForArtirst  metoda sa arunce exceptia CdNotFoundException  with AssertJ")
    // given when then
    public void givenNoCdForArtistWhenGetLatestCdByArtistThenThrowCdNotFoundException2(){
        final CdService cdService = new CdService();

        cdService.addCd(new Cd("Origins", new Artist("Imagine Dragons",""), LocalDate.of(2019,1,9)));
        cdService.addCd(new Cd("All the lost sould", new Artist("James","Blunt"), LocalDate.of(2007,9,14)));
        cdService.addCd(new Cd("Once upon a mind", new Artist("James","Blunt"), LocalDate.of(2019,1,25)));
        cdService.addCd(new Cd("The afterlove", new Artist("James","Blunt"), LocalDate.of(2017,3,24)));

        assertThatExceptionOfType(CdNotFoundException.class).isThrownBy(()->cdService.getLatestCdByArtist(new Artist("Jam","Blunt")))
                .withMessage("Couldn't find any CDs")
                .withCauseExactlyInstanceOf(CdNotFoundException.class);

        assertThatThrownBy(()->cdService.getLatestCdByArtist(new Artist("Jack", "Blunt")))
                .isInstanceOf(CdNotFoundException.class)
                .hasMessage("Couldn't find any CDs")
                .hasCauseExactlyInstanceOf(CdNotFoundException.class);

    }

}