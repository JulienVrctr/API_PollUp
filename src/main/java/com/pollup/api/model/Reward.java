package com.pollup.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "reward", schema = "public")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idreward")
    private Long idreward;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "winrewards")
    @JsonIgnore
    private List<Artist> artists = new ArrayList<>();
}
