package br.com.domain.app.minhagelada.listagem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.domain.app.minhagelada.R;

public class ItemHolder extends RecyclerView.ViewHolder{

    public TextView descricao;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public ItemHolder(View itemView) {
        super(itemView);
        descricao =  itemView.findViewById(R.id.descricao);
        btnEditar =  itemView.findViewById(R.id.btnEdit);
        btnExcluir = itemView.findViewById(R.id.btnDelete);
    }
}
