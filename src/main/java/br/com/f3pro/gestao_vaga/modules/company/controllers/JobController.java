package br.com.f3pro.gestao_vaga.modules.company.controllers;

import br.com.f3pro.gestao_vaga.modules.company.dto.CreateJobDTO;
import br.com.f3pro.gestao_vaga.modules.company.entities.JobEntity;
import br.com.f3pro.gestao_vaga.modules.company.useCases.CreateJobUserCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUserCase createJobUserCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        // jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .build();

        return this.createJobUserCase.execute(jobEntity);

    }
}
