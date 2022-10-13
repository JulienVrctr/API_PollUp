package com.pollup.api.controller;

import com.pollup.api.model.*;
import com.pollup.api.service.ArtistService;
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
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public List<ArtistMusicDto> getAllArtists(){
        return artistService.getAllArtists();
    }

    @GetMapping("/artistsbyname/{name}")
    public List<ArtistMusicDto> getArtistByNameLike(@PathVariable("name") final String name){
        return artistService.getArtistByNameLike(name);
    }

    @GetMapping("/artist/{idartist}")
    public Optional<ArtistMusicDto> getMusicArtistById(@PathVariable("idartist") final Long idartist){
        return artistService.getArtistById(idartist);
    }

    @GetMapping("/login/{mail}/{password}")
    public Long getMusicArtistById(@PathVariable("mail") final String mail, @PathVariable("password") final String password){
        Optional<ArtistMusicDto> artist = artistService.LoginArtist(mail, password);
        return artist.get().getIdartist();
    }

    @GetMapping("/artist_by_featuring/{idmusic}")
    public String getArtistFeaturingNameByIdmusic(@PathVariable("idmusic") final Long idmusic){
        return artistService.getArtistFeaturingNameByIdmusic(idmusic);
    }

    @PostMapping("add_artist")
    public Long postArtist(@RequestParam("file") MultipartFile file, @RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam String avatar, @RequestParam String password, @RequestParam String registration_date) throws Exception {
        Artist artist = artistService.saveArtist(file, name, email, phone, avatar, password, registration_date, avatar, file);
        return artist.getIdartist();
    }

    /**
     * Update - Update an existing Artist
     * @param idartist - The id of the artist
     */
    @PutMapping("update_artist/{idartist}")
    public void updateUser(@RequestParam("file") MultipartFile file, @PathVariable Long idartist, @RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam String avatar) throws Exception {
        Optional<ArtistMusicDto> userExisting =  artistService.getArtistById(idartist);
        if(userExisting  == null){
            throw new Exception("User Not Found");
        } else {
            byte[] filebyte;
            if(name.isEmpty()) {
                name = userExisting.get().getName();
            }
            if(email.isEmpty()) {
                email = userExisting.get().getMail();
            }
            if(phone.isEmpty()) {
                phone = userExisting.get().getPhone();
            }
            if(avatar.isEmpty()) {
                avatar = userExisting.get().getAvatar();
            }
            if(file.isEmpty()) {
                filebyte = userExisting.get().getData();
            } else {
                filebyte = file.getBytes();
            }
            artistService.updateArtist(filebyte, name, email, phone, avatar, idartist);
        }
    }

    @GetMapping("/artist_avatar/{idartist}")
    public ResponseEntity<Resource> downloadAvatar(@PathVariable("idartist") final Long idartist){
        Optional<ArtistMusicDto> artistDto = artistService.getArtistById(idartist);
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + artistDto.get().getAvatar()
                                + "\"")
                .body(new ByteArrayResource(artistDto.get().getData()));
    }

}
