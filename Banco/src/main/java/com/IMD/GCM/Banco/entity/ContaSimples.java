package com.IMD.GCM.Banco.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SIMPLES")
public class ContaSimples extends Conta {
    public ContaSimples() { }

}
