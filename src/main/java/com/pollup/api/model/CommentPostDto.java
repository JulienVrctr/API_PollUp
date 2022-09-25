package com.pollup.api.model;

import lombok.Data;

@Data
public class CommentPostDto {

    private Long music;
    private Long artist;
    private String comment;
    private String date;
}
