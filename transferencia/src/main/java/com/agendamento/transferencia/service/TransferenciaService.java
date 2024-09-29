package com.agendamento.transferencia.service;

import com.agendamento.transferencia.model.Transferencia;
import com.agendamento.transferencia.repository.TransferenciaRepository;

import java.time.LocalDate;
import java.util.List;

public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        transferencia.setDataAgendamento(LocalDate.now());
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listarAgendamentos() {
        return transferenciaRepository.findAll();
    }

}
