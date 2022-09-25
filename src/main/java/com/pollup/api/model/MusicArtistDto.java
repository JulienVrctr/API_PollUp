package com.pollup.api.model;

import lombok.Data;

import java.util.List;

@Data
public class MusicArtistDto {
    private Long idmusic;
    private Long idproject;
    private String nameArtist;
    private String avatarArtist;
    private String title;
    private byte[] data;
    private List<CommentDto> listComments;
}
