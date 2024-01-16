package br.com.f3pro.gestao_vaga.modules.candidate.useCases;

import br.com.f3pro.gestao_vaga.modules.candidate.CandidateRespository;
import br.com.f3pro.gestao_vaga.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.f3pro.gestao_vaga.modules.candidate.dto.dto.AuthCandidateResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Service
public class AuthCandidateUseCase {

    @Value("${sucurity.token.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRespository candidateRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponse execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var candidate = this.candidateRespository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("username/password incorret"));

        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("francivaldo")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
        var authCandidateResponse = AuthCandidateResponse.builder()
                .acess_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponse;
    }
}
