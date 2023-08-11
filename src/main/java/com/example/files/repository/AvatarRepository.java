package com.example.files.repository;

import com.example.files.model.Avatar;
import com.example.files.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findFirstByStudent(Student student);
}
