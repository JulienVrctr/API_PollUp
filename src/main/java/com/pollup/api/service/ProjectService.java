package com.pollup.api.service;

import com.pollup.api.model.*;
import com.pollup.api.repository.ArtistRepository;
import com.pollup.api.repository.ProjectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public List<ProjectDto> getAllProjects(){
        return projectRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public Optional<ProjectDto> getProjectById(Long idproject){
        return projectRepository.findById(idproject)
                .map(this::convertEntityToDto);

    }
    public Project saveProject(MultipartFile file, String title, Long idartist, String type, String style) throws Exception {
        try {
            Artist artist = getArtistById(idartist);
            Project project
                    = new Project(artist,
                    title,
                    file.getBytes(), type, style);
            return projectRepository.save(project);

        } catch (Exception e) {
            throw new Exception("Could not save File");
        }
    }

    public Project saveProject(MultipartFile file) throws Exception {
        return null;
    }

    public Project saveProject(ProjectArtistDto projectDto) {
        final Project project = convertDtoToEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return savedProject;
    }

    public List<ProjectDto> getProjectByTitleLike(String title){
        return projectRepository.findByTitleContaining(title)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    private ProjectDto convertEntityToDto(Project project){
        ProjectDto projectDTO = new ProjectDto();
        projectDTO.setIdprojects(project.getIdprojects());
        projectDTO.setNameArtist(project.getIdartist().getName());
        projectDTO.setTitle(project.getTitle());
        projectDTO.setCover(project.getCover());
        projectDTO.setData(project.getData());
        projectDTO.setListMusics(project.getListMusics().stream().map(this::getMusicForProject).collect(Collectors.toList()));
        return projectDTO;
    }

    private Project convertDtoToEntity(ProjectArtistDto projectDto){
        Project project = new Project();
        project.setIdartist(getArtistById(projectDto.getIdartist()));
        project.setTitle(projectDto.getTitle());
        project.setCover(projectDto.getCover());
        return project;
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
}
