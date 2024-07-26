package com.IMD.GCM.Banco.service;

import com.IMD.GCM.Banco.entity.Conta;

public interface ContaService {

    Conta cadastrarConta(String numero, double saldo, String tipoConta);

    Conta consultarConta(String numero);

    double consultarSaldo(Long id);

    Conta credito(Long id, double valor);

    Conta debito(Long id, double valor);

    void transferencia(Long fromId, Long toId, double valor);

    void renderJuros(double taxa);
}