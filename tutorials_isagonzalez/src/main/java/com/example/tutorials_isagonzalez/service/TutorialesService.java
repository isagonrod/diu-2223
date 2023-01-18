package com.example.tutorials_isagonzalez.service;

import com.example.tutorials_isagonzalez.model.TutorialesVO;
import com.example.tutorials_isagonzalez.model.dto.TutorialesDTO;
import com.example.tutorials_isagonzalez.model.dto.converter.TutorialesDTOConverter;
import com.example.tutorials_isagonzalez.repository.TutorialesRepository;
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

    public List<TutorialesDTO> getTutorialsByTitle(String title) {
        return TutorialesDTOConverter.convertListToDto(repository.findAllByTitleContaining(title));
    }

    public List<TutorialesDTO> getTutorialsByPublished(Boolean published) {
        return TutorialesDTOConverter.convertListToDto(repository.findAllByPublished(published));
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
