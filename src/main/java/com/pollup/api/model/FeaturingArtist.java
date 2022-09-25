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
@Table(name = "featuring_artist", schema = "public")
public class FeaturingArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idfeaturing")
    private Long idfeaturing;

    @Column(name = "idartist")
    private Long idartist;

    @Column(name = "idmusic")
    private Long idmusic;
}
