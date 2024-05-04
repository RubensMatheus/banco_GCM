package backend;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Conta conta = CadastraConta.cadastrandoConta(scanner);

        ConsultaSaldo.consultarSaldo(conta, scanner);

        CadastraCredito.cadastrarCredito(conta, scanner);

        scanner.close();
    }

}
