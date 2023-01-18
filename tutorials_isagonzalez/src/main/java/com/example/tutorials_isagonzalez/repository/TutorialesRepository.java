package com.example.tutorials_isagonzalez.repository;

import com.example.tutorials_isagonzalez.model.TutorialesVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialesRepository extends JpaRepository<TutorialesVO, String> {

    List<TutorialesVO> findAllByPublished(Boolean published);

    List<TutorialesVO> findAllByTitleContaining(String title);
}
