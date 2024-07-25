package com.IMD.GCM.Banco.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Bonus")
public class ContaBonus extends Conta{
    private int pontuacao = 10;

    public ContaBonus() { }

    public ContaBonus(String numero, Double saldo) {
        super(numero, saldo);
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
