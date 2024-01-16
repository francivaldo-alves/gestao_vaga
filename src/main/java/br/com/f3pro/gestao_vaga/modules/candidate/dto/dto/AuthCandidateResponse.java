package br.com.f3pro.gestao_vaga.modules.candidate.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidateResponse{
    private String acess_token;
    private Long expires_in;
}
