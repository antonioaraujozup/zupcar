package br.com.zup.car.api.unidade;

import br.com.zup.car.api.carro.Carro;
import br.com.zup.car.api.exception.UnidadeJaReservadaException;
import br.com.zup.car.api.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String chassi;
    private Integer ano;
    private boolean reservado = false;

    @Version
    private int version;

    @ManyToOne(optional = false)
    private Carro carro;

    @OneToOne(optional = true)
    private Usuario usuario;

    public Unidade(String placa, String chassi, Integer ano, Carro carro) {
        this.placa = placa;
        this.chassi = chassi;
        this.ano = ano;
        this.carro = carro;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Unidade() {
    }

    public void reservar(Usuario usuario) {
        if (this.isReservado()) {
            throw new UnidadeJaReservadaException("A unidade já está reservada");
        }

        this.reservado = true;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public boolean isReservado() {
        return reservado;
    }
}
