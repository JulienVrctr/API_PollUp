package com.pollup.api.model;

import lombok.Data;

@Data
public class MusicOfWeekDto {
    private Long idmusic_of_week;
    private Long idProject;
    private String nameMusic;
    private Long idMusic;
    private String nameArtist;
    private String startWeek;
    private String endWeek;
    private String style;
}
