package dev.java.desafiorelogio.entity;

import dev.java.desafiorelogio.entity.enums.MaterialCaixa;
import dev.java.desafiorelogio.entity.enums.TipoMovimento;
import dev.java.desafiorelogio.entity.enums.TipoVidro;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="relogio", indexes = {
        @Index(name = "idx_relgio_marca", columnList = "marca"),
        @Index(name = "idx_relogio_criado_em", columnList = "criadoEm"),
        @Index(name = "idx_relogio_preco", columnList = "precoEmCentavos")
})
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Relogio {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, length = 60)
    private String marca;

    @Column(nullable = false, length = 150)
    private String modelo;

    @Column(nullable = false, length = 60)
    private String referencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoMovimento tipoMovimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoVidro tipoVidro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MaterialCaixa materialCaixa;

    @Column(nullable = false)
    private int resistenciaAguaM;

    @Column(nullable = false)
    private int diamentroMn;

    @Column(nullable = false)
    private int lugToLugMn;

    @Column(nullable = false)
    private int espessuraMn;

    @Column(nullable = false)
    private int larguraLugMn;

    @Column(nullable = false)
    private long precoEmCentavos;

    @Column(nullable = false, length = 600)
    private String urlImagem;

    @Column(nullable = false)
    private Instant criadoEm;

    @PrePersist
    void prePersist() {
        if(id == null) id = UUID.randomUUID();

        if(criadoEm == null) criadoEm = Instant.now();

        normalizar();
    }

    @PreUpdate
    void preUpdate() {
        normalizar();
    }

    private void normalizar() {
        if (marca != null) marca = marca.trim();
        if (modelo != null) modelo = modelo.trim();
        if (referencia != null) referencia = referencia.trim();
        if (urlImagem != null) urlImagem = urlImagem.trim();
    }
}
