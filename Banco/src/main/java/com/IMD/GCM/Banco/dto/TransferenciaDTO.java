package com.IMD.GCM.Banco.dto;

public class TransferenciaDTO {
    private Long from;
    private Long to;
    private double amount;

    // Getters e setters

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
