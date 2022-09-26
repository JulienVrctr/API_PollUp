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
@Table(name = "featuring_artist", schema = "public")
public class FeaturingArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idfeaturing")
    private Long idfeaturing;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "idartist")
    private Artist idartist;

    @Column(name = "idmusic")
    private Long idmusic;
}
