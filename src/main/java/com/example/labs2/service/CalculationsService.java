package com.example.labs2.service;

import com.example.labs2.models.Calculations;
import com.example.labs2.repository.CalculationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalculationsService {

    @Autowired
    CalculationsRepository calculationsRepository;

    public List<Calculations> getAll() {
        return calculationsRepository.findAll();
    }

    public Optional<Calculations> getById(long id) {
        return calculationsRepository.findById(id);
    }

    public Calculations save(Calculations calculations) {
        return calculationsRepository.save(calculations);
    }

    public void delete(long id) {
        calculationsRepository.deleteById(id);
    }
}