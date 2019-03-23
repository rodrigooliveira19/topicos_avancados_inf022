package br.com.domain.app.minhagelada.listagem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.domain.app.minhagelada.R;

/*
Esta Classe extends de ViewHolder para poder
pegar os dados de item_lista_cesta.xml.
*/
public class CestaHolder extends  RecyclerView.ViewHolder{

    public TextView descricao;
    public ImageButton btnAddItem;
    public ImageButton btnDeleteCesta;

    public CestaHolder(View itemView){
        super(itemView);
        descricao = itemView.findViewById(R.id.descricaoCesta);
        btnAddItem = itemView.findViewById(R.id.btnAddItem);
        btnDeleteCesta = itemView.findViewById(R.id.btnDeleteCesta);
    }
}
