package com.school_services.school_services.service;

import com.school_services.school_services.entity.School;
import com.school_services.school_services.repository.SchoolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return schoolRepository.findAll().stream()
                .collect(Collectors.toList());
    }
    
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id)
                .orElse(null);
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public School updateSchool(Long id, School school) {
        if (!schoolRepository.existsById(id)) {
            return null;
        }
        School existingSchool = schoolRepository.findById(id).orElse(null);
        if (existingSchool == null) {
            return null;
        }
        existingSchool.setName(school.getName());
        existingSchool.setAddress(school.getAddress());
        return schoolRepository.save(existingSchool);
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
