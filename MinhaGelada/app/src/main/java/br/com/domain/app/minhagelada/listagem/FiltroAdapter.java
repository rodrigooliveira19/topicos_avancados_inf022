package br.com.domain.app.minhagelada.listagem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Filtro;

public class FiltroAdapter extends RecyclerView.Adapter<FiltroHolder>{

    private final List<Filtro> filtros;

    public FiltroAdapter(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    @Override
    public FiltroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FiltroHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(FiltroHolder holder, int position) {
        holder.descricao.setText(filtros.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return filtros != null ? filtros.size() : 0;
    }
}
