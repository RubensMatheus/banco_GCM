package backend;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- CADASTRAMENTO DE CONTA ----");
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        Conta conta = new Conta(numeroConta, 0.0);
        
        System.out.println("- CONTA CADASTRADA COM SUCESSO! -");
        System.out.println("Número da conta: " + conta.getNumeroConta());
        System.out.println("Saldo: " + conta.getSaldo());

        scanner.close();

    }
}
