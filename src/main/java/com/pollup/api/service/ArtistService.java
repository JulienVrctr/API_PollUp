package com.pollup.api.service;

import com.pollup.api.model.*;
import com.pollup.api.repository.ArtistRepository;
import com.pollup.api.security.PasswordEncoder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class ArtistService implements UserDetailsService {

    @Autowired
    private ArtistRepository artistRepository;
    private final PasswordEncoder passwordEncoder;


    public List<ArtistMusicDto> getAllArtists(){
        return artistRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Optional<ArtistMusicDto> getArtistById(Long idartist){
        return artistRepository.findById(idartist)
                .map(this::convertEntityToDto);

    }

    public Optional<ArtistMusicDto> LoginArtist(String mail, String password){
        return artistRepository.findByEmailAndPassword(mail, password)
                .map(this::convertEntityToDto);

    }

    public String getArtistFeaturingNameByIdmusic(Long idmusic){
        return artistRepository.findNameartistFeaturingByIdmusic(idmusic);

    }

    public void updateNbpolls(Long idartist) throws IOException {
        artistRepository.updateNbpolls(idartist);

    }

    public void updateArtist(byte[] file, String name, String mail, String phone, String avatar, Long idartist) throws IOException {
        artistRepository.updateArtist(file, name, mail, phone, avatar, idartist);

    }

    public List<ArtistMusicDto> getArtistByNameLike(String name){
        return artistRepository.findByNameContaining(name)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    private ArtistMusicDto convertEntityToDto(Artist artist){
        ArtistMusicDto artistDTO = new ArtistMusicDto();
        artistDTO.setIdartist(artist.getIdartist());
        artistDTO.setName(artist.getName());
        artistDTO.setMail(artist.getEmail());
        artistDTO.setPhone(artist.getPhone());
        artistDTO.setAvatar(artist.getAvatar());
        artistDTO.setData(artist.getData());
        artistDTO.setRegistration_date(artist.getRegistration_date());
        artistDTO.setPassword(artist.getPassword());
        artistDTO.setNb_polls(artist.getNb_polls());
        artistDTO.setListProjects(artist.getProjects().stream().map(this::getProjectForArtist).collect(Collectors.toList()));
        artistDTO.setNameMusic(artist.getMusics().stream().map(this::getMusicForArtist).collect(Collectors.toList()));
        artistDTO.setSocialNetwork(artist.getSocialNetwork());
        artistDTO.setLikedmusic(artist.getLikedmusic().stream().map(this::getMusicForArtist).collect(Collectors.toList()));
        artistDTO.setFeaturingArtists(artist.getFeaturings().stream().map(this::getFeaturingForArtist).collect(Collectors.toList()));
        artistDTO.setWinrewards(artist.getWinrewards());
        return artistDTO;
    }

    private MusicArtistDto getMusicForArtist(Music music){
        MusicArtistDto musicArtistDTO = new MusicArtistDto();
        musicArtistDTO.setNameArtist(music.getIdartist().getName());
        musicArtistDTO.setIdmusic(music.getIdmusic());
        musicArtistDTO.setIdproject(music.getIdproject().getIdprojects());
        musicArtistDTO.setTitle(music.getTitle());
        return musicArtistDTO;
    }

    private FeaturingArtistDto getFeaturingForArtist(FeaturingArtist featuringArtist){
        FeaturingArtistDto featuringArtistDto = new FeaturingArtistDto();
        featuringArtistDto.setIdfeaturing(featuringArtist.getIdfeaturing());
        featuringArtistDto.setIdmusic(featuringArtist.getIdmusic());
        featuringArtistDto.setIdartist(featuringArtist.getIdartist().getIdartist());
        return featuringArtistDto;
    }

    private ProjectDto getProjectForArtist(Project project){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setIdprojects(project.getIdprojects());
        projectDto.setNameArtist(project.getIdartist().getName());
        projectDto.setTitle(project.getTitle());
        projectDto.setCover(project.getCover());
        projectDto.setListMusics(project.getListMusics().stream().map(this::getMusicForProject).collect(Collectors.toList()));
        return projectDto;
    }

    private MusicArtistDto getMusicForProject(Music music){
        MusicArtistDto musicArtistDTO = new MusicArtistDto();
        musicArtistDTO.setIdmusic(music.getIdmusic());
        musicArtistDTO.setIdproject(music.getIdproject().getIdprojects());
        musicArtistDTO.setTitle(music.getTitle());
        musicArtistDTO.setNameArtist(music.getIdartist().getName());
        musicArtistDTO.setAvatarArtist(music.getIdartist().getAvatar());
        musicArtistDTO.setListComments(music.getComments().stream().map(this::getCommentsForMusic).collect(Collectors.toList()));
        return musicArtistDTO;
    }

    private CommentDto getCommentsForMusic(Comment comment){
        CommentDto commentDTO = new CommentDto();
        commentDTO.setIdcomments(comment.getIdcomments());
        commentDTO.setIdartist(comment.getIdartist().getIdartist());
        commentDTO.setComment(comment.getComment());
        commentDTO.setDate(comment.getDate());
        commentDTO.setNameArtist(comment.getIdartist().getName());
        commentDTO.setAvatarArtist(comment.getIdartist().getAvatar());
        return commentDTO;
    }

    public Artist saveArtist(MultipartFile dataAvatar, String name, String email, String phone, String avatar, String password, String registration_date, String banniere, MultipartFile dataBanniere) throws Exception {
        try {
            Artist artist
                    = new Artist(name, email, phone, avatar, password, dataAvatar.getBytes(), registration_date, banniere, dataBanniere.getBytes()
                    );
            return artistRepository.save(artist);

        } catch (Exception e) {
            throw new Exception("Could not save File");
        }
    }

    private Artist convertDtoToEntity(ArtistDto artistDto){
        Artist artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setEmail(artistDto.getMail());
        artist.setPhone(artistDto.getPhone());
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(artistDto.getPassword());
        artist.setPassword(encodedPassword);
        artist.setAvatar(artistDto.getAvatar());
        artist.setRegistration_date(artistDto.getRegistration_date());
        artist.setNb_polls(0);
        return artist;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return artistRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Mot de passe ou identifiant incorrect !"));
    }

    public void signUpUser(Artist user) {
        //sauvegarde de l'utilisateur avec un mot de passe crypté
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        artistRepository.save(user);
    }
}
