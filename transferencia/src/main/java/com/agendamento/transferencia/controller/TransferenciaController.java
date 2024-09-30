package com.agendamento.transferencia.controller;

import com.agendamento.transferencia.model.Transferencia;
import com.agendamento.transferencia.service.TransferenciaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Page<Transferencia>> listarAgendamentos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Transferencia> transferencias = transferenciaService.listarAgendamentos(pageable);
        return ResponseEntity.ok(transferencias);
    }
}
