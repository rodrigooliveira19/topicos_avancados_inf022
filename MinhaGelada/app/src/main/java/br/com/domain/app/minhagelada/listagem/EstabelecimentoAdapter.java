package br.com.domain.app.minhagelada.listagem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoHolder>{

    private final List<Estabelecimento> estabelecimentos;

    public EstabelecimentoAdapter(List<Estabelecimento> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    @Override
    public EstabelecimentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EstabelecimentoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(EstabelecimentoHolder holder, int position) {
        holder.descricao.setText(estabelecimentos.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return estabelecimentos != null ? estabelecimentos.size() : 0;
    }
}
