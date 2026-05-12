package data;

import java.io.Serializable;

public class Produce implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;

    private String name;

    private String masse_type;
    private double pay_masse;


    public Produce(int ID, String name, double pay_masse, String masse_type) {
        this.ID = ID;
        this.name = name;
        this.masse_type = masse_type;
        this.pay_masse = pay_masse;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasse_type() {
        return masse_type;
    }

    public void setMasse_type(String masse_type) {
        this.masse_type = masse_type;
    }

    public double getPay_masse() {
        return pay_masse;
    }

    public void setPay_masse(double pay_masse) {
        this.pay_masse = pay_masse;
    }
}
