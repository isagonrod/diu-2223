package com.example.tutoriales.service;

import com.example.tutoriales.model.TutorialesVO;
import com.example.tutoriales.model.dto.TutorialesDTO;
import com.example.tutoriales.model.dto.converter.TutorialesDTOConverter;
import com.example.tutoriales.repository.TutorialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialesService {

    @Autowired
    private TutorialesRepository repository;

    public List<TutorialesDTO> getAllTutorials() {
        return TutorialesDTOConverter.convertListToDto(repository.findAll());
    }

    public TutorialesDTO getTutorialById(String id) {
        return TutorialesDTOConverter.convertToDto(repository.findById(id));
    }

    public List<TutorialesDTO> getTutorialByPublished(Boolean published) {
        return TutorialesDTOConverter.convertToDto(repository.findBy(published));
    }

    public TutorialesDTO saveTutorial(TutorialesVO tutorial) {
        return TutorialesDTOConverter.convertToDto(repository.save(tutorial));
    }

    public void deleteTutorialById(String id) {
        repository.deleteById(id);
    }

    public void deleteAllTutorials() {
        repository.deleteAll();
    }
}
