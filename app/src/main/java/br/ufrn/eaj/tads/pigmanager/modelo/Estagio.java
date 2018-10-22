package br.ufrn.eaj.tads.pigmanager.modelo;

import br.ufrn.eaj.tads.pigmanager.modelo.enums.EnumEstagio;

public class Estagio {

    private Integer id;

    private Matriz matriz;

    private EnumEstagio estagio;

    public Estagio() {
    }

    public Estagio(Matriz matriz, String estagio) {
        this.matriz = matriz;
        this.estagio = EnumEstagio.valueOf(estagio);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public EnumEstagio getEstagio() {
        return estagio;
    }

    public void setEstagio(EnumEstagio estagio) {
        this.estagio = estagio;
    }

}
