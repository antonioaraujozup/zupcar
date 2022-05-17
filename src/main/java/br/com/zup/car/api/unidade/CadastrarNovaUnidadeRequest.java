package br.com.zup.car.api.unidade;

import br.com.zup.car.api.carro.Carro;
import br.com.zup.car.api.unidade.Unidade;

import javax.validation.constraints.*;

public class CadastrarNovaUnidadeRequest {

    @NotNull
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @NotNull
    @Size(min = 17, max = 17)
    private String chassi;

    @NotNull
    @Min(2000)
    @Max(2099)
    private Integer ano;

    public CadastrarNovaUnidadeRequest(String placa, String chassi, Integer ano) {
        this.placa = placa;
        this.chassi = chassi;
        this.ano = ano;
    }

    public CadastrarNovaUnidadeRequest() {
    }

    public Unidade toModel(Carro carro) {
        return new Unidade(placa,chassi,ano,carro);
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassi() {
        return chassi;
    }

    public Integer getAno() {
        return ano;
    }
}
