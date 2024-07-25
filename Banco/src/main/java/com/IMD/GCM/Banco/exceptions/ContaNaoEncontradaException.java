package com.IMD.GCM.Banco.exceptions;

public class ContaNaoEncontradaException extends BancoException {
    public ContaNaoEncontradaException() {
        super("Conta não encontrada");
    }
}

