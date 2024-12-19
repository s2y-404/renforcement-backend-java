package com.student_services.student_services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SchoolDto {
    private Long id;
    private String name;
    private String address;
}
