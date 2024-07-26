package com.IMD.GCM.Banco.controller;

import com.IMD.GCM.Banco.dto.TransferenciaDTO;
import com.IMD.GCM.Banco.entity.Conta;
import com.IMD.GCM.Banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banco/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/")
    public ResponseEntity<Conta> cadastrarConta(@RequestParam String numero, @RequestParam(required = false) Double saldo, @RequestParam String tipoConta) {
        if (saldo == null && ("SIMPLES".equalsIgnoreCase(tipoConta) || "POUPANCA".equalsIgnoreCase(tipoConta))) {
            return ResponseEntity.badRequest().body(null);  // Saldo é obrigatório para Conta Simples e Conta Poupança
        }
        if (saldo == null) {
            saldo = 0.0;
        }
        Conta conta = contaService.cadastrarConta(numero, saldo, tipoConta);
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/")
    public ResponseEntity<Conta> consultarConta(@RequestParam String numero) {
        Conta conta = contaService.consultarConta(numero);
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Double> consultarSaldo(@PathVariable Long id) {
        double saldo = contaService.consultarSaldo(id);
        return ResponseEntity.ok(saldo);
    }

    @PutMapping("/{id}/credito")
    public ResponseEntity<Conta> credito(@PathVariable Long id, @RequestParam double valor) {
        Conta conta = contaService.credito(id, valor);
        return ResponseEntity.ok(conta);
    }

    @PutMapping("/{id}/debito")
    public ResponseEntity<Conta> debito(@PathVariable Long id, @RequestParam double valor) {
        Conta conta = contaService.debito(id, valor);
        return ResponseEntity.ok(conta);
    }

    @PutMapping("/transferencia")
    public ResponseEntity<Void> transferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        contaService.transferencia(transferenciaDTO.getFrom(), transferenciaDTO.getTo(), transferenciaDTO.getAmount());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rendimento")
    public ResponseEntity<Void> renderJuros(@RequestParam double taxa) {
        contaService.renderJuros(taxa);
        return ResponseEntity.ok().build();
    }
}