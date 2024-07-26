package com.IMD.GCM.Banco.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {
    public ContaPoupanca() {}

}
