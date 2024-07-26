package com.IMD.GCM.Banco.service;

import com.IMD.GCM.Banco.dao.ContaRepository;
import com.IMD.GCM.Banco.entity.Conta;
import com.IMD.GCM.Banco.entity.ContaBonus;
import com.IMD.GCM.Banco.entity.ContaPoupanca;
import com.IMD.GCM.Banco.entity.ContaSimples;
import com.IMD.GCM.Banco.exceptions.ContaNaoEncontradaException;
import com.IMD.GCM.Banco.exceptions.SaldoInsuficienteException;
import com.IMD.GCM.Banco.exceptions.ValorNegativoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    @Transactional
    public Conta cadastrarConta(String numero, double saldo, String tipoConta) {
        Conta conta;
        if ("BONUS".equalsIgnoreCase(tipoConta)) {
            conta = new ContaBonus();
            saldo = 0;
        } else if ("POUPANCA".equalsIgnoreCase(tipoConta)) {
            conta = new ContaPoupanca();
        } else {
            conta = new ContaSimples();
        }

        conta.setNumero(numero);
        conta.setSaldo(saldo);

        return contaRepository.save(conta);
    }

    @Override
    public Conta consultarConta(String numero) {
        Conta conta = contaRepository.findByNumero(numero);
        if (conta == null) {
            throw new ContaNaoEncontradaException();
        }
        return conta;
    }

    @Override
    public double consultarSaldo(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);
        return conta.getSaldo();
    }

    @Override
    @Transactional
    public Conta credito(Long id, double valor) {
        if (valor < 0) throw new ValorNegativoException();
        Conta conta = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);
        conta.setSaldo(conta.getSaldo() + valor);
        if (conta instanceof ContaBonus) {
            ((ContaBonus) conta).setPontuacao(((ContaBonus) conta).getPontuacao() + (int) (valor / 100));
        }
        return contaRepository.save(conta);
    }

    @Override
    @Transactional
    public Conta debito(Long id, double valor) {
        if (valor < 0) throw new ValorNegativoException();
        Conta conta = contaRepository.findById(id).orElseThrow(ContaNaoEncontradaException::new);
        if (conta.getSaldo() - valor < -2000) throw new SaldoInsuficienteException();
        conta.setSaldo(conta.getSaldo() - valor);
        return contaRepository.save(conta);
    }

    @Override
    @Transactional
    public void transferencia(Long fromId, Long toId, double valor) {
        if (valor < 0) throw new ValorNegativoException();
        Conta contaOrigem = contaRepository.findById(fromId).orElseThrow(ContaNaoEncontradaException::new);
        Conta contaDestino = contaRepository.findById(toId).orElseThrow(ContaNaoEncontradaException::new);
        debito(fromId, valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        if (contaDestino instanceof ContaBonus) {
            ((ContaBonus) contaDestino).setPontuacao(((ContaBonus) contaDestino).getPontuacao() + (int) (valor / 150));
        }
        contaRepository.save(contaDestino);
    }

    @Override
    @Transactional
    public void renderJuros(double taxa) {
        List<Conta> contas = contaRepository.findAll();
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                double novoSaldo = conta.getSaldo() * (1 + taxa / 100);
                conta.setSaldo(novoSaldo);
                contaRepository.save(conta);
            }
        }
    }
}