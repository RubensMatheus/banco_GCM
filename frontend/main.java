package frontend;

import backend.dao.Banco;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        
        /*Scanner scanner = new Scanner(System.in);
        Conta conta = CadastraConta.cadastrandoConta(scanner);

        ConsultaSaldo.consultarSaldo(conta, scanner);

        CadastraCredito.cadastrarCredito(conta, scanner);

        scanner.close();*/

        Banco banco = new Banco();
        banco.cadastrarConta(123);
        banco.consultarSaldo(123);

        banco.creditar(123, 100);
        banco.consultarSaldo(123);

        banco.debitar(123, 50);
        banco.consultarSaldo(123);

    }

}
