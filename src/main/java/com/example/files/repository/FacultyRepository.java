package com.example.files.repository;


import com.example.files.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByColor(String color);

    List<Faculty> findAllByColorLikeIgnoreCaseOrNameLikeIgnoreCase(String color, String name);
}
