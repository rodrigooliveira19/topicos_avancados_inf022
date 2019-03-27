package br.com.domain.app.minhagelada.listagem;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.domain.app.minhagelada.ItemCestaActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Cesta;

public class CestaAdapter extends RecyclerView.Adapter<CestaHolder> {

    private final List<Cesta> cestas;
    private Cesta cesta = null;

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

        this.cesta = cestas.get(position);
        holder.btnAddItem.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, ItemCestaActivity.class);
                intent.putExtra("idCesta", cesta.getId());
                //intent.putExtra("idCesta", cesta.);
                activity.startActivity(intent);

            }
        });

        holder.btnListItem.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, ListaDeItemActivity.class);
                intent.putExtra("idCesta", cesta.getId());
                //intent.putExtra("idCesta", cesta.);
                activity.startActivity(intent);

            }
        });





    }

    @Override
    public int getItemCount() {
        return cestas != null ? cestas.size() : 0;
    }

    //Método que permmite obiter uma activity a partir de uma view.
    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }


}
