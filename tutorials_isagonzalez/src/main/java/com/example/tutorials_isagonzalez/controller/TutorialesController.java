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

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class TutorialesController {
    @Autowired
    private TutorialesService service;

    @GetMapping("/tutorials")
    public ResponseEntity<?> getAllTutorials(@RequestParam(value="title") Optional<String> title, @RequestParam(value="published") Optional<Boolean> published) {
        ResponseEntity<?> result;
        if (title.isPresent()) {
            result = ResponseEntity.ok(service.getTutorialsByTitle(title.get()));
        } else if (published.isPresent()) {
            result = ResponseEntity.ok(service.getTutorialsByPublished(published.get()));
        } else {
            result = ResponseEntity.ok(service.getAllTutorials());
        }
        return result;
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<?> getTutorialById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTutorialById(id));
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
