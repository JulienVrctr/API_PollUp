package com.pollup.api.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "my_listenings", schema = "public")
public class MyListenings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idmy_listenings")
    private Long idmy_listenings;

    @Column(name = "idartist")
    private Long idartist;

    @Column(name = "idmusic")
    private Long idmusic;

    @Column(name = "number_listen")
    private Long number;
}
