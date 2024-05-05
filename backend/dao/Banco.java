package backend.dao;

import backend.model.Conta;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<Integer, Conta> contas;

    public Banco() {
        contas = new HashMap<>();
    }

    public void cadastrarConta(int numero) {
        if (!contas.containsKey(numero)) {
            contas.put(numero, new Conta(numero));
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
        Conta conta = contas.get(numero);
        if (conta != null) {
            conta.creditar(valor);
            System.out.println("Crédito de " + valor + " realizado com sucesso na conta " + numero);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void debitar(int numero, double valor) {
        Conta conta = contas.get(numero);
        if (conta != null) {
            conta.debitar(valor);
            System.out.println("Débito de " + valor + " realizado com sucesso na conta " + numero);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void transferencia(int origem, int destino, double valor) {
        Conta contaOrigem = contas.get(origem);
        Conta contaDestino = contas.get(destino);

        if (contaOrigem != null && contaDestino != null) {
            contaOrigem.debitar(valor);
            contaDestino.creditar(valor);
            System.out.println("Transferência de " + valor + " da conta " + origem + " para a conta " + destino + " realizada com sucesso.");
        } else {
            System.out.println("Conta de origem ou destino não encontrada.");
        }
    }
}
