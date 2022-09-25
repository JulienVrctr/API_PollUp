package com.pollup.api.service;

import com.pollup.api.model.*;
import com.pollup.api.repository.ArtistRepository;
import com.pollup.api.repository.ProblemRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public Optional<Problem> getProblem(final Long idproblem){
        return problemRepository.findById(idproblem);
    }

    public Iterable<Problem> getProblems() {
        return problemRepository.findAll();
    }

    public void deleteProblem(final Long idproblem) {
        problemRepository.deleteById(idproblem);
    }

    public Problem saveProblem(ProblemDto problemDto) {
        final Problem problem = convertDtoToEntity(problemDto);
        Problem savedProblem = problemRepository.save(problem);
        return savedProblem;
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

    private Problem convertDtoToEntity(ProblemDto problemDto){
        Problem problem = new Problem();
        problem.setIdartist(getArtistById(problemDto.getArtist()));
        problem.setType_problem(problemDto.getType_problem());
        problem.setDescription(problemDto.getDescription());
        return problem;
    }
}
