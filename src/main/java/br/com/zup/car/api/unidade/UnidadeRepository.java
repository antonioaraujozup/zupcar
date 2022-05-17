package br.com.zup.car.api.unidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Unidade,Long> {
    boolean existsByPlaca(String placa);

    boolean existsByChassi(String chassi);
}
