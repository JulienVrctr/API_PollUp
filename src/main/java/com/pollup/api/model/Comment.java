package com.pollup.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "comments", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idcomments")
    private Long idcomments;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "idartist")
    private Artist idartist;

    @ManyToOne
    @JoinColumn(name = "idmusic")
    private Music idmusic;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private String date;
}
