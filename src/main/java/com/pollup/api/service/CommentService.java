package com.pollup.api.service;

import com.pollup.api.model.*;
import com.pollup.api.repository.ArtistRepository;
import com.pollup.api.repository.CommentRepository;
import com.pollup.api.repository.MusicRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MusicRepository musicRepository;

    public Optional<Comment> getComment(final Long idcomments){
        return commentRepository.findById(idcomments);
    }

    public Iterable<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(final Long idcomments) {
        commentRepository.deleteById(idcomments);
    }

    public Comment saveComment(CommentPostDto commentPostDto) {
        final Comment comment = convertDtoToEntity(commentPostDto);
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
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

    public Music getMusicById(Long idmusic){
        Optional<Music> music = musicRepository.findById(idmusic);
        Music music1 = new Music();
        music1.setIdartist(music.get().getIdartist());
        music1.setIdmusic(music.get().getIdmusic());
        music1.setTitle(music.get().getTitle());
        music1.setComments(music.get().getComments());
        music1.setMusicOfWeek(music.get().getMusicOfWeek());
        music1.setIdproject(music.get().getIdproject());
        return music1;
    }

    private Comment convertDtoToEntity(CommentPostDto commentPostDto){
        Comment comment = new Comment();
        comment.setComment(commentPostDto.getComment());
        comment.setDate(commentPostDto.getDate());
        comment.setIdartist(getArtistById(commentPostDto.getArtist()));
        comment.setIdmusic(getMusicById(commentPostDto.getMusic()));
        return comment;
    }
}
