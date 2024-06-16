package com.JUnit.test.controller;

import com.JUnit.test.model.Employer;
import com.JUnit.test.service.EmployerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/employer")
public class EmployerController {
    private EmployerService employerService;
    @PostMapping
    public Employer saveEmployer(@RequestBody Employer employer){
        return employerService.saveEmployer(employer);
    }
    @GetMapping
    public List<Employer>employerList(){
        return employerService.getAllEmployers();
    }
    @GetMapping("/{id]")
    public ResponseEntity<Employer>getEmployerById(@PathVariable("id")  Long id){
        return employerService.getEmployerById(id).map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

}
