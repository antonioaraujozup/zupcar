package br.com.zup.car.api.usuario;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastrarNovoUsuarioRequest {

    @NotBlank
    @Size(max = 120)
    private String nome;

    @NotNull
    @Size(max = 11)
    @CPF
    private String cpf;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    public CadastrarNovoUsuarioRequest(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public CadastrarNovoUsuarioRequest() {
    }

    public Usuario toModel() {
        return new Usuario(nome,cpf,email);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
