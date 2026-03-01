package dev.java.desafiorelogio.controller;

import dev.java.desafiorelogio.dto.AtualizarRelogioRequest;
import dev.java.desafiorelogio.dto.CriarRelogioRequest;
import dev.java.desafiorelogio.dto.PaginaRelogioDto;
import dev.java.desafiorelogio.dto.RelogioDto;
import dev.java.desafiorelogio.service.RelogioServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/relogios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RelogioController {

    private final RelogioServices servico;

    @GetMapping
    public ResponseEntity<PaginaRelogioDto> listar(
           @RequestParam(defaultValue = "1") int pagina,
           @RequestParam(defaultValue = "12") int porPagina,
           @RequestParam(required = false) String busca,
           @RequestParam(required = false) String marca,
           @RequestParam(required = false) String tipoMovimento,
           @RequestParam(required = false) String materialCaixa,
           @RequestParam(required = false) String tipoVidro,
           @RequestParam(required = false) Integer resistenciaMin,
           @RequestParam(required = false) Integer resistenciaMax,
           @RequestParam(required = false) Long precoMin,
           @RequestParam(required = false) Long precoMax,
           @RequestParam(required = false) Integer diametroMin,
           @RequestParam(required = false) Integer diametroMax,
           @RequestParam(required = false) String ordenar
    ) {
        PaginaRelogioDto pagRelogioDto = servico.listar(pagina, porPagina, busca, marca, tipoMovimento, materialCaixa, tipoVidro, resistenciaMin, resistenciaMax, precoMin, precoMax, diametroMin, diametroMax, ordenar);

        return ResponseEntity.ok(pagRelogioDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelogioDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelogioDto> criar(@RequestBody @Valid CriarRelogioRequest criarRelogioRequest) {
        return ResponseEntity.ok(servico.criar(criarRelogioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelogioDto> atualizar(@RequestBody @Valid UUID id, AtualizarRelogioRequest atualizarRelogioRequest) {
        return ResponseEntity.ok(servico.atualizar(id, atualizarRelogioRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        servico.remover(id);

        return ResponseEntity.noContent().build();
    }
}
