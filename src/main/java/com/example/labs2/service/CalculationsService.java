package com.example.labs2.service;

import com.example.labs2.models.Calculations;
import com.example.labs2.repository.CalculationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationsService {

    @Autowired
    CalculationsRepository calculationsRepository;

    public Calculations save(Calculations calculations) {
        return calculationsRepository.save(calculations);
    }
}