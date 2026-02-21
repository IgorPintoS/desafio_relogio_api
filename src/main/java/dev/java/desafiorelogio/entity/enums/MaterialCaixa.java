package dev.java.desafiorelogio.entity.enums;

public enum MaterialCaixa {
    ACO("steel", 10),
    TITANIO("titanium", 12),
    RESINA("resin", 0),
    BRONZE("bronze", 0),
    CERAMICA("ceramic", 0);

    private final String material;
    private final int pontuacao;

    MaterialCaixa(String material, int pontuacao) {
        this.material = material;
        this.pontuacao = pontuacao;
    }

    public String getMaterial() {
        return this.material;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }
}
