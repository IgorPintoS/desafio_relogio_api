package dev.java.desafiorelogio.service;

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
}
