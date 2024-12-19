package com.school_services.school_services.controller;

import com.school_services.school_services.entity.School;
import com.school_services.school_services.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        if (school == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(school);
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School createdSchool = schoolService.createSchool(school);
        return ResponseEntity.ok(createdSchool);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
        School updatedSchool = schoolService.updateSchool(id, school);
        if (updatedSchool == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSchool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
