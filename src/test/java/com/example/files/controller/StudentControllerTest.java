package com.example.files.controller;

import com.example.files.model.Student;
import com.example.files.repository.FacultyRepository;
import com.example.files.repository.StudentRepository;
import com.example.files.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    FacultyRepository facultyRepository;

    @MockBean
    StudentRepository studentRepository;

    @SpyBean
    StudentService studentService;

    @SpyBean
    StudentController studentController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    //Виктор Токовенко
    @Test
    void crud() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setAge(20);
        student.setName("ivan");

        when(studentRepository.save(any(Student.class))).thenReturn(student);


        mockMvc.perform(post("/student")
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.age").value("20"))
                .andExpect(jsonPath("$.name").value("ivan"));
    }

    @Test
    void getAll() throws Exception {
        Student s1 = new Student();
        s1.setId(1L);
        s1.setAge(20);
        s1.setName("ivan");

        Student s2 = new Student();
        s2.setId(2L);
        s2.setAge(21);
        s2.setName("marina");

        when(studentRepository.findAll()).thenReturn(List.of(s1, s2));

        mockMvc.perform(get("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));
    }
}
