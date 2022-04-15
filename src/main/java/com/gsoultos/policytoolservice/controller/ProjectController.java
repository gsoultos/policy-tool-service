package com.gsoultos.policytoolservice.controller;

import com.gsoultos.policytoolservice.service.ProjectService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {
  private final ProjectService projectService;

  @Autowired
  public ProjectController(@NonNull ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping
  void loadData(@NonNull @RequestBody String exportedData) {
    this.projectService.loadData(exportedData);
  }

  @GetMapping
  String exportData() {
    return this.projectService.exportData();
  }
}
