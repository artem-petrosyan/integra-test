package com.example.files.controller;

import com.example.files.FilesApplication;
import com.example.files.model.Faculty;
import com.example.files.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FilesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @Autowired
    TestRestTemplate template;

    @Test
    void crud() {
        Faculty faculty = new Faculty();
        faculty.setColor("red");
        faculty.setName("math");


        //create
        ResponseEntity<Faculty> response = template.postForEntity("/faculty", faculty, Faculty.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty respBody = response.getBody();
        assertThat(respBody).isNotNull();
        assertThat(respBody.getId()).isNotNull();
        assertThat(respBody.getName()).isEqualTo("math");
        assertThat(respBody.getColor()).isEqualTo("red");

//        ResponseEntity<Faculty> exchange = template
//                .exchange("/faculty/" + respBody.getId(), HttpMethod.GET, HttpEntity.EMPTY, Faculty.class);

        //read
        response = template.getForEntity("/faculty/" + respBody.getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        respBody = response.getBody();
        assertThat(respBody).isNotNull();
        assertThat(respBody.getId()).isNotNull();
        assertThat(respBody.getName()).isEqualTo("math");
        assertThat(respBody.getColor()).isEqualTo("red");

        //update
        faculty = new Faculty();
        faculty.setColor("blue");
        faculty.setName("math");
        template.put("/faculty/" + respBody.getId(), faculty);

        response = template.getForEntity("/faculty/" + respBody.getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        respBody = response.getBody();
        assertThat(respBody).isNotNull();
        assertThat(respBody.getId()).isNotNull();
        assertThat(respBody.getName()).isEqualTo("math");
        assertThat(respBody.getColor()).isEqualTo("blue");

        //delete
        template.delete("/faculty/" + respBody.getId());
        response = template.getForEntity("/faculty/" + respBody.getId(), Faculty.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    void getByStudent() {
        Faculty faculty = new Faculty();
        faculty.setColor("red");
        faculty.setName("math");


        ResponseEntity<Faculty> facultyResponse = template.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(facultyResponse).isNotNull();
        assertThat(facultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


        Student student = new Student();
        student.setName("ivan");
        student.setAge(20);
        student.setFaculty(facultyResponse.getBody());

        ResponseEntity<Student> studRespons = template.postForEntity("/student", student, Student.class);
        assertThat(studRespons).isNotNull();
        assertThat(studRespons.getStatusCode()).isEqualTo(HttpStatus.OK);
        Long studentId = studRespons.getBody().getId();


        ResponseEntity<Faculty> facultyByStud = template
                .getForEntity("/faculty/by-student?studentId=" + studentId, Faculty.class);
        assertThat(facultyByStud).isNotNull();
        assertThat(facultyByStud.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyByStud.getBody()).isNotNull();
        assertThat(facultyByStud.getBody().getId()).isEqualTo(facultyResponse.getBody().getId());
    }
}
