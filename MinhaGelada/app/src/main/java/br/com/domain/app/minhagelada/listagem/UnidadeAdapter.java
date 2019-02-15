package br.com.domain.app.minhagelada.listagem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Unidade;

public class UnidadeAdapter extends RecyclerView.Adapter<UnidadeHolder>{

    private final List<Unidade> unidades;

    public UnidadeAdapter(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    @Override
    public UnidadeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UnidadeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(UnidadeHolder holder, int position) {
        holder.descricao.setText(unidades.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return unidades != null ? unidades.size() : 0;
    }
}
