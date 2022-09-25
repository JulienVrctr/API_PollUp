package com.pollup.api.model;

import lombok.Data;

@Data
public class MusicDto {
    private Long artist;
    private String title;
    private byte[] data;
}
