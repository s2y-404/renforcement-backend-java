package com.student_services.student_services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.student_services.student_services.dto.SchoolDto;

@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    public SchoolDto getSchool(long id) {
        String url = "http://SCHOOL-SERVICES/schools/" + id;
        return restTemplate.getForObject(url, SchoolDto.class);
    }

}
