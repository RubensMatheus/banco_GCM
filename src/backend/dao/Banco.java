package src.backend.dao;

import src.backend.model.Conta;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<Integer, Conta> contas;

    public Banco() {
        contas = new HashMap<>();
    }

    public void cadastrarConta(int numero, double saldoInicial) {
        if (!contas.containsKey(numero)) {
            contas.put(numero, new Conta(numero, saldoInicial));
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("Já existe uma conta com esse número.");
        }
    }

    public void consultarSaldo(int numero) {
        Conta conta = contas.get(numero);
        if (conta != null) {
            System.out.println("Saldo da conta " + numero + ": " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void creditar(int numero, double valor) {
        if (valor >= 0) {
            Conta conta = contas.get(numero);
            if (conta != null) {
                conta.creditar(valor);
                System.out.println("Crédito de " + valor + " realizado com sucesso na conta " + numero);
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("O valor a ser creditado não pode ser negativo.");
        }
    }

    public void debitar(int numero, double valor) {
        if (valor >= 0) {
            Conta conta = contas.get(numero);
            if (conta != null) {
                if (conta.getSaldo() >= valor) {
                    conta.debitar(valor);
                    System.out.println("Débito de " + valor + " realizado com sucesso na conta " + numero);
                }else {
                    System.out.println("Saldo insuficiente na conta de origem.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("O valor a ser debitado não pode ser negativo.");
        }
    }

    public void transferencia(int origem, int destino, double valor) {
        if (valor >= 0) {
            Conta contaOrigem = contas.get(origem);
            Conta contaDestino = contas.get(destino);

            if (contaOrigem != null && contaDestino != null) {
                if (contaOrigem.getSaldo() >= valor) {
                    contaOrigem.debitar(valor);
                    contaDestino.creditar(valor);
                    System.out.println("Transferência de " + valor + " da conta " + origem + " para a conta " + destino + " realizada com sucesso.");
                }else {
                    System.out.println("Saldo insuficiente na conta de origem.");
                }
            } else {
                System.out.println("Conta de origem ou destino não encontrada.");
            }
        } else {
            System.out.println("O valor a ser transferido não pode ser negativo.");

        }
    }
}
