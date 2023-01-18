package com.example.tutorials_isagonzalez.controller;

import com.example.tutorials_isagonzalez.model.TutorialesVO;
import com.example.tutorials_isagonzalez.model.dto.TutorialesDTO;
import com.example.tutorials_isagonzalez.service.TutorialesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diu")
@CrossOrigin(origins = "http://localhost:8082")
public class TutorialesController {
    @Autowired
    private TutorialesService service;

    @GetMapping("/tutorials")
    public ResponseEntity<?> getAllTutorials() {
        return ResponseEntity.ok(service.getAllTutorials());
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<?> getTutorialById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTutorialById(id));
    }

    @GetMapping("/tutorials/{title}")
    public ResponseEntity<?> getTutorialsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(service.getTutorialsByTitle(title));
    }

    @GetMapping("/tutorials/{published}")
    public ResponseEntity<?> getTutorialsByPublished(@PathVariable Boolean published) {
        return ResponseEntity.ok(service.getTutorialsByPublished(published));
    }

    @PostMapping("/tutorials")
    public ResponseEntity<TutorialesDTO> saveTutorial(@Validated @RequestBody TutorialesVO tutorial) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTutorial(tutorial));
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<?> updateTutorial(@RequestBody TutorialesVO tutorial, @PathVariable String id) {
        tutorial.setId(id);
        return ResponseEntity.ok(service.saveTutorial(tutorial));
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<?> deleteTutorial(@PathVariable String id) {
        service.deleteTutorialById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<?> deleteAllTutorials() {
        service.deleteAllTutorials();
        return ResponseEntity.noContent().build();
    }
}
