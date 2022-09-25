package com.pollup.api.model;

import lombok.Data;
import java.util.List;

@Data
public class ProjectDto {

    private Long idprojects;
    private String nameArtist;
    private String title;
    private String cover;
    private byte[] data;
    private List<MusicArtistDto> listMusics;
}
