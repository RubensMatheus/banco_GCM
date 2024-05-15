package src.backend.dao;

import src.backend.model.Conta;
import src.backend.model.ContaBonus;
import src.backend.model.ContaPoupanca;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<Integer, Conta> contas;

    public Banco() {
        contas = new HashMap<>();
    }

    public void cadastrarConta(int numero, int tipo) {
        if (!contas.containsKey(numero)) {
            if (tipo == 1) {
                contas.put(numero, new ContaBonus(numero));
            } else {
                contas.put(numero, new Conta(numero));
            }
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("Já existe uma conta com esse número.");
        }
    }

    public void cadastrarContaPoupanca(int numero, double saldoInicial) {
        contas.put(numero, new ContaPoupanca(numero, saldoInicial));

    }

    public void consultarSaldo(int numero) {
        Conta conta = contas.get(numero);
        if (conta != null) {
            if (conta instanceof  ContaBonus) {
                System.out.println("Saldo da conta " + numero + ": " + conta.getSaldo() + " - Pontuação atual: " + ((ContaBonus) conta).getPontuacao());
            }else {
                System.out.println("Saldo da conta " + numero + ": " + conta.getSaldo());
            }
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
                if (!(conta instanceof ContaPoupanca) && conta.getSaldo() - valor >= -1000.00) {
                    conta.debitar(valor);
                    System.out.println("Débito de " + valor + " realizado com sucesso na conta " + numero);
                } else if (conta instanceof ContaPoupanca && conta.getSaldo() >= valor) {
                    conta.debitar(valor);
                    System.out.println("Débito de " + valor + " realizado com sucesso na conta " + numero);
                }
                else {
                    System.out.println("Saldo insuficiente para realizar o débito.");
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
                if (!(contaOrigem instanceof ContaPoupanca) && contaOrigem.getSaldo() - valor >= 1000.00) {
                    contaOrigem.debitar(valor);
                    contaDestino.creditar(valor);
                    System.out.println("Transferência de " + valor + " da conta " + origem + " para a conta " + destino + " realizada com sucesso.");
                } else if (contaOrigem instanceof ContaPoupanca && contaOrigem.getSaldo() >= valor) {
                    contaOrigem.debitar(valor);
                    contaDestino.creditar(valor);
                    System.out.println("Transferência de " + valor + " da conta " + origem + " para a conta " + destino + " realizada com sucesso.");
                }
                else {
                    System.out.println("Saldo insuficiente para realizar o débito.");
                }
            } else {
                System.out.println("Conta de origem ou destino não encontrada.");
            }
        } else {
            System.out.println("O valor a ser transferido não pode ser negativo.");

        }
    }

    public void renderJuros(double taxaPercentual) {
        for (Conta conta : contas.values()) {
            if (conta instanceof ContaPoupanca) {
                ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
                contaPoupanca.renderJuros(taxaPercentual);
            }
        }
        System.out.println("Juros renderizados!");
    }
}
