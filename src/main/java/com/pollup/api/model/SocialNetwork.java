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
@Table(name = "social_artist", schema = "public")
public class SocialNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idsocial_artist")
    private Long idsocial_artist;

    @Column(name = "social_network")
    private String social_network;

    @Column(name = "link")
    private String link;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idartist")
    private Artist idartist;
}
