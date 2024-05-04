package backend;

import java.util.Scanner;

public class CadastraCredito {

    public static void cadastrarCredito(Conta conta, Scanner scanner) {
        System.out.println("---- CRÉDITO NA CONTA ----");
        System.out.print("Número da conta que deseja depositar: ");
        String numeroConta = scanner.nextLine();

        if (numeroConta.equals(conta.getNumeroConta())) {
            System.out.print("Digite o valor que deseja depositar: ");
            double valorCredito = scanner.nextDouble();
            scanner.nextLine();

            double novoSaldo = conta.getSaldo() + valorCredito;
            conta.setSaldo(novoSaldo);

            System.out.println("Depósito realizado com sucesso!");
            System.out.println("A conta " + conta.getNumeroConta() + " agora possui RS " + conta.getSaldo());
        } else {
            System.out.println("Conta não cadastrada!");
        }
    }
    
}
