package dev.java.desafiorelogio.entity.enuns;

public enum MaterialCaixa {
    ACO, TITANIO, RESINA, BRONZE, CERAMICA;

    public static MaterialCaixa fromApi(String valor) {
        if (valor == null || valor.isBlank()) return null;

        return switch (valor) {
            case "steel" -> ACO;

            case "titanium" -> TITANIO;

            case "resin" -> RESINA;

            case "bronze" -> BRONZE;

            case "ceramic" -> CERAMICA;

            default -> throw new IllegalArgumentException("Tipo de material da caixa inválido:" + valor);
        };
    }

    public String toApi() {
        return switch (this) {
            case ACO -> "steel";

            case TITANIO -> "titanium";

            case RESINA -> "resin";

            case BRONZE -> "bronze";

            case CERAMICA -> "ceramic";
        };
    }
}
