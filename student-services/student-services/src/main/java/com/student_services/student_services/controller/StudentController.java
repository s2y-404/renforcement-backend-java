package com.student_services.student_services.controller;

import com.student_services.student_services.entity.Student;
import com.student_services.student_services.dto.SchoolDto;
import com.student_services.student_services.dto.StudentWithSchoolDto;
import com.student_services.student_services.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.save(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/school")
    public ResponseEntity<StudentWithSchoolDto> getStudentByIdWithSchool(@PathVariable Long id) {
        StudentWithSchoolDto studentWithSchoolDto = studentService.findByIdWithSchool(id);
        if (studentWithSchoolDto != null) {
            return ResponseEntity.ok(studentWithSchoolDto);
        }
        return ResponseEntity.notFound().build();
    }
}