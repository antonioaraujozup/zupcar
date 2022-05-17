package br.com.zup.car.api.unidade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReservarUnidadeRequest {

    @NotNull
    @Positive
    private Long idUsuario;

    public ReservarUnidadeRequest(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ReservarUnidadeRequest() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
