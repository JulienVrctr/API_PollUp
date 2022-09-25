package com.pollup.api.service;

import com.pollup.api.model.*;
import com.pollup.api.repository.ArtistRepository;
import com.pollup.api.repository.MusicRepository;
import com.pollup.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<MusicArtistDto> getAllMusicArtist(){
        return musicRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Optional<MusicArtistDto> getMusicArtistById(Long idmusic){
        return musicRepository.findById(idmusic)
                .map(this::convertEntityToDto);

    }

    public List<MusicArtistDto> getMusicByTitleLike(String title){
        return musicRepository.findByTitleContaining(title)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Music saveMusic(MultipartFile file, String title, Long idartist, Long idproject) throws Exception {
        try {
            Artist artist = getArtistById(idartist);
            Project project = getProjectById(idproject);
            Music music
                    = new Music(artist,
                    title,
                    file.getBytes(), project);
            return musicRepository.save(music);

        } catch (Exception e) {
            throw new Exception("Could not save File");
        }
    }

    public Music saveMusic(MusicDto musicDto) {
        final Music music = convertDtoToEntity(musicDto);
        Music savedMusic = musicRepository.save(music);
        return savedMusic;
    }

    private MusicArtistDto convertEntityToDto(Music music){
        MusicArtistDto musicArtistDTO = new MusicArtistDto();
        musicArtistDTO.setIdmusic(music.getIdmusic());
        musicArtistDTO.setIdproject(music.getIdproject().getIdprojects());
        musicArtistDTO.setTitle(music.getTitle());
        musicArtistDTO.setData(music.getData());
        musicArtistDTO.setNameArtist(music.getIdartist().getName());
        musicArtistDTO.setAvatarArtist(music.getIdartist().getAvatar());
        musicArtistDTO.setListComments(music.getComments().stream().map(this::getCommentsForMusic).collect(Collectors.toList()));
        return musicArtistDTO;
    }

    public Artist getArtistById(Long idartist){
        Optional<Artist> artist = artistRepository.findById(idartist);
        Artist artist1 = new Artist();
        artist1.setIdartist(artist.get().getIdartist());
        artist1.setName(artist.get().getName());
        artist1.setEmail(artist.get().getEmail());
        artist1.setPhone(artist.get().getPhone());
        artist1.setAvatar(artist.get().getAvatar());
        artist1.setRegistration_date(artist.get().getRegistration_date());
        artist1.setPassword(artist.get().getPassword());
        artist1.setNb_polls(artist.get().getNb_polls());
        artist1.setMusics(artist.get().getMusics());
        artist1.setSocialNetwork(artist.get().getSocialNetwork());
        artist1.setLikedmusic(artist.get().getLikedmusic());
        artist1.setWinrewards(artist.get().getWinrewards());
        return artist1;
    }

    public Project getProjectById(Long idproject){
        Optional<Project> project = projectRepository.findById(idproject);
        Project project1 = new Project();
        project1.setIdprojects(project.get().getIdprojects());
        project1.setIdartist(project.get().getIdartist());
        project1.setCover(project.get().getCover());
        project1.setTitle(project.get().getTitle());
        project1.setData(project.get().getData());
        project1.setStyle(project.get().getStyle());
        project1.setType(project.get().getType());
        return project1;
    }

    private Music convertDtoToEntity(MusicDto musicDto){
        Music music = new Music();
        music.setTitle(musicDto.getTitle());
        music.setData(musicDto.getData());
        music.setIdartist(getArtistById(musicDto.getArtist()));
        return music;
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


    public List<MusicArtistDto> getFiveMostPoll(String startweek, String endweek){
        return musicRepository.getFiveMostPoll(startweek, endweek)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public List<MusicArtistDto> getFeaturingsByArtist(Long idartist){
        return musicRepository.findFeaturingByIdartist(idartist)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }
}
