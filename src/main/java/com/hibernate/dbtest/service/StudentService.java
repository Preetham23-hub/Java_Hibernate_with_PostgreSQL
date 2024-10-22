package com.hibernate.dbtest.service;

import com.hibernate.dbtest.dao.StudentDAO;
import com.hibernate.dbtest.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentDAO studentDAO;

    // Save student
    public Student saveStudentService(Student student) {
        try {
            var studentResponse = Optional.ofNullable(studentDAO.saveStudent(student));
            if (studentResponse.isPresent()) {
                return studentResponse.get();
            } else throw new RuntimeException("Error saving student");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    // Get Student by ID
    public Student getStudentByIdService(Long id) {
        try {
            var studentResponse = Optional.ofNullable(studentDAO.getStudentById(id));
            if (studentResponse.isPresent()) {
                return studentResponse.get();
            } else throw new RuntimeException("Not able to retrieve details of student id: " + id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public List<Student> getAllStudentsService() {
        try {
            var studentResponse = Optional.ofNullable(studentDAO.getAllStudents());
            if (studentResponse.isPresent()) {
                return studentResponse.get();
            } else throw new RuntimeException("Error retrieving get all students");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
    public void deleteStudentservice(long id){
        try {
            studentDAO.deleteStudent(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
