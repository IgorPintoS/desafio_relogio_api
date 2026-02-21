package dev.java.desafiorelogio.entity.enums;

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
}
