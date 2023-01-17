package com.example.tutorials_isagonzalez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tutorial")
public class TutorialesVO {
    @Id
    @Column(nullable = false)
    private String id;
    private String title;
    private String description;
    private Boolean published;

}
