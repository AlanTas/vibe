package com.vibe.bibliotecagothan.view.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vibe.bibliotecagothan.R;
import com.vibe.bibliotecagothan.modelos.LivroLista;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AcervoRecyclerViewAdapter extends RecyclerView.Adapter<AcervoRecyclerViewAdapter.MyViewHolder> {

    private List<LivroLista> acervoList;
    private OnClickBookItemListener listener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View v;
        public TextView tituloTextView;
        public TextView AutorTextView;
        public TextView DisponivelTextView;

        public MyViewHolder(View v) {
            super(v);
            this.v = v;
            tituloTextView = v.findViewById(R.id.tvTituloLivro);
            AutorTextView = v.findViewById(R.id.tvAutorLivro);
            DisponivelTextView = v.findViewById(R.id.tvDisponivel);
        }
    }

    public AcervoRecyclerViewAdapter(List<LivroLista> acervoList, OnClickBookItemListener listener) {
        this.acervoList = acervoList;
        this.listener = listener;
    }

    public void updateDataSet() {
        notifyDataSetChanged();
    }

    public void cleanDataSet() {
        acervoList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AcervoRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acervo_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AcervoRecyclerViewAdapter.MyViewHolder holder, final int position) {

        LivroLista l = acervoList.get(position);
        holder.AutorTextView.setText(l.getAutor());
        holder.tituloTextView.setText(l.getTitulo());
        holder.DisponivelTextView.setText(l.isDisponivel() ? "Disponivel":"Indisponivel");
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(acervoList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return acervoList.size();
    }

    public interface OnClickBookItemListener {
         void onClick(LivroLista livro);
    }
}
