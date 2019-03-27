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

    public List<ItemCesta> selectAll(int cesta_id){
        List<ItemCesta> itens = new ArrayList<>();
        ItemCesta itemCesta;
        Cursor cursor = banco.rawQuery("select \n" +
                "  item.id, \n" +
                "  item.valor, \n" +
                "  estabelecimento.descricao as descEstabelecimento,\n" +
                "  marca.descricao as descMarca,\n" +
                "  unidade.descricao as descUnidade,\n" +
                "  filtro.descricao as descFiltro\n" +
                "from item_cesta as item\n" +
                "inner join estabelecimento on estabelecimento.id = item.estabelecimento_id\n" +
                "inner join marca on marca.id = item.marca_id\n" +
                "inner join unidade on unidade.id = item.unidade_id\n" +
                "inner join filtro on filtro.id = item.filtro_id\n" +
                "where item.cesta_id = ?", new String[] { cesta_id + "" });
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            float valor = cursor.getFloat(cursor.getColumnIndex("valor"));
            String descEstabelecimento = cursor.
                    getString(cursor.getColumnIndex("descEstabelecimento"));
            String descMarca = cursor.getString(cursor.getColumnIndex("descMarca"));
            String descUnidade = cursor.getString(cursor.getColumnIndex("descUnidade"));
            String descFiltro = cursor.getString(cursor.getColumnIndex("descFiltro"));

            itemCesta = new ItemCesta(descEstabelecimento,descMarca,descUnidade,descFiltro,id,valor);

            itens.add(itemCesta);
        }
        cursor.close();
        return itens;

    }
}
