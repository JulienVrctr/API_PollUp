package com.pollup.api.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectArtistDto {
    private Long idprojects;
    private Long idartist;
    private String title;
    private String cover;
    private List<MusicArtistDto> listMusics;
}
