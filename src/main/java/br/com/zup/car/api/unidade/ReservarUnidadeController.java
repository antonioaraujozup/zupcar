package br.com.zup.car.api.unidade;

import br.com.zup.car.api.usuario.Usuario;
import br.com.zup.car.api.usuario.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ReservarUnidadeController {

    private final UnidadeRepository unidadeRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservarUnidadeController(UnidadeRepository unidadeRepository, UsuarioRepository usuarioRepository) {
        this.unidadeRepository = unidadeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PatchMapping("/unidades/{idUnidade}")
    @Transactional
    public ResponseEntity<?> reservar(@PathVariable Long idUnidade, @RequestBody @Valid ReservarUnidadeRequest request) {
        Unidade unidade = unidadeRepository.findById(idUnidade)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada"));

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        unidade.reservar(usuario);

        unidadeRepository.save(unidade);

        return ResponseEntity.noContent().build();
    }
}
