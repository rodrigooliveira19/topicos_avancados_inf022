package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
        values.put("estabelecimento_desc",itemCesta.getEstabelecimento().getDescricao());
        values.put("marca_desc",itemCesta.getMarca().getDescricao());
        values.put("unidade_desc",itemCesta.getUnidade().getDescricao());
        values.put("filtro_desc",itemCesta.getFiltro().getDescricao());
        values.put("valor",itemCesta.getValor());

        long result = this.banco.insert("item_cesta",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public List<ItemCesta> selectAll(int cesta_id){
        List<ItemCesta> itens = new ArrayList<>();
        ItemCesta itemCesta;
        Cursor cursor = banco.rawQuery("select \n" +
                "  item.id, \n" +
                "  item.valor, \n" +
                "  item.estabelecimento_desc as estabelecimento_desc,\n" +
                "  item.marca_desc as marca_desc,\n" +
                "  item.unidade_desc as unidade_desc,\n" +
                "  item.filtro_desc as filtro_desc\n" +
                "from item_cesta as item\n" +
                "where item.cesta_id = ?", new String[] { cesta_id + "" });
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            float valor = cursor.getFloat(cursor.getColumnIndex("valor"));
            String descEstabelecimento = cursor.
                    getString(cursor.getColumnIndex("estabelecimento_desc"));
            String descMarca = cursor.getString(cursor.getColumnIndex("marca_desc"));
            String descUnidade = cursor.getString(cursor.getColumnIndex("unidade_desc"));
            String descFiltro = cursor.getString(cursor.getColumnIndex("filtro_desc"));

            itemCesta = new ItemCesta(descEstabelecimento,descMarca,descUnidade,descFiltro,id,valor);

            itens.add(itemCesta);
        }
        cursor.close();
        return itens;

    }
}
