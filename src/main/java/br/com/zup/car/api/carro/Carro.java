package br.com.zup.car.api.carro;

import javax.persistence.*;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String marca;

    @Enumerated(EnumType.STRING)
    private Grupo grupo;

    public Carro(String nome, String marca, Grupo grupo) {
        this.nome = nome;
        this.marca = marca;
        this.grupo = grupo;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Carro() {
    }

    public Long getId() {
        return id;
    }
}
