package com.pollup.api.controller;

import com.pollup.api.model.MusicOfWeekDto;
import com.pollup.api.service.MusicOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MusicOfWeekController {

    @Autowired
    private MusicOfWeekService musicOfWeekService;

    @GetMapping("/musicsOfWeeks")
    public List<MusicOfWeekDto> getAllMusicArtist(){
        return musicOfWeekService.getAllMusicOfWeek();
    }

    @GetMapping("/musicOfWeek/{idmusic}")
    public Optional<MusicOfWeekDto> getMusicArtistById(@PathVariable("idmusic") final Long idmusic){
        return musicOfWeekService.getMusicOfWeekById(idmusic);
    }

    @GetMapping("/musicOfWeek/{startweek}/{endweek}")
    public List<MusicOfWeekDto> getMusicOfWeekByStartEnd(@PathVariable("startweek") final String startweek, @PathVariable("endweek") final String endweek){
        return musicOfWeekService.getMusicOfWeekByStartEnd(startweek, endweek);
    }
}
