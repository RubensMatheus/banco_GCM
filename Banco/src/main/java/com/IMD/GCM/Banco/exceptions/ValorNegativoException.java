package com.IMD.GCM.Banco.exceptions;

public class ValorNegativoException extends BancoException{
    public ValorNegativoException() {
        super("Valor não pode ser negativo");
    }
}
