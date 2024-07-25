package com.IMD.GCM.Banco.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {
    public ContaPoupanca() {}

    public ContaPoupanca(String numero, double saldo) {
        super(numero, saldo);
    }
}
