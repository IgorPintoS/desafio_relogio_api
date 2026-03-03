package dev.java.desafiorelogio.config;

import dev.java.desafiorelogio.entity.Relogio;
import dev.java.desafiorelogio.entity.enums.MaterialCaixa;
import dev.java.desafiorelogio.entity.enums.TipoMovimento;
import dev.java.desafiorelogio.entity.enums.TipoVidro;
import dev.java.desafiorelogio.repository.RelogioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class SeedDadosInicial {

    private final RelogioRepository relogioRepository;

    @Bean
    CommandLineRunner seedRelogios() {
        return args -> {
            if (relogioRepository.count() > 0) return;

            Instant agora = Instant.now();

            List<Relogio> relogios = List.of(
                    Relogio.builder()
                        .id(UUID.randomUUID())
                        .marca("Casio")
                        .modelo("F-91WD")
                        .referencia("1234")
                        .tipoMovimento(TipoMovimento.QUARTZ)
                        .materialCaixa(MaterialCaixa.RESINA)
                        .tipoVidro(TipoVidro.MINERAL)
                        .resistenciaAguaM(30)
                        .diametroMm(35)
                        .lugToLugMm(38)
                        .espessuraMm(9)
                        .larguraLugMm(18)
                        .precoEmCentavos(12990)
                        .urlImagem("123")
                        .criadoEm(agora.minusSeconds(50000))
                        .build(),
                    Relogio.builder()
                            .id(UUID.randomUUID())
                            .marca("Seiko")
                            .modelo("Diver 200m")
                            .referencia("890")
                            .tipoMovimento(TipoMovimento.AUTOMATICO)
                            .materialCaixa(MaterialCaixa.ACO)
                            .tipoVidro(TipoVidro.MINERAL)
                            .resistenciaAguaM(42)
                            .diametroMm(46)
                            .lugToLugMm(50)
                            .espessuraMm(13)
                            .larguraLugMm(22)
                            .precoEmCentavos(159990)
                            .urlImagem("472")
                            .criadoEm(agora.minusSeconds(30000))
                            .build(),
                    Relogio.builder()
                            .id(UUID.randomUUID())
                            .marca("Citizen")
                            .modelo("Eco-Diver Field")
                            .referencia("8857")
                            .tipoMovimento(TipoMovimento.QUARTZ)
                            .materialCaixa(MaterialCaixa.TITANIO)
                            .tipoVidro(TipoVidro.SAFIRA)
                            .resistenciaAguaM(60)
                            .diametroMm(34)
                            .lugToLugMm(25)
                            .espessuraMm(13)
                            .larguraLugMm(22)
                            .precoEmCentavos(259990)
                            .urlImagem("4232")
                            .criadoEm(agora.minusSeconds(30000))
                            .build()
            );
            relogioRepository.saveAll(relogios);
        };
    }
}
