package com.student_services.student_services.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Long SchoolId;
}
