package src.backend.model;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(int numeroConta) {
        super(numeroConta);
    }

    public void renderJuros(double taxaPercentual) {
        double juros = getSaldo() * (taxaPercentual / 100);
        creditar(juros);
    }
}
