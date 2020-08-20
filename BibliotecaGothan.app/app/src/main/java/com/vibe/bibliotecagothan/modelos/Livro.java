package com.vibe.bibliotecagothan.modelos;

import com.google.gson.annotations.SerializedName;

public class Livro {

    @SerializedName("descricao")
    private String descricao;
    @SerializedName("ano")
    private int ano;
    @SerializedName("isbn")
    private String iSBN;
    @SerializedName("edicao")
    private int edicao;
    @SerializedName("paginas")
    private int paginas;

    public Livro(String descricao, int ano, String iSBN, int edicao, int paginas) {
        this.descricao = descricao;
        this.ano = ano;
        this.iSBN = iSBN;
        this.edicao = edicao;
        this.paginas = paginas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
