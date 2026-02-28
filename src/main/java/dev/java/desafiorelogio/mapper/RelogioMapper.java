package dev.java.desafiorelogio.mapper;

import dev.java.desafiorelogio.dto.AtualizarRelogioRequest;
import dev.java.desafiorelogio.dto.CriarRelogioRequest;
import dev.java.desafiorelogio.dto.RelogioDto;
import dev.java.desafiorelogio.entity.Relogio;
import dev.java.desafiorelogio.entity.enums.MaterialCaixa;
import dev.java.desafiorelogio.entity.enums.TipoMovimento;
import dev.java.desafiorelogio.entity.enums.TipoVidro;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

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

    public Relogio toCreateEntityFromDto(CriarRelogioRequest req) {
        if (req == null) return null;

        return Relogio.builder()
                .id(UUID.randomUUID())
                .marca(req.marca())
                .modelo(req.modelo())
                .referencia(req.referencia())
                .tipoMovimento(TipoMovimento.fromApi(req.tipoMovimento()))
                .materialCaixa(MaterialCaixa.fromApi(req.materialCaixa()))
                .tipoVidro(TipoVidro.fromApi(req.tipoVidro()))
                .resistenciaAguaM(req.resistenciaAguaM())
                .diametroMm(req.diametroMm())
                .lugToLugMm(req.lugToLugMm())
                .espessuraMm(req.espessuraMm())
                .larguraLugMm(req.larguraLugMm())
                .precoEmCentavos(req.precoEmCentavos())
                .urlImagem(req.urlImagem())
                .criadoEm(Instant.now())
                .build();
    }

    public void toUpdateEntityFromDto(Relogio relogio, AtualizarRelogioRequest req) {
        if (relogio == null) return;

        relogio.setMarca(req.marca());
        relogio.setModelo(req.modelo());
        relogio.setReferencia(req.referencia());
        relogio.setTipoMovimento(TipoMovimento.fromApi(req.tipoMovimento()));
        relogio.setMaterialCaixa(MaterialCaixa.fromApi(req.materialCaixa()));
        relogio.setTipoVidro(TipoVidro.fromApi(req.tipoVidro()));
        relogio.setResistenciaAguaM(req.resistenciaAguaM());
        relogio.setDiametroMm(req.diametroMm());
        relogio.setLugToLugMm(req.lugToLugMm());
        relogio.setEspessuraMm(req.espessuraMm());
        relogio.setLarguraLugMm(req.larguraLugMm());
        relogio.setPrecoEmCentavos(req.precoEmCentavos());
        relogio.setUrlImagem(req.urlImagem());
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
