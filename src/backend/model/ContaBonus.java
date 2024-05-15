package src.backend.model;

public class ContaBonus extends Conta{

    private int pontuacao;

    public ContaBonus(int numero) {
        super(numero);
        this.pontuacao = 10;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    @Override
    public void creditar(double valor) {
        super.creditar(valor);
        pontuacao += (int) (valor / 100.0);
    }

    public void creditarTransferencia(double valor) {
        super.creditar(valor);
        pontuacao += (int) (valor / 150.0);
    }
}
