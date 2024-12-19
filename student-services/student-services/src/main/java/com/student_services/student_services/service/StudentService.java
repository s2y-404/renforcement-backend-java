package com.student_services.student_services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_services.student_services.entity.Student;
import com.student_services.student_services.dto.SchoolDto;
import com.student_services.student_services.dto.StudentWithSchoolDto;
import com.student_services.student_services.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RestTemplateService restTemplateService;

    @Autowired
    public StudentService(StudentRepository studentRepository, RestTemplateService restTemplateService) {
        this.studentRepository = studentRepository;
        this.restTemplateService = restTemplateService;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student student) {
        if (!studentRepository.existsById(id)) {
            return null;
        }

        student.setName(student.getName());
        student.setAge(student.getAge());
        student.setGender(student.getGender());
        student.setSchoolId(student.getSchoolId());
        return studentRepository.save(student);
    }

    public StudentWithSchoolDto findByIdWithSchool(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            return null;
        }

        SchoolDto schoolDto = restTemplateService.getSchool(student.get().getSchoolId());

        return new StudentWithSchoolDto(student.get(), schoolDto);
    }
}