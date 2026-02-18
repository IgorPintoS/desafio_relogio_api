package dev.java.desafiorelogio.dto;

import lombok.Builder;

@Builder
public record RelogioDto(
        String marca,
        String modelo,
        String referencia,
        String materialCaixa,
        String tipoMovimento,
        String tipoVidro,
        int resistenciaAguaM,
        int diametroMm,
        int lugToLugMm,
        int espessuraMm,
        int larguraLugMm,
        long precoEmCentavos,
        String urlImagem,
        String etiquetaResistenciaAgua,
        int pontuacaoColecionador
) {
}
