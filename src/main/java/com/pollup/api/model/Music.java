package com.pollup.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "musics", schema = "public")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idmusic")
    private Long idmusic;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idartist")
    private Artist idartist;

    @Column(name = "title")
    private String title;

    @Lob
    private byte[] data;

    @OneToMany(mappedBy = "idmusic", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    //@ManyToMany(mappedBy = "likedmusic")
    //@JsonIgnore
    //private List<Artist> artists = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idproject")
    private Project idproject;

    @OneToMany(mappedBy = "idmusic")
    private List<MusicOfWeek> musicOfWeek = new ArrayList<>();

    public Music(Artist idartist, String title, byte[] data , Project idproject) {
        this.idartist = idartist;
        this.title = title;
        this.data = data;
        this.idproject = idproject;
    }
}
