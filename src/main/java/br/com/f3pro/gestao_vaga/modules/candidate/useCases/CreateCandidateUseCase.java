package br.com.f3pro.gestao_vaga.modules.candidate.useCases;

import br.com.f3pro.gestao_vaga.exceptions.UserFoundException;
import br.com.f3pro.gestao_vaga.modules.candidate.CandidateEntity;
import br.com.f3pro.gestao_vaga.modules.candidate.CandidateRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRespository candidateRespository;

    public CandidateEntity execulte(CandidateEntity candidateEntity ) {


        this.candidateRespository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();

                });
        return this.candidateRespository.save(candidateEntity);
    }
}

