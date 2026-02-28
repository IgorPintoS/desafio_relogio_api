package dev.java.desafiorelogio.entity.enums;

import java.util.Arrays;

public enum TipoMovimento {
    QUARTZ("quartz", 0),
    AUTOMATICO("automatic", 20),
    MANUAL("manual", 0);

    private final String movimento;
    private final int pontuacao;

    TipoMovimento(String movimento, int pontuacao) {
        this.movimento = movimento;
        this.pontuacao = pontuacao;
    }

    public String getMovimento() {
        return this.movimento;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public static TipoMovimento fromApi(String movimento) {
        if (movimento == null || movimento.isBlank()) {
            return null;
        }

        for (TipoMovimento tipoMovimento : TipoMovimento.values()) {
            if (tipoMovimento.getMovimento().equalsIgnoreCase(movimento)) {
                return tipoMovimento;
            }
        }
        return null;
    }
}
