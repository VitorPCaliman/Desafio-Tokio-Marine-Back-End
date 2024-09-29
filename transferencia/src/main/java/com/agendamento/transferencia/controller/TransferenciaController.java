package com.agendamento.transferencia.controller;

import com.agendamento.transferencia.model.Transferencia;
import com.agendamento.transferencia.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private  final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
        Transferencia novaTransferencia = transferenciaService.agendarTransferencia(transferencia);
        return ResponseEntity.ok(novaTransferencia);
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> listarAgendamentos() {
        List<Transferencia> agendamentos = transferenciaService.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }
}
