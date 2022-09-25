package com.pollup.api.model;

import lombok.Data;

import java.util.List;

@Data
public class ArtistMusicDto {
    private Long idartist;
    private String name;
    private String mail;
    private String phone;
    private String avatar;
    private byte[] data;
    private String password;
    private String registration_date;
    private Integer nb_polls;
    private List<ProjectDto> listProjects;
    private List<MusicArtistDto> nameMusic;
    private List<SocialNetwork> socialNetwork;
    private List<Reward> winrewards;
    private List<MusicArtistDto> likedmusic;
}
