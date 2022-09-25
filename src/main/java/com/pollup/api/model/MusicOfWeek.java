package com.pollup.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "music_of_week", schema = "public")
public class MusicOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idmusic_of_week")
    private Long idmusic_of_week;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idmusic")
    private Music idmusic;

    @Column(name = "start_week")
    private String startWeek;

    @Column(name = "end_week")
    private String endWeek;
}
