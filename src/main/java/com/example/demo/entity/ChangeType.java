package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "AMOUNTTYPE")
@Entity
public class ChangeType {

    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "TYPE")
    private double type;

    public ChangeType() {
    }

    public ChangeType(int id, String currency, double type) {
        this.id = id;
        this.currency = currency;
        this.type = type;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }
}
