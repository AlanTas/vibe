package com.vibe.bibliotecagothan.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pagina {
    @SerializedName("livros")
    private List<LivroLista> livros;
    @SerializedName("paginador")
    private Paginador pagina;

    public Pagina(List<LivroLista> livros, Paginador pagina) {
        this.livros = livros;
        this.pagina = pagina;
    }

    public List<LivroLista> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroLista> livros) {
        this.livros = livros;
    }

    public Paginador getPagina() {
        return pagina;
    }

    public void setPagina(Paginador pagina) {
        this.pagina = pagina;
    }
}
