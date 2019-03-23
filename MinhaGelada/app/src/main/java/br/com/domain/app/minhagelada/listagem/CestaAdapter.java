package br.com.domain.app.minhagelada.listagem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Cesta;

public class CestaAdapter extends RecyclerView.Adapter<CestaHolder> {

    private final List<Cesta> cestas;

    public CestaAdapter( List<Cesta> cestas){
        this.cestas = cestas;
    }


    @Override
    public CestaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CestaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_cesta, parent, false));
    }

    @Override
    public void onBindViewHolder(CestaHolder holder, int position) {
        holder.descricao.setText(cestas.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return cestas != null ? cestas.size() : 0;
    }
}
