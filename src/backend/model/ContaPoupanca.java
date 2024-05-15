package src.backend.model;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(int numeroConta, double saldoInicial) {
        super(numeroConta);
        creditar(saldoInicial);
    }


    public void renderJuros(double taxaPercentual) {
        double juros = getSaldo() * (taxaPercentual / 100);
        creditar(juros);
    }
}
