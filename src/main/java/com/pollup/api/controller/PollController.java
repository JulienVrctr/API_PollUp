package com.pollup.api.controller;

import com.pollup.api.model.*;
import com.pollup.api.service.ArtistService;
import com.pollup.api.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private ArtistService artistService;


    @GetMapping("/polls")
    public List<PollDto> getAllPolls(){
        return pollService.getAllPolls();
    }

    @GetMapping("/poll/{idpoll}")
    public Optional<PollDto> getPollById(@PathVariable("idpoll") final Long idpoll){
        return pollService.getPollById(idpoll);
    }

    @GetMapping("/polls/{startweek}/{endweek}/{idartist}/{style}")
    public Optional<PollDto> getPollByStartWeekAndEndWeek(@PathVariable("startweek") final String startweek, @PathVariable("endweek") final String endweek, @PathVariable("idartist") final Long idartist, @PathVariable("style") final String style){
        return pollService.getPollByStartWeekAndEndWeek(startweek, endweek, idartist, style);
    }

    @PostMapping("/add_poll/{idartist}")
    public ResponseEntity<String> postPoll(@RequestBody PollDto pollDto, @PathVariable("idartist") final Long idartist) throws IOException {
        pollService.savePoll(pollDto);
        artistService.updateNbpolls(idartist);
        return ResponseEntity.ok("");
    }

    /**
     * Delete - Delete a Poll
     * @param idartist - The id of the artiste
     * @param date - The date of the day
     */
    @DeleteMapping("/delete_poll/{idartist}/{date}")
    public void deletePoll(@PathVariable("idartist") final Long idartist, @PathVariable("date") final String date) {
        pollService.deletePoll(idartist, date);
    }
}
