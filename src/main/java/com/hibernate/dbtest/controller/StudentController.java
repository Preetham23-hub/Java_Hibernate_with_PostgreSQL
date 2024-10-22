package com.hibernate.dbtest.controller;

import com.hibernate.dbtest.entity.Student;
import com.hibernate.dbtest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // CREATE or UPDATE a student
    @PostMapping(path = "/save/student",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        try {
            studentService.saveStudentService(student);
            return ResponseEntity.ok("Student saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // READ a student by ID
    @GetMapping("get/student/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Long id) {
        try {
            Student student = studentService.getStudentByIdService(id);
            if (student != null) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // READ all students
    @GetMapping("/all/student")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudentsService();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // DELETE a student by ID
    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
        try {
            studentService.deleteStudentservice(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting student: " + e.getMessage());
        }
    }

}
