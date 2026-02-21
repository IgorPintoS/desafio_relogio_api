package dev.java.desafiorelogio.mapper;

import dev.java.desafiorelogio.dto.RelogioDto;
import dev.java.desafiorelogio.entity.Relogio;
import org.springframework.stereotype.Component;

@Component
public class RelogioMapper {

    public RelogioDto toDto(Relogio relogio) {
        if (relogio == null) return null;

        return RelogioDto.builder()
                .marca(relogio.getMarca())
                .modelo(relogio.getModelo())
                .referencia(relogio.getReferencia())
                .tipoMovimento(relogio.getTipoMovimento().getMovimento())
                .tipoVidro(relogio.getTipoVidro().getMaterial())
                .materialCaixa(relogio.getMaterialCaixa().getMaterial())
                .resistenciaAguaM(relogio.getResistenciaAguaM())
                .diametroMm(relogio.getDiametroMm())
                .lugToLugMm(relogio.getLugToLugMm())
                .espessuraMm(relogio.getEspessuraMm())
                .larguraLugMm(relogio.getLarguraLugMm())
                .precoEmCentavos(relogio.getPrecoEmCentavos())
                .urlImagem(relogio.getUrlImagem())
                .etiquetaResistenciaAguaM(etiquetaResistenciaAguaNormalizar(relogio.getResistenciaAguaM()))
                .pontuacaoColecionador(pontuacaoColecionar(relogio))
                .build();
    }

    public String etiquetaResistenciaAguaNormalizar(int etiquetaResistenciaAguaM) {
        if (etiquetaResistenciaAguaM < 50) return "respingos";
        if (etiquetaResistenciaAguaM < 100) return "uso_diario";
        if (etiquetaResistenciaAguaM < 200) return "natação";
        return "mergulho";
    }

    public int pontuacaoColecionar(Relogio relogio) {
        int pontos = 0;

        pontos += relogio.getTipoVidro().getPontuacao();
        pontos += relogio.getTipoMovimento().getPontuacao();
        pontos += relogio.getMaterialCaixa().getPontuacao();

        if(relogio.getResistenciaAguaM() >= 100) pontos += 15;
        if(relogio.getResistenciaAguaM() >= 200) pontos += 10;
        if(relogio.getDiametroMm() >= 38 && relogio.getDiametroMm() <= 42) pontos += 8;

        return pontos;
    }
}
