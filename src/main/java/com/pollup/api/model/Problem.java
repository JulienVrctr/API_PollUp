package com.pollup.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "reported_problem", schema = "public")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idproblem")
    private Long idproblem;

    @ManyToOne
    @JoinColumn(name = "idartist")
    private Artist idartist;

    @Column(name = "type_problem")
    private Integer type_problem;

    @Column(name = "description")
    private String description;
}
