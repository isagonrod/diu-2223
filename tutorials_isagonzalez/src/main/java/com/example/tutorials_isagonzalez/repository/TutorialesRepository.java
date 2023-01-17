package com.example.tutorials_isagonzalez.repository;

import com.example.tutorials_isagonzalez.model.TutorialesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialesRepository extends JpaRepository<TutorialesVO, String> {
}
