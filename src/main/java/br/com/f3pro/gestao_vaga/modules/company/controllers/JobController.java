package br.com.f3pro.gestao_vaga.modules.company.controllers;

import br.com.f3pro.gestao_vaga.modules.company.entities.JobEntity;
import br.com.f3pro.gestao_vaga.modules.company.useCases.CreateJobUserCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUserCase createJobUserCase;

@PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity){

    return this.createJobUserCase.execute(jobEntity);

    }
}
