package com.student_services.student_services.dto;

import com.student_services.student_services.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithSchoolDto {
    private Student student;
    private SchoolDto school;
}
