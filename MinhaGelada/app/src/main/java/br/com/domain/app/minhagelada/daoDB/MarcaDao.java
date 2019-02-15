package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.app.minhagelada.conexaoDB.ConexaoDB;
import br.com.domain.app.minhagelada.entidades.Marca;

public class MarcaDao {

    private ConexaoDB conexaoDB;
    private SQLiteDatabase banco;

    public MarcaDao(Context context){
        this.conexaoDB = new ConexaoDB(context);
        this.banco = conexaoDB.getWritableDatabase();
    }

    public boolean insert(Marca marca){
        ContentValues values = new ContentValues();

        values.put("descricao",marca.getDescricao());

        long result = this.banco.insert("marca",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public List<Marca> selectAll(){
        List<Marca> marcas = new ArrayList<>();
        String[] colunas ={"id","descricao"};
        Cursor cursor = banco.query("marca",colunas,null,null,
                null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            Marca marca = new Marca();
            marca.setId(id);
            marca.setDescricao(descricao);
            marcas.add(marca);
        }
        cursor.close();
        return marcas;
    }
}
