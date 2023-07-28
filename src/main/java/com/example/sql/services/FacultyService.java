package com.example.sql.services;

import com.example.sql.exception.FacultyNotFoundException;
import com.example.sql.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();

    private static long COUNTER = 0;

    public Faculty create(Faculty faculty) {
        faculty.setId(COUNTER++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getById(Long id) {
        Faculty faculty = faculties.get(id);
        if (faculty == null) {
            throw new FacultyNotFoundException();
        }
        return faculty;
    }

    public Faculty update(Long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            faculty.setId(id);
            faculties.put(id, faculty);
            return faculty;
        }
        throw new FacultyNotFoundException();
    }

    public Faculty remove(long id) {
        Faculty faculty = faculties.remove(id);
        if (faculty == null) {
            throw new FacultyNotFoundException();
        }
        return faculty;
    }

    public Collection<Faculty> getAll() {
        return faculties.values();
    }

    public Collection<Faculty> getByColor(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
