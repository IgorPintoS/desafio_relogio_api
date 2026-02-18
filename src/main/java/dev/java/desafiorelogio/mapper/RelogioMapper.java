package dev.java.desafiorelogio.mapper;

import dev.java.desafiorelogio.dto.RelogioDto;
import dev.java.desafiorelogio.entity.Relogio;
import org.springframework.stereotype.Component;

import static dev.java.desafiorelogio.entity.enums.MaterialCaixa.pontuacaoColecionadorMaterialCaixa;
import static dev.java.desafiorelogio.entity.enums.TipoMovimento.pontuacaoColecionadorTipoMovimento;
import static dev.java.desafiorelogio.entity.enums.TipoVidro.pontuacaoColecionadorTipoVidro;

@Component
public class RelogioMapper {

    public RelogioDto toDto(Relogio relogio) {
        return RelogioDto.builder()
                .marca(relogio.getMarca())
                .modelo(relogio.getModelo())
                .referencia(relogio.getReferencia())
                .tipoMovimento(relogio.getTipoMovimento().toApi())
                .tipoVidro(relogio.getTipoVidro().toApi())
                .materialCaixa(relogio.getMaterialCaixa().toApi())
                .resistenciaAguaM(relogio.getResistenciaAguaM())
                .diametroMm(relogio.getDiamentroMm())
                .lugToLugMm(relogio.getLugToLugMm())
                .espessuraMm(relogio.getEspessuraMm())
                .larguraLugMm(relogio.getLarguraLugMm())
                .precoEmCentavos(relogio.getPrecoEmCentavos())
                .urlImagem(relogio.getUrlImagem())
                .build();
    }

    public int pontuacaoColecionar(Relogio relogio) {
        int pontos = 0;

        pontos += pontuacaoColecionadorTipoVidro(relogio.getTipoVidro());
        pontos += pontuacaoColecionadorTipoMovimento(relogio.getTipoMovimento());
        pontos += pontuacaoColecionadorMaterialCaixa(relogio.getMaterialCaixa());

        if(relogio.getResistenciaAguaM() >= 100) pontos += 15;
        if(relogio.getResistenciaAguaM() >= 200) pontos += 10;
        if(relogio.getDiamentroMm() >= 38 && relogio.getDiamentroMm() <= 42) pontos += 8;

        return pontos;
    }
}
