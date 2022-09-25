package com.pollup.api.controller;

import com.pollup.api.model.*;
import com.pollup.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "add_project")
    public Long postProject(@RequestParam("file") MultipartFile file, @RequestParam String title, @RequestParam Long idartist, @RequestParam String type, @RequestParam String style) throws Exception {
        Project project = projectService.saveProject(file, title, idartist, type, style);
        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(project.getIdprojects().toString())
                .toUriString();

        return project.getIdprojects();
    }

    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/projectsbytitle/{title}")
    public List<ProjectDto> getProjectByTitleLike(@PathVariable("title") final String title){
        return projectService.getProjectByTitleLike(title);
    }

    @GetMapping("/project/{idproject}")
    public Optional<ProjectDto> getProjectById(@PathVariable("idproject") final Long idproject){
        return projectService.getProjectById(idproject);
    }

    @GetMapping("/project_cover/{idproject}")
    public ResponseEntity<Resource> downloadCover(@PathVariable("idproject") final Long idproject){
        Optional<ProjectDto> projectDto = projectService.getProjectById(idproject);
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + projectDto.get().getCover()
                                + "\"")
                .body(new ByteArrayResource(projectDto.get().getData()));
    }
}
