package br.com.financeiro.controllers;


import br.com.financeiro.controllers.dto.ClienteDTO;
import br.com.financeiro.model.Cliente;
import br.com.financeiro.repositorys.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteService;

    public ClienteController(ClienteRepository clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(ClienteDTO clienteRequest) {
        final Cliente response = clienteService.save(Cliente.from(clienteRequest));
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(response.getCodigo())
                                .toUri()
                ).body(ClienteDTO.from(response));
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizar(ClienteDTO clienteRequest) {
        final Cliente response = clienteService.save(Cliente.from(clienteRequest));
        return ResponseEntity.ok()
                .body(ClienteDTO.from(response));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> consultar() {
        final List<Cliente> response = clienteService.findAll();

        if (response.isEmpty())
            return ResponseEntity.noContent()
                    .build();

        return ResponseEntity.ok()
                .body(response.stream().map(ClienteDTO::from)
                        .collect(Collectors.toUnmodifiableList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> detalhar(@PathVariable Long id) {
        final Optional<Cliente> response = clienteService.findById(id);

        if (!response.isPresent())
            return ResponseEntity.noContent()
                    .build();

        return ResponseEntity.ok()
                .body(response.map(ClienteDTO::from).get());
    }

}
