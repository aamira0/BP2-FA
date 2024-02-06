package com.example.energiebedrijf1.classes;

public class Gastarief {
    private double tariefGas;
    private String datumVanaf;
    private String datumTot;

    public Gastarief(double tariefGas, String datumVanaf, String datumTot) {
        this.tariefGas = tariefGas;
        this.datumVanaf = datumVanaf;
        this.datumTot = datumTot;
    }
    public double getTariefGas() {
        return tariefGas;
    }

    public void setTariefGas(double tariefGas) {
        this.tariefGas = tariefGas;
    }

    public String getDatumVanaf() {
        return datumVanaf;
    }

    public void setDatumVanaf(String datumVanaf) {
        this.datumVanaf = datumVanaf;
    }

    public String getDatumTot() {
        return datumTot;
    }

    public void setDatumTot(String datumTot) {
        this.datumTot = datumTot;
    }
}
