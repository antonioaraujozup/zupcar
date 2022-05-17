package br.com.zup.car.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
