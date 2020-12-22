package com.example.demo.dto;

import java.math.BigDecimal;

public class ChangeResponse {
    private BigDecimal amount;
    private BigDecimal resultAmount;
    private String oCurrency;
    private String dCurrency;
    private BigDecimal changeType;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getResultAmount() {
        return resultAmount;
    }

    public void setResultAmount(BigDecimal resultAmount) {
        this.resultAmount = resultAmount;
    }

    public String getoCurrency() {
        return oCurrency;
    }

    public void setoCurrency(String oCurrency) {
        this.oCurrency = oCurrency;
    }

    public String getdCurrency() {
        return dCurrency;
    }

    public void setdCurrency(String dCurrency) {
        this.dCurrency = dCurrency;
    }

    public BigDecimal getChangeType() {
        return changeType;
    }

    public void setChangeType(BigDecimal changeType) {
        this.changeType = changeType;
    }

}
