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
import android.widget.Toast;

import java.util.List;

import br.com.domain.app.minhagelada.EstabelecimentoActivity;
import br.com.domain.app.minhagelada.R;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoHolder>{

    private final List<Estabelecimento> estabelecimentos;
    //private Estabelecimento estabelecimento = null;

    public EstabelecimentoAdapter(List<Estabelecimento> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    @Override
    public EstabelecimentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EstabelecimentoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(EstabelecimentoHolder holder, final int position) {
        holder.descricao.setText(estabelecimentos.get(position).getDescricao());

        //this.estabelecimento = estabelecimentos.get(position);
        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, EstabelecimentoActivity.class);
                intent.putExtra("id", estabelecimentos.get(position).getId());
                intent.putExtra("descricao", estabelecimentos.get(position).getDescricao());



                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return estabelecimentos != null ? estabelecimentos.size() : 0;
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
