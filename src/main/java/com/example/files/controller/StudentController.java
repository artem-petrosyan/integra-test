package com.example.files.controller;

import com.example.files.model.Student;
import com.example.files.services.AvatarService;
import com.example.files.services.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;
    private final AvatarService avatarService;

    public StudentController(StudentService service, AvatarService avatarService) {
        this.service = service;
        this.avatarService = avatarService;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return service.update(id, student);

    }

    @DeleteMapping("/{id}")
    public Student remove(@PathVariable Long id) {
        return service.remove(id);

    }

    @GetMapping
    public Collection<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/filtered")
    public Collection<Student> filtered(@RequestParam int age) {
        return service.getByAge(age);
    }

    @GetMapping("/age-between")
    public Collection<Student> ageBetween(@RequestParam int min, @RequestParam int max) {
        return service.getByAge(min, max);
    }

    @GetMapping("/by-faculty")
    public Collection<Student> getAllByFaculty(Long facultyId) {
        return service.getByFacultyId(facultyId);
    }

    @PostMapping(value = "/{studentId}/avatart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> save(@PathVariable Long studentId, @RequestBody MultipartFile multipartFile) {
        try {
            return ResponseEntity.ok(avatarService.save(studentId, multipartFile));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
