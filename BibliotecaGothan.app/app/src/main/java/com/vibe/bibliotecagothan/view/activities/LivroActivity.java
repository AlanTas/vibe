package com.vibe.bibliotecagothan.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vibe.bibliotecagothan.Constantes;
import com.vibe.bibliotecagothan.R;
import com.vibe.bibliotecagothan.apis.ClienteApi;
import com.vibe.bibliotecagothan.apis.LibraryService;
import com.vibe.bibliotecagothan.apis.LibraryServiceMock;
import com.vibe.bibliotecagothan.modelos.Livro;
import com.vibe.bibliotecagothan.modelos.LivroLista;
import com.vibe.bibliotecagothan.modelos.Pagina;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivroActivity extends AppCompatActivity {
    LibraryServiceMock client = new LibraryServiceMock();
    private TextView tituloLivroTv;
    private TextView autorLivroTv;
    private TextView descricaoLivroTv;
    private TextView isbnLivroTv;
    private TextView editoraLivroTv;
    private TextView anoLivroTv;
    private TextView edicaoLivroTv;
    private TextView paginasLivroTv;
    private Livro livro;
    private LivroLista livroLista;
    private ProgressDialog progressDoalog;
    private LibraryService service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_livro);
        setupViews();
        setupClient();
        livroLista = new LivroLista();
        livroLista.setTitulo(getIntent().getStringExtra(Constantes.TITULO_LIVRO));
        livroLista.setAutor(getIntent().getStringExtra(Constantes.AUTOR_LIVRO));
        livroLista.setId(getIntent().getIntExtra(Constantes.ID_LIVRO,0));
        getLivro(livroLista.getId());

    }
    private void setupClient()
    {
        service = ClienteApi.getRetrofitInstance().create(LibraryService.class);
    }


    private void setupViews() {
       tituloLivroTv = findViewById(R.id.tituloLivro);
       autorLivroTv = findViewById(R.id.autorLivro);
       descricaoLivroTv = findViewById(R.id.livroDescricao);
       isbnLivroTv = findViewById(R.id.livroISBN);
       editoraLivroTv = findViewById(R.id.livroEditora);
       anoLivroTv = findViewById(R.id.livroAno);
       edicaoLivroTv = findViewById(R.id.livroEdicao);
       paginasLivroTv = findViewById(R.id.livroPaginas);
    }

    public void onClickEmprestar(View v) {

    }

    private void getLivro(int id) {
        progressDoalog = new ProgressDialog(LivroActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Call<Livro> call = service.obterInfoLivro(id);
        call.enqueue(new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                progressDoalog.dismiss();
                if (response==null)
                    return;
                livro = response.body();
                tituloLivroTv.setText(livroLista.getTitulo());
                autorLivroTv.setText(livroLista.getAutor());
                descricaoLivroTv.setText(livro.getDescricao());
                isbnLivroTv.setText(livro.getiSBN());
                edicaoLivroTv.setText(livro.getEdicao()+"");
                paginasLivroTv.setText(livro.getPaginas()+"");
            }

            @Override
            public void onFailure(Call<Livro> call, Throwable t) {
                progressDoalog.dismiss();
                t.printStackTrace();
                Toast.makeText(LivroActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

      //editoraLivroTv.setText(livro.get);
    }
}
