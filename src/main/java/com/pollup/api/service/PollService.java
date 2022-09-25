package com.pollup.api.service;

import com.pollup.api.model.*;
import com.pollup.api.repository.ArtistRepository;
import com.pollup.api.repository.MusicRepository;
import com.pollup.api.repository.PollRepository;
import com.pollup.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<PollDto> getAllPolls(){
        return pollRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Optional<PollDto> getPollById(Long idpoll){
        return pollRepository.findById(idpoll)
                .map(this::convertEntityToDto);
    }

    private PollDto convertEntityToDto(Poll poll){
        PollDto pollDTO = new PollDto();
        pollDTO.setArtist(poll.getIdartist().getIdartist());
        pollDTO.setMusic(poll.getIdmusic().getIdmusic());
        pollDTO.setDate(poll.getDate());
        return pollDTO;
    }

    public void deletePoll(Long idartist, String date) {
        pollRepository.deleteByIdartistAndDate(getArtistById(idartist), date);

    }

    public Poll savePoll(PollDto pollDto) {
        final Poll poll = convertDtoToEntity(pollDto);
        Poll savedPoll = pollRepository.save(poll);
        return savedPoll;
    }

    private Poll convertDtoToEntity(PollDto pollDto){
        Poll poll = new Poll();
        poll.setIdmusic(getMusicById(pollDto.getMusic()));
        poll.setIdartist(getArtistById(pollDto.getArtist()));
        poll.setIdproject(getProjectById(pollDto.getProject()));
        poll.setDate(pollDto.getDate());
        return poll;
    }

    public Project getProjectById(Long idproject){
        Optional<Project> project = projectRepository.findById(idproject);
        Project project1 = new Project();
        project1.setIdprojects(project.get().getIdprojects());
        project1.setIdartist(project.get().getIdartist());
        project1.setTitle(project.get().getTitle());
        project1.setCover(project.get().getCover());
        project1.setData(project.get().getData());
        return project1;
    }

    public Music getMusicById(Long idmusic){
        Optional<Music> music = musicRepository.findById(idmusic);
        Music music1 = new Music();
        music1.setIdartist(music.get().getIdartist());
        music1.setTitle(music.get().getTitle());
        music1.setIdmusic(music.get().getIdmusic());
        music1.setMusicOfWeek(music.get().getMusicOfWeek());
        music1.setComments(music.get().getComments());
        music1.setIdproject(music.get().getIdproject());
        return music1;
    }

    public Optional<PollDto> getPollByStartWeekAndEndWeek(String startweek, String endweek, Long idartist, String style){
        return pollRepository.findByIdproject_StyleAndDateLessThanEqualAndDateGreaterThanEqualAndIdartist(style, endweek, startweek, getArtistById(idartist))
                .map(this::convertEntityToDto);
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
}
