package br.com.f3pro.gestao_vaga.modules.candidate.controllers;

import br.com.f3pro.gestao_vaga.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.f3pro.gestao_vaga.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var tokek = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(tokek);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }


}
