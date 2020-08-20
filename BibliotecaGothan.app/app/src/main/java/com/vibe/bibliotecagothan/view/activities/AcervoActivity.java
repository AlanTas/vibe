package com.vibe.bibliotecagothan.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vibe.bibliotecagothan.Constantes;
import com.vibe.bibliotecagothan.R;
import com.vibe.bibliotecagothan.apis.ClienteApi;
import com.vibe.bibliotecagothan.apis.LibraryService;
import com.vibe.bibliotecagothan.apis.LibraryServiceMock;
import com.vibe.bibliotecagothan.modelos.LivroLista;
import com.vibe.bibliotecagothan.modelos.Pagina;
import com.vibe.bibliotecagothan.view.adapters.AcervoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcervoActivity extends AppCompatActivity implements AcervoRecyclerViewAdapter.OnClickBookItemListener {
    //views fields
    private RecyclerView mRecyclerViewAcervo;
    private int pagina = 0;
    private int totalPaginas;
    private LibraryServiceMock client = new LibraryServiceMock();
    LibraryService service;

    //fields
    private List<LivroLista> acervoLista = new ArrayList<>();
    private AcervoRecyclerViewAdapter adapter;
    private ProgressDialog progressDoalog;
    private LinearLayoutManager layoutManager;
    private TextView editServerTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_acervo);
        editServerTv = findViewById(R.id.editServidor);
        editServerTv.setText(Constantes.Base_url);
        getViews();
        setupRecyclerView();
        setupClient();
        getAcervo();
        setupClient();

    }

    private void getViews() {
        mRecyclerViewAcervo = findViewById(R.id.recyclerViewAcervo);
    }

    private void setupClient()
    {
        service = ClienteApi.getRetrofitInstance().create(LibraryService.class);
    }

    private void setupRecyclerView() {
        mRecyclerViewAcervo.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerViewAcervo.setLayoutManager(layoutManager);
        adapter = new AcervoRecyclerViewAdapter(acervoLista,this);
        mRecyclerViewAcervo.setAdapter(adapter);
        mRecyclerViewAcervo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager.findLastVisibleItemPosition() == acervoLista.size() -1){
                    if (pagina!=0&&pagina == totalPaginas)
                        return;
                    pagina ++;
                    getAcervo();
                }
            }
        });
    }

    private void getAcervo() {

        if (progressDoalog==null) {
            progressDoalog = new ProgressDialog(AcervoActivity.this);
            progressDoalog.setMessage("Loading....");
        }
        progressDoalog.show();

        Call<Pagina> call = service.obterAcervo(pagina);
        call.enqueue(new Callback<Pagina>() {
            @Override
            public void onResponse(Call<Pagina> call, Response<Pagina> response) {
                progressDoalog.dismiss();
                totalPaginas = response.body().getPagina().getTotalPaginas();
                acervoLista.addAll(response.body().getLivros());
                adapter.updateDataSet();
            }

            @Override
            public void onFailure(Call<Pagina> call, Throwable t) {
                progressDoalog.dismiss();
                t.printStackTrace();
                Toast.makeText(AcervoActivity.this, "Erro ao carregar dados!", Toast.LENGTH_SHORT).show();
            }
        });

    }




    @Override
    public void onClick(LivroLista livro) {
        Intent i = new Intent(this,LivroActivity.class);
        i.putExtra(Constantes.TITULO_LIVRO,livro.getTitulo());
        i.putExtra(Constantes.AUTOR_LIVRO,livro.getAutor());
        i.putExtra(Constantes.ID_LIVRO,livro.getId());
        startActivity(i);
    }

    public void onClickChangeServer(View view) {
        Constantes.Base_url = editServerTv.getText().toString();
        service = ClienteApi.getRetrofitInstance().create(LibraryService.class);
        pagina = 0;
        totalPaginas = 0;
        acervoLista.clear();
        adapter.cleanDataSet();
        getAcervo();
    }
}
