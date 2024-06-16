package com.JUnit.test.service;

import com.JUnit.test.exception.ResourceNotFoundException;
import com.JUnit.test.model.Employer;
import com.JUnit.test.repository.EmployerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class EmployerServiceImpl implements EmployerService{
    private EmployerRepository employerRepository;
    @Override
    public Employer saveEmployer(Employer employer) {
        Optional<Employer>employerOptional = employerRepository.findByEmail(employer.getEmail());
        if(employerOptional.isPresent()){
            throw  new ResourceNotFoundException("The employer has already exists");

        }
        return employerRepository.save(employer);
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Optional<Employer> getEmployerById(Long id) {
        return employerRepository.findById(id);
    }

    @Override
    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
}
