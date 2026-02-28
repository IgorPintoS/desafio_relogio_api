package dev.java.desafiorelogio.entity.enums;

public enum TipoVidro {
    MINERAL("mineral", 0),
    SAFIRA("sapphire", 25),
    ACRILICO("acrylic", 0);

    private final String material;
    private final int pontuacao;

    TipoVidro(String material, int pontuacao) {
        this.material = material;
        this.pontuacao = pontuacao;
    }

    public String getMaterial() {
        return this.material;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public static TipoVidro fromApi(String vidro) {
        if (vidro == null || vidro.isBlank()) {
            return null;
        }

        for (TipoVidro tipoVidro : TipoVidro.values()) {
            if (tipoVidro.getMaterial().equalsIgnoreCase(vidro)) {
                return tipoVidro;
            }
        }
        return null;
    }
}
