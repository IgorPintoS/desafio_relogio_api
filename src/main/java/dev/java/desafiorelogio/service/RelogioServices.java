package dev.java.desafiorelogio.service;

import dev.java.desafiorelogio.dto.AtualizarRelogioRequest;
import dev.java.desafiorelogio.dto.CriarRelogioRequest;
import dev.java.desafiorelogio.dto.PaginaRelogioDto;
import dev.java.desafiorelogio.dto.RelogioDto;
import dev.java.desafiorelogio.entity.Relogio;
import dev.java.desafiorelogio.entity.enums.MaterialCaixa;
import dev.java.desafiorelogio.entity.enums.TipoMovimento;
import dev.java.desafiorelogio.entity.enums.TipoVidro;
import dev.java.desafiorelogio.exception.RelogioNaoEncontradoException;
import dev.java.desafiorelogio.mapper.RelogioMapper;
import dev.java.desafiorelogio.repository.RelogioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.java.desafiorelogio.service.RelogioSpecs.*;

@Service
@RequiredArgsConstructor
public class RelogioServices {

    private final RelogioRepository repo;
    private final RelogioMapper mapper;

    public PaginaRelogioDto listar(int pagina,
                                   int porPagina,
                                   String busca,
                                   String marca,
                                   String tipoMovimento,
                                   String materialCaixa,
                                   String tipoVidro,
                                   Integer resistenciaMin,
                                   Integer resistenciaMax,
                                   Long precoMin,
                                   Long precoMax,
                                   Integer diamentroMin,
                                   Integer diametroMax,
                                   String ordenar
    ) {
        int paginaSegura = Math.max(1, pagina);
        int porPaginaSegura = Math.min(60, Math.max(1, porPagina));

        TipoMovimento tipoMovimentoEnum = TipoMovimento.fromApi(tipoMovimento);
        MaterialCaixa materialCaixaEnum = MaterialCaixa.fromApi(materialCaixa);
        TipoVidro tipoVidroEnum = TipoVidro.fromApi(tipoVidro);

        OrdenacaoRelogios ordenacaoRelogios = OrdenacaoRelogios.fromApi(ordenar);

        Sort sort = switch (ordenacaoRelogios) {
            case MAIS_RECENTES -> Sort.by(Sort.Direction.DESC, "criadoEm");
            case PRECO_CRESC -> Sort.by(Sort.Direction.ASC, "precoEmCentavos");
            case PRECO_DESC -> Sort.by(Sort.Direction.DESC, "precoEmCentavos");
            case DIAMETRO_CRESC -> Sort.by(Sort.Direction.ASC, "diametroMm");
            case RESISTENCIA_DESC -> Sort.by(Sort.Direction.DESC, "resistenciaAguaM");
        };

        Pageable pageable = PageRequest.of(paginaSegura - 1, porPaginaSegura, sort);

        Specification<Relogio> spec = Specification.where(busca(busca))
                .and(marcaIgual(marca))
                .and(tipoMovimentoIgual(tipoMovimentoEnum))
                .and(materialCaixaIgual(materialCaixaEnum))
                .and(tipoVidroIgual(tipoVidroEnum))
                .and(resistenciaAguaMEntre(resistenciaMin, resistenciaMax))
                .and(precoEntre(precoMin, precoMax))
                .and(diametroEntre(diamentroMin, diametroMax));

        Page<Relogio> resultado = repo.findAll(spec, pageable);

        return new PaginaRelogioDto(
                resultado.getContent().stream().map(mapper::toDto).toList(),
                resultado.getTotalElements());
    }

    public RelogioDto buscarPorId(UUID id) {
        Relogio relogio = repo.findById(id)
                .orElseThrow(() -> new RelogioNaoEncontradoException("Relógio não encontrado."));

        return mapper.toDto(relogio);
    }

    public RelogioDto criar(CriarRelogioRequest req) {
        Relogio relogio = mapper.toCreateEntityFromDto(req);

        return mapper.toDto(repo.save(relogio));
    }

    public RelogioDto atualizar(UUID id, AtualizarRelogioRequest req) {
        Relogio relogio = repo.findById(id)
                .orElseThrow(() -> new RelogioNaoEncontradoException("Relógio não encontrado."));

        mapper.toUpdateEntityFromDto(relogio, req);

        return mapper.toDto(repo.save(relogio));
    }

    public void remover(UUID id) {
        if (!repo.existsById(id)) {
            throw new RelogioNaoEncontradoException("Relógio não encontrado.");
        }
        repo.deleteById(id);
    }
}
