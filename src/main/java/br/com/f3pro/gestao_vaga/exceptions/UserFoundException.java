package br.com.f3pro.gestao_vaga.exceptions;

public class UserFoundException  extends RuntimeException{
    public UserFoundException(){
        super("Usuário já existe");
    }

}
