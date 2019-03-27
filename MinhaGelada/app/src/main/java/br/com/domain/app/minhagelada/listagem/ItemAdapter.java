package br.com.domain.app.minhagelada.listagem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.ItemCesta;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{

    private final List<ItemCesta> itemCesta;
    //private ItemCesta item = null;

    public ItemAdapter(List<ItemCesta> itemCesta) {
        this.itemCesta = itemCesta;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.descricao.setText(itemCesta.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return itemCesta != null ? itemCesta.size() : 0;
    }
}
