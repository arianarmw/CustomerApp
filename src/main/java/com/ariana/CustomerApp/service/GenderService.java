package com.ariana.CustomerApp.service;

import com.ariana.CustomerApp.model.Gender;
import com.ariana.CustomerApp.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }
}
