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
@Table(name = "my_likes", schema = "public")
public class MyLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idmy_likes")
    private Long idmy_likes;

    @Column(name = "idartist")
    private Long idartist;

    @Column(name = "idmusic")
    private Long idmusic;
}
