package br.com.zup.car.api.carro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastrarNovoCarroRequest {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 100)
    private String marca;

    @NotNull
    private Grupo grupo;

    public CadastrarNovoCarroRequest(String nome, String marca, Grupo grupo) {
        this.nome = nome;
        this.marca = marca;
        this.grupo = grupo;
    }

    public CadastrarNovoCarroRequest() {
    }

    public Carro toModel() {
        return new Carro(nome,marca,grupo);
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public Grupo getGrupo() {
        return grupo;
    }
}
