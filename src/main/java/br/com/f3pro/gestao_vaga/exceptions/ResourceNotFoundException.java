package br.com.f3pro.gestao_vaga.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

}
