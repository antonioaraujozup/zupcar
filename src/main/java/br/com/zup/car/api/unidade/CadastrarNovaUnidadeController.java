package br.com.zup.car.api.unidade;

import br.com.zup.car.api.carro.Carro;
import br.com.zup.car.api.carro.CarroRepository;
import br.com.zup.car.api.unidade.CadastrarNovaUnidadeRequest;
import br.com.zup.car.api.unidade.Unidade;
import br.com.zup.car.api.unidade.UnidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovaUnidadeController {

    private final CarroRepository carroRepository;
    private final UnidadeRepository unidadeRepository;

    public CadastrarNovaUnidadeController(CarroRepository carroRepository, UnidadeRepository unidadeRepository) {
        this.carroRepository = carroRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @PostMapping("/carros/{idCarro}/unidades")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable Long idCarro, @RequestBody @Valid CadastrarNovaUnidadeRequest request,
                                       UriComponentsBuilder uriComponentsBuilder) {
        Carro carro = carroRepository.findById(idCarro)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado"));

        if (unidadeRepository.existsByPlaca(request.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unidade com placa já cadastrada");
        }

        if (unidadeRepository.existsByChassi(request.getChassi())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unidade com chassi já cadastrado");
        }

        Unidade unidade = request.toModel(carro);

        carro.adicionar(unidade);

        carroRepository.flush();

        URI location = uriComponentsBuilder.path("/carros/{idCarro}/unidades/{idUnidade}")
                .buildAndExpand(idCarro, unidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
