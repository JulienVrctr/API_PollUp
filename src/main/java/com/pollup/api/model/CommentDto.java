package com.pollup.api.model;

import lombok.Data;

@Data
public class CommentDto {

    private Long idcomments;
    private Long idartist;
    private String nameArtist;
    private String avatarArtist;
    private String comment;
    private String date;
}
