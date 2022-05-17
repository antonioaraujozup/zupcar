package br.com.zup.car.api.carro;

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

    public Long getId() {
        return id;
    }
}
