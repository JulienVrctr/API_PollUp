package com.pollup.api.controller;

import com.pollup.api.model.Problem;
import com.pollup.api.model.ProblemDto;
import com.pollup.api.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @PostMapping(value = "add_problem")
    public ResponseEntity<String> postProblem(@RequestBody ProblemDto problemDto) {

        problemService.saveProblem(problemDto);

        return ResponseEntity.ok("");
    }

    /**
     * Read - Get all problems
     * @return - An Iterable object of Problem full filled
     */
    @GetMapping("/problems")
    public Iterable<Problem> getProblems() {
        return problemService.getProblems();
    }

    /**
     * Read - Get one problem
     * @param idproblem The id of the problem
     * @return An Problem object full filled
     */
    @GetMapping("/problem/{idproblem}")
    public Problem getProblem(@PathVariable("idproblem") final Long idproblem) {
        Optional<Problem> problem = problemService.getProblem(idproblem);
        if(problem.isPresent()) {
            return problem.get();
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a problem
     * @param idproblem - The id of the music to delete
     */
    @DeleteMapping("/delete_problem/{idproblem}")
    public void deleteProblem(@PathVariable("idproblem") final Long idproblem) {
        problemService.deleteProblem(idproblem);
    }
}
