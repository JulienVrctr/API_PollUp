package com.pollup.api.controller;

import com.pollup.api.ResponseData;
import com.pollup.api.model.*;
import com.pollup.api.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/musics")
    public List<MusicArtistDto> getAllMusicArtist(){
        return musicService.getAllMusicArtist();
    }

    @GetMapping("/musicsbytitle/{title}")
    public List<MusicArtistDto> getMusicByTitleLike(@PathVariable("title") final String title){
        return musicService.getMusicByTitleLike(title);
    }
    @GetMapping("/music/{idmusic}")
    public Optional<MusicArtistDto> getMusicArtistById(@PathVariable("idmusic") final Long idmusic){
        return musicService.getMusicArtistById(idmusic);
    }

    @GetMapping("/most_polls/{startweek}/{endweek}")
    public List<MusicArtistDto> getFiveMostPoll(@PathVariable("startweek") final String startweek, @PathVariable("endweek") final String endweek){
        return musicService.getFiveMostPoll(startweek, endweek);
    }
    @PostMapping(value = "add_music")
    public ResponseData postMusic(@RequestParam("file") MultipartFile file, @RequestParam String title, @RequestParam Long idartist, @RequestParam Long idproject) throws Exception {
        Music music = musicService.saveMusic(file, title, idartist, idproject);
        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(music.getIdmusic().toString())
                .toUriString();

        return new ResponseData(music.getTitle(),
                downloadURl,
                file.getSize());
    }

    @GetMapping("/music_data/{idmusic}")
    public ResponseEntity<Resource> downloadCover(@PathVariable("idmusic") final Long idmusic){
        Optional<MusicArtistDto> musicDto = musicService.getMusicArtistById(idmusic);
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + musicDto.get().getTitle()
                                + "\"")
                .body(new ByteArrayResource(musicDto.get().getData()));
    }

    /**
     * Read - Get all FeaturingArtist of an artist
     * @param - idartist - The id of the artist
     * @return - An Iterable object of FeaturingArtist full filled
     */
    @GetMapping("/featurings/{idartist}")
    public Iterable<MusicArtistDto> getFeaturingsByArtist(@PathVariable("idartist") final Long idartist) {
        return musicService.getFeaturingsByArtist(idartist);
    }
}