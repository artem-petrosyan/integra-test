package com.example.files.services;

import com.example.files.exception.FacultyNotFoundException;
import com.example.files.exception.StudentNotFoundException;
import com.example.files.model.Faculty;
import com.example.files.model.Student;
import com.example.files.repository.FacultyRepository;
import com.example.files.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        existingStudent.setAge(student.getAge());
        existingStudent.setName(student.getName());
        return studentRepository.save(existingStudent);
    }

    public Student remove(long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getByAge(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Collection<Student> getByFacultyId(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElseThrow(FacultyNotFoundException::new);
    }
}
