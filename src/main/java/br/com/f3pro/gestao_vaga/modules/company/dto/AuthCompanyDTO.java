package br.com.f3pro.gestao_vaga.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

    private  String password;
    private String username;
}
