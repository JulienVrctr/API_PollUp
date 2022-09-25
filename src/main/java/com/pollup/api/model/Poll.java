package com.pollup.api.model;

import com.pollup.api.service.MusicOfWeekService;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "pollmusic", schema = "public")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idpollmusic_artist")
    private Long idpollmusic_artist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idmusic")
    private Music idmusic;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idartist")
    private Artist idartist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idproject")
    private Project idproject;

    @Column(name = "date")
    private String date;

}
