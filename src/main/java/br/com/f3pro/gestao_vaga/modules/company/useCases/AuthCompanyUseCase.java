package br.com.f3pro.gestao_vaga.modules.company.useCases;

import br.com.f3pro.gestao_vaga.exceptions.ResourceNotFoundException;
import br.com.f3pro.gestao_vaga.modules.company.dto.AuthCompanyDTO;
import br.com.f3pro.gestao_vaga.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Value("${sucurity.token}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {
        var company =companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow();
// verificar a senha são iguais
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        // se não for igual -> erro
        if (!passwordMatches) {
            throw new ResourceNotFoundException("senha ou usuario invalido");
        }
        // se for igual -> Gerar o token

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("francivaldo")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;

    }

}
