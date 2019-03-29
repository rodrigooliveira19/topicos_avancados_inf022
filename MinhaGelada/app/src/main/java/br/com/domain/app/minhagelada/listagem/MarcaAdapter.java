package br.com.domain.app.minhagelada.listagem;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.domain.app.minhagelada.MarcaActivity;
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
    public void onBindViewHolder(MarcaHolder holder, final int position) {
        holder.descricao.setText(marcas.get(position).getDescricao());

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, MarcaActivity.class);
                intent.putExtra("id", marcas.get(position).getId());
                intent.putExtra("descricao", marcas.get(position).getDescricao());
                intent.putExtra("DELETE",false);

                activity.startActivity(intent);

            }
        });


        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, MarcaActivity.class);
                intent.putExtra("id", marcas.get(position).getId());
                intent.putExtra("descricao", marcas.get(position).getDescricao());
                intent.putExtra("DELETE",true);

                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return marcas != null ? marcas.size() : 0;
    }

    //Método que permite obiter uma activity a partir de uma view.
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
