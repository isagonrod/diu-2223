package com.example.tutoriales.repository;

import com.example.tutoriales.model.TutorialesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialesRepository extends JpaRepository<TutorialesVO, String> {
}
