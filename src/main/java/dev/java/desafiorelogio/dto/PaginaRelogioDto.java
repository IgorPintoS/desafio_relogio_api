package dev.java.desafiorelogio.dto;

import java.util.List;

public record PaginaRelogioDto(
        List<RelogioDto> itens,
        long total
) {
}
