package com.pollup.api.model;

import lombok.Data;

@Data
public class ArtistDto {
    private String name;
    private String mail;
    private String phone;
    private String avatar;
    private String password;
    private String registration_date;
}
