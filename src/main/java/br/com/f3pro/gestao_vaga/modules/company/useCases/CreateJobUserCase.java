package br.com.f3pro.gestao_vaga.modules.company.useCases;

import br.com.f3pro.gestao_vaga.modules.company.entities.JobEntity;
import br.com.f3pro.gestao_vaga.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUserCase {
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);


    }

}
