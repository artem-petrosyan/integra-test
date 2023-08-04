package com.example.files.controller;

import com.example.files.model.Faculty;
import com.example.files.services.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return service.create(faculty);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable Long id, @RequestBody Faculty faculty) {
        return service.update(id, faculty);

    }

    @DeleteMapping("/{id}")
    public Faculty remove(@PathVariable Long id) {
        return service.remove(id);

    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return service.getAll();
    }

    @GetMapping("/filtered")
    public Collection<Faculty> filtered(@RequestParam String color) {
        return service.getByColor(color);
    }

    @GetMapping("/by-color-or-name")
    public Collection<Faculty> filteredByColorOrName(@RequestParam String colorOrName) {
        return service.getAllByNameOrColor(colorOrName, colorOrName);
    }

    @GetMapping("/by-student")
    public Faculty getByStudent(Long studentId) {
        return service.getByStudentId(studentId);
    }
}
