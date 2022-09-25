package com.pollup.api.model;

import lombok.Data;

@Data
public class ProblemDto {

    private Long artist;
    private Integer type_problem;
    private String description;

}
