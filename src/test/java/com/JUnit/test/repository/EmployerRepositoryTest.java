package com.JUnit.test.repository;

import com.JUnit.test.model.Employer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EmployerRepositoryTest {
    private Employer employer;
    @Autowired
    private EmployerRepository employerRepository;
    @BeforeEach
    void setUp(){
        employer = Employer.builder()
                .name("Michael")
                .lastname("Lopez")
                .email("example3@gmail.com")
                .build();
    }
    @Test
    void canDeleteEmployer() {
        //GIVEN
        employerRepository.save(employer);
        //WHEN
        employerRepository.deleteById(employer.getId());
        Optional<Employer>employerOptional = employerRepository.findById(employer.getId());
        //THEN
        assertThat(employerOptional.isEmpty()).isTrue();
    }
    @Test
    void canFindById() {
        //GIVEN
        Employer saveEmployer = employerRepository.save(employer);
        //WHEN
        Optional<Employer> employerOptional= employerRepository.findById(saveEmployer.getId());
        //THEN
        assertThat(employerOptional.isPresent()).isEqualTo(true);
    }
    @Test
    void canSaveEmployer() {
        //GIVEN
        Employer employer = Employer.builder()
                .name("Jack")
                .lastname("example")
                .email("exmaple@gmail.com")
                .build();

        //WHEN
        Employer saveEmployer = employerRepository.save(employer);
        //THEN
        assertThat(saveEmployer).isNotNull();
        assertThat(saveEmployer.getId()).isGreaterThan(0);
    }
    @Test
    void canGetAllEmployers() {
        //GIVEN
        Employer employer1 = Employer.builder()
                .name("Jack")
                .lastname("example")
                .email("exmaple@gmail.com")
                .build();
        Employer employer2 = Employer.builder()
                .name("Juliet")
                .lastname("example2")
                .email("exmapl2e@gmail.com")
                .build();
        employerRepository.save(employer);
        employerRepository.save(employer1);
        employerRepository.save(employer2);
        //WHEN
        List<Employer>employerList = employerRepository.findAll();
        //THEN
        assertThat(employerList).isNotNull();
        assertThat(employerList.size()).isEqualTo(3);
    }


}