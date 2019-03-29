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

import br.com.domain.app.minhagelada.FiltroActivity;
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
    public void onBindViewHolder(FiltroHolder holder, final int position) {
        holder.descricao.setText(filtros.get(position).getDescricao());

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, FiltroActivity.class);
                intent.putExtra("id", filtros.get(position).getId());
                intent.putExtra("descricao", filtros.get(position).getDescricao());
                intent.putExtra("DELETE", false);

                activity.startActivity(intent);

            }
        });


        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, FiltroActivity.class);
                intent.putExtra("id", filtros.get(position).getId());
                intent.putExtra("descricao", filtros.get(position).getDescricao());
                intent.putExtra("DELETE", true);

                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return filtros != null ? filtros.size() : 0;
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
