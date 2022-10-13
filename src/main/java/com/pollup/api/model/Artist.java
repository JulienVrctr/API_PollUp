package com.pollup.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pollup.api.security.UserRole;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "artist", schema = "public")
public class Artist implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idartist")
    private Long idartist;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Lob
    private byte[] data;

    @Column(name = "banniere")
    private String banniere;

    @Lob
    private byte[] dataBanniere;

    @Column(name = "registration_date")
    private String registration_date;

    @Column(name = "nb_polls")
    private Integer nb_polls;

    @OneToMany(mappedBy = "idartist", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "idartist", cascade = CascadeType.ALL)
    private List<Music> musics = new ArrayList<>();

    @OneToMany(mappedBy = "idartist", cascade = CascadeType.ALL)
    private List<SocialNetwork> socialNetwork = new ArrayList<>();

    @OneToMany(mappedBy = "idartist", cascade = CascadeType.ALL)
    private List<Problem> problem = new ArrayList<>();

    @OneToMany(mappedBy = "idartist", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "idartist", cascade = CascadeType.ALL)
    private List<FeaturingArtist> featurings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "reward_artist",
            joinColumns = @JoinColumn(name = "idartist"),
            inverseJoinColumns = @JoinColumn(name = "idreward"))
    private List<Reward> winrewards = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "my_likes",
            joinColumns = @JoinColumn(name = "idartist"),
            inverseJoinColumns = @JoinColumn(name = "idmusic"))
    private List<Music> likedmusic = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getName() { return this.name; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Artist(String name, String email, String phone, String avatar, String password, byte[] data, String registration_date, String banniere, byte[] dataBanniere) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nb_polls = 0;
        this.avatar = avatar;
        this.password = password;
        this.data = data;
        this.registration_date = registration_date;
        this.banniere = banniere;
        this.dataBanniere = dataBanniere;
    }
}
