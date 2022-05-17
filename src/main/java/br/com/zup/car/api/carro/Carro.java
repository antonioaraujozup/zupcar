package br.com.zup.car.api.carro;

import br.com.zup.car.api.unidade.Unidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String marca;

    @Enumerated(EnumType.STRING)
    private Grupo grupo;

    @OneToMany(mappedBy = "carro", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Unidade> unidades = new ArrayList<>();

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

    public void adicionar(Unidade unidade) {
        this.unidades.add(unidade);
    }
}
