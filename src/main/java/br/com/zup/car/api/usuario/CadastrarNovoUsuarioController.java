package br.com.zup.car.api.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoUsuarioController {

    private final UsuarioRepository usuarioRepository;

    public CadastrarNovoUsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarNovoUsuarioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        if (usuarioRepository.existsByCpf(request.getCpf())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe usuário cadastrado com o CPF informado");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe usuário cadastrado com o email informado");
        }

        Usuario usuario = request.toModel();

        usuarioRepository.save(usuario);

        URI location = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
