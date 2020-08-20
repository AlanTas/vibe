package com.vibe.bibliotecagothan.modelos;

import com.google.gson.annotations.SerializedName;

public class Paginador {
    @SerializedName("pagina")
    private int pagina;
    @SerializedName("totalPaginas")
    private int totalPaginas;

    public Paginador(int pagina, int totalPaginas) {
        this.pagina = pagina;
        this.totalPaginas = totalPaginas;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
}
