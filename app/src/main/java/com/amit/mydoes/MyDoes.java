package com.amit.mydoes;

public class MyDoes {
    String titledoes;
    String datedoes;
    String descdoes;

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    String keydoes;

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }


    public MyDoes() {
    }

    public String getDatedoes() {
        return datedoes;
    }

    public void setDatedoes(String datedoes) {
        this.datedoes = datedoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }


    public MyDoes(String datedoes, String descdoes, String titledoes,String keydoes) {
        this.datedoes = datedoes;
        this.descdoes = descdoes;
        this.titledoes = titledoes;
        this.keydoes=keydoes;
    }
}
