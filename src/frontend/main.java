package src.frontend;

import src.backend.dao.Banco;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Conta");
            System.out.println("2. Consultar Saldo");
            System.out.println("3. Crédito");
            System.out.println("4. Débito");
            System.out.println("5. Transferência");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite o número da conta: ");
                    int numeroConta = scanner.nextInt();
                    banco.cadastrarConta(numeroConta);
                    break;
                case 2:
                    System.out.print("Digite o número da conta: ");
                    int numConta = scanner.nextInt();
                    banco.consultarSaldo(numConta);
                    break;
                case 3:
                    System.out.print("Digite o número da conta: ");
                    int numC = scanner.nextInt();
                    System.out.print("Digite o valor a ser creditado: ");
                    double valorCredito = scanner.nextDouble();
                    banco.creditar(numC, valorCredito);
                    break;
                case 4:
                    System.out.print("Digite o número da conta: ");
                    int numD = scanner.nextInt();
                    System.out.print("Digite o valor a ser debitado: ");
                    double valorDebito = scanner.nextDouble();
                    banco.debitar(numD, valorDebito);
                    break;
                case 5:
                    System.out.print("Digite o número da conta de origem: ");
                    int origem = scanner.nextInt();
                    System.out.print("Digite o número da conta de destino: ");
                    int destino = scanner.nextInt();
                    System.out.print("Digite o valor a ser transferido: ");
                    double valorTransferencia = scanner.nextDouble();
                    banco.transferencia(origem, destino, valorTransferencia);
                    break;
                case 0:
                    System.out.println("Encerrando app ...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");

            }

        } while (opcao != 0);

        scanner.close();

    }

}
