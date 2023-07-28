package com.example.sql.services;

import com.example.sql.exception.StudentNotFoundException;
import com.example.sql.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();

    private static long COUNTER = 0;

    public Student create(Student student) {
        student.setId(COUNTER++);
        students.put(student.getId(), student);
        return student;
    }

    public Student getById(Long id) {
        Student student = students.get(id);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        return student;
    }

    public Student update(Long id, Student student) {
        if (students.containsKey(id)) {
            student.setId(id);
            students.put(id, student);
            return student;
        }
        throw new StudentNotFoundException();
    }

    public Student remove(long id) {
        Student student = students.remove(id);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        return student;
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public Collection<Student> getByAge(int age) {
        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
