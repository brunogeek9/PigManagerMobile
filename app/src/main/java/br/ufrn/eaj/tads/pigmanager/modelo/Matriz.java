package br.ufrn.eaj.tads.pigmanager.modelo;

import java.util.Date;

import br.ufrn.eaj.tads.pigmanager.modelo.enums.EnumEstagio;

public class Matriz {
    private Integer id;

    private double identificador;

    private String raca;

    private double peso;

    private EnumEstagio estagio;

    private Date dataNascimento;

    private boolean ativo = true;

    private String arquivo;

    public Matriz() {
    }

    public Matriz(double identificador, String raca, double peso, Date dataNascimento,EnumEstagio estagio, String arquivo) {
        this.identificador = identificador;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.arquivo = arquivo;
        this.estagio = estagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getIdentificador() {
        return identificador;
    }

    public void setIdentificador(double identificador) {
        this.identificador = identificador;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public EnumEstagio getEstagio() {
        return estagio;
    }

    public void setEstagio(EnumEstagio estagio) {
        this.estagio = estagio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Matriz{" +
                "identificador=" + identificador +
                ", raca='" + raca + '\'' +
                ", peso=" + peso +
                ", estagio=" + estagio +
                ", ativo=" + ativo +
                '}';
    }
}
