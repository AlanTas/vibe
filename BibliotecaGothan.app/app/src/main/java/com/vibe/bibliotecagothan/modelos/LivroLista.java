package com.vibe.bibliotecagothan.modelos;

import com.google.gson.annotations.SerializedName;

import androidx.recyclerview.widget.RecyclerView;

public class LivroLista {
    @SerializedName("id")
    private int id;
    @SerializedName("autor")
    private String autor;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("disponivel")
    private boolean disponivel;

    public LivroLista() {

    }

    public LivroLista(int id, String autor, String titulo, boolean disponivel) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
