package com.agendamento.transferencia.service;

import com.agendamento.transferencia.model.Transferencia;
import com.agendamento.transferencia.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    private static final BigDecimal TAXA_FIXA_DIA_0 = BigDecimal.valueOf(3.00);
    private static final BigDecimal TAXA_PERCENTUAL_DIA_0 = BigDecimal.valueOf(2.5 / 100);
    private static final double TAXA_FIXA_1_A_10 = 12.00;
    private static final BigDecimal TAXA_PERCENTUAL_11_A_20 = BigDecimal.valueOf(8.2 / 100);
    private static final BigDecimal TAXA_PERCENTUAL_21_A_30 = BigDecimal.valueOf(6.9 / 100);
    private static final BigDecimal TAXA_PERCENTUAL_31_A_40 = BigDecimal.valueOf(4.7 / 100);
    private static final BigDecimal TAXA_PERCENTUAL_41_A_50 = BigDecimal.valueOf(1.7 / 100);

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        Double taxa = calcularTaxa(transferencia.getDataTransferencia(), transferencia.getValorTransferencia());
        transferencia.setTaxa(taxa);
        transferencia.setDataAgendamento(LocalDate.now());
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listarAgendamentos() {
        return transferenciaRepository.findAll();
    }

    public Double calcularTaxa(LocalDate dataTransferencia, BigDecimal valorTransferencia) {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), dataTransferencia);

        if (dias == 0) {
            return TAXA_FIXA_DIA_0
                    .add(TAXA_PERCENTUAL_DIA_0.multiply(valorTransferencia))
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }
        if (dias >= 1 && dias <= 10) {
            return TAXA_FIXA_1_A_10;
        }
        if (dias >= 11 && dias <= 20) {
            return TAXA_PERCENTUAL_11_A_20
                    .multiply(valorTransferencia)
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }
        if (dias >= 21 && dias <= 30) {
            return TAXA_PERCENTUAL_21_A_30
                    .multiply(valorTransferencia)
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }
        if (dias >= 31 && dias <= 40) {
            return TAXA_PERCENTUAL_31_A_40
                    .multiply(valorTransferencia)
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }
        if (dias >= 41 && dias <= 50) {
            return TAXA_PERCENTUAL_41_A_50
                    .multiply(valorTransferencia)
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .doubleValue();
        }
        return 0.00;
    }
}

