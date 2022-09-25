package com.pollup.api.model;

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
@Table(name = "projects", schema = "public")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idprojects")
    private Long idprojects;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "idartist")
    private Artist idartist;

    @Column(name = "title")
    private String title;

    @Column(name = "cover")
    private String cover;

    @Lob
    private byte[] data;

    @Column(name = "style")
    private String style;

    @Column(name = "type_project")
    private String type;

    @OneToMany(mappedBy = "idproject", cascade = CascadeType.ALL)
    private List<Music> listMusics = new ArrayList<>();

    public Project(Artist idartist, String title, byte[] data, String type, String style) {
        this.idartist = idartist;
        this.title = title;
        this.data = data;
        this.type = type;
        this.style = style;
    }
}
