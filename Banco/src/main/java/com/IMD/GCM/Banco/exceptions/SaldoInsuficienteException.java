package com.IMD.GCM.Banco.exceptions;

public class SaldoInsuficienteException extends BancoException{
    public SaldoInsuficienteException() {
        super("Saldo Insuficiente");
    }
}
