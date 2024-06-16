package com.JUnit.test.service;

import com.JUnit.test.model.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    Employer saveEmployer(Employer employer);
    List<Employer>getAllEmployers();
    Optional<Employer>getEmployerById(Long id);
    void deleteEmployer(Long id);
}
