package br.com.domain.app.minhagelada.listagem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Marca;

public class MarcaAdapter extends RecyclerView.Adapter<MarcaHolder>{

    private final List<Marca> marcas;

    public MarcaAdapter(List<Marca> marcas) {
        this.marcas = marcas;
    }

    @Override
    public MarcaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarcaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(MarcaHolder holder, int position) {
        holder.descricao.setText(marcas.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return marcas != null ? marcas.size() : 0;
    }
}
