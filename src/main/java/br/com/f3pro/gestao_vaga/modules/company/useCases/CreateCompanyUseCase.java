package br.com.f3pro.gestao_vaga.modules.company.useCases;

import br.com.f3pro.gestao_vaga.modules.company.entities.CompanyEntity;
import br.com.f3pro.gestao_vaga.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execulte(CompanyEntity companyEntity){

        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user)->{
                    throw new RuntimeException();
                });

      return this.companyRepository.save(companyEntity);

    }
}
