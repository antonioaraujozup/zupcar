package br.com.zup.car.api.carro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro,Long> {
    boolean existsByNome(String nome);
}
