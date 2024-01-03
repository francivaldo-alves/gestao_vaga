package br.com.f3pro.gestao_vaga.modules.company.repositories;

import br.com.f3pro.gestao_vaga.modules.company.entities.CompanyEntity;
import br.com.f3pro.gestao_vaga.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
