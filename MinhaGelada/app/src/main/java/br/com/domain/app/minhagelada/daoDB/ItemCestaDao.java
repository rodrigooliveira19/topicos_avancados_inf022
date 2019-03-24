package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.domain.app.minhagelada.conexaoDB.ConexaoDB;
import br.com.domain.app.minhagelada.entidades.ItemCesta;

public class ItemCestaDao {

    private ConexaoDB conexaoDB;
    private SQLiteDatabase banco;

    public ItemCestaDao(Context context){
        this.conexaoDB = new ConexaoDB(context);
        this.banco = conexaoDB.getWritableDatabase();
    }


    public boolean insert(ItemCesta itemCesta){
        ContentValues values = new ContentValues();

        values.put("cesta_id",itemCesta.getIdCesta());
        values.put("estabelecimento_id",itemCesta.getEstabelecimento().getId());
        values.put("marca_id",itemCesta.getMarca().getId());
        values.put("unidade_id",itemCesta.getUnidade().getId());
        values.put("filtro_id",itemCesta.getFiltro().getId());
        values.put("valor",itemCesta.getValor());

        long result = this.banco.insert("item_cesta",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }
}
