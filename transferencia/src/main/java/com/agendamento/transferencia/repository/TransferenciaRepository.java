package com.agendamento.transferencia.repository;

import com.agendamento.transferencia.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
