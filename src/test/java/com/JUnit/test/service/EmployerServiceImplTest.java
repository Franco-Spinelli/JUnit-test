package com.JUnit.test.service;

import com.JUnit.test.exception.ResourceNotFoundException;
import com.JUnit.test.model.Employer;
import com.JUnit.test.repository.EmployerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployerServiceImplTest {
    @Mock
    private EmployerRepository employerRepository;
    @InjectMocks
    private EmployerServiceImpl underTest;
    private Employer employer;

    @BeforeEach
    void setUp() {
        employer = Employer.builder()
                .name("Michael")
                .lastname("Lopez")
                .email("example3@gmail.com")
                .build();
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void canSaveEmployer() {
        //GIVEN
        given(employerRepository.findByEmail(employer.getEmail()))
                .willReturn(Optional.empty());
        given(employerRepository.save(employer)).willReturn(employer);
        //WHEN
        Employer saveEmployer = underTest.saveEmployer(employer);
        //THEN
        assertThat(saveEmployer).isNotNull();
    }
    @Test
    void canSaveEmployerWithThrowException() {
        //GIVEN
        given(employerRepository.findByEmail(employer.getEmail()))
                .willReturn(Optional.of(employer));

        //WHEN
        assertThrows(ResourceNotFoundException.class,()->{
           underTest.saveEmployer(employer);
        });
        //THEN
        verify(employerRepository,never()).save(any(Employer.class));
    }
    @Test
    void getAllEmployers() {
    }

    @Test
    void getEmployerById() {
    }

    @Test
    void deleteEmployer() {
    }
}