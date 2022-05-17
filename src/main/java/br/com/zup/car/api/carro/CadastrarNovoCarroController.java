package br.com.zup.car.api.carro;

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
public class CadastrarNovoCarroController {

    private final CarroRepository carroRepository;

    public CadastrarNovoCarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @PostMapping("/carros")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarNovoCarroRequest request, UriComponentsBuilder uriComponentsBuilder) {
        if (carroRepository.existsByNome(request.getNome())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carro já cadastrado");
        }

        Carro carro = request.toModel();

        carroRepository.save(carro);

        URI location = uriComponentsBuilder.path("/carros/{id}")
                .buildAndExpand(carro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
