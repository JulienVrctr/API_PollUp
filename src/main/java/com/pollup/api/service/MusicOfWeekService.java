package com.pollup.api.service;

import com.pollup.api.model.MusicOfWeek;
import com.pollup.api.model.MusicOfWeekDto;
import com.pollup.api.repository.MusicOfWeekRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class MusicOfWeekService {
    @Autowired
    private MusicOfWeekRepository musicOfWeekRepository;

    public List<MusicOfWeekDto> getAllMusicOfWeek(){
        return musicOfWeekRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Optional<MusicOfWeekDto> getMusicOfWeekById(Long idmusic){
        return musicOfWeekRepository.findById(idmusic)
                .map(this::convertEntityToDto);

    }

    public List<MusicOfWeekDto> getMusicOfWeekByStartEnd(String startweek, String endweek){
        return musicOfWeekRepository.findByStartWeekAndEndWeek(startweek, endweek)
                .stream().map(this::convertEntityToDto).collect(Collectors.toList());

    }

    private MusicOfWeekDto convertEntityToDto(MusicOfWeek musicOfWeek){
        MusicOfWeekDto musicOfWeekDTO = new MusicOfWeekDto();
        musicOfWeekDTO.setIdmusic_of_week(musicOfWeek.getIdmusic_of_week());
        musicOfWeekDTO.setIdProject(musicOfWeek.getIdmusic().getIdproject().getIdprojects());
        musicOfWeekDTO.setIdMusic(musicOfWeek.getIdmusic().getIdmusic());
        musicOfWeekDTO.setNameMusic(musicOfWeek.getIdmusic().getTitle());
        musicOfWeekDTO.setStartWeek(musicOfWeek.getStartWeek());
        musicOfWeekDTO.setEndWeek(musicOfWeek.getEndWeek());
        musicOfWeekDTO.setNameArtist(musicOfWeek.getIdmusic().getIdartist().getName());
        musicOfWeekDTO.setStyle(musicOfWeek.getIdmusic().getIdproject().getStyle());
        return musicOfWeekDTO;
    }
}
