package backend;

import java.util.Scanner;

public class ConsultaSaldo {
    public static void consultarSaldo(Conta conta, Scanner scanner) {

        System.out.println("---- CONSULTA DE SALDO ----");
        System.out.print("Digite o número da conta que quer verificar: ");
        
        String numeroConta = scanner.nextLine();

        if (numeroConta.equals(conta.getNumeroConta())) {
            System.out.println("- VERIFICANDO SALDO DA CONTA -");
            System.out.println("O saldo da conta " + conta.getNumeroConta() + " é: " + conta.getSaldo());
        } else {
            System.out.println("Conta não cadastrada!");
        } 
    }
    
}
