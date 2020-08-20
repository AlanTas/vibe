package com.vibe.bibliotecagothan.apis;
import com.vibe.bibliotecagothan.modelos.Livro;
import com.vibe.bibliotecagothan.modelos.LivroLista;
import com.vibe.bibliotecagothan.modelos.Pagina;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LibraryService {

    @GET("acervo/{pag}")
    Call<Pagina> obterAcervo(@Path("pag") int pag);

    @GET("livro/{id}/info")
    Call<Livro> obterInfoLivro(@Path("id") int id);

    @POST("livro/emprestar")
    Call<String> emprestarLivro(@Field("id") int id);
}
