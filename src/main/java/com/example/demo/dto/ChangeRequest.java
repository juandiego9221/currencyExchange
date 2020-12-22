package com.example.demo.dto;

import java.math.BigDecimal;

public class ChangeRequest {
    private BigDecimal amount;
    private String oCurrency;
    private String dCurrency;

    public String getoCurrency() {
        return oCurrency;
    }

    public String getdCurrency() {
        return dCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setoCurrency(String oCurrency) {
        this.oCurrency = oCurrency;
    }

    public void setdCurrency(String dCurrency) {
        this.dCurrency = dCurrency;
    }
}
