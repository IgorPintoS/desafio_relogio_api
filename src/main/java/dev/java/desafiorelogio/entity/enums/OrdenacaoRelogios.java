package dev.java.desafiorelogio.entity.enums;

public enum OrdenacaoRelogios {
    MAIS_RECENTES("newest"),
    PRECO_CRESC("price_asc"),
    PRECO_DESC("price_desc"),
    DIAMETRO_CRESC("diameter_asc"),
    RESISTENCIA_DESC("wr_desc");

    private final String ordenacao;

    OrdenacaoRelogios(String ordenacao) {
        this.ordenacao = ordenacao;
    }

    public String getOrdenacao() {
        return this.ordenacao;
    }

    public static OrdenacaoRelogios fromApi(String ordenacao) {
        if (ordenacao == null || ordenacao.isBlank()) {
            return MAIS_RECENTES;
        }

        for (OrdenacaoRelogios ordenacaoRelogio : OrdenacaoRelogios.values()) {
            if (ordenacaoRelogio.ordenacao.equalsIgnoreCase(ordenacao)) {
                return ordenacaoRelogio;
            }
        }
        return MAIS_RECENTES;
    }
}
