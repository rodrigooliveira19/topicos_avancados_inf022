package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.app.minhagelada.conexaoDB.ConexaoDB;
import br.com.domain.app.minhagelada.entidades.Unidade;

public class UnidadeDao {

    private ConexaoDB conexaoDB;
    private SQLiteDatabase banco;

    public UnidadeDao(Context context){
        this.conexaoDB = new ConexaoDB(context);
        this.banco = conexaoDB.getWritableDatabase();
    }

    public boolean insert(Unidade marca){
        ContentValues values = new ContentValues();

        values.put("descricao",marca.getDescricao());

        long result = this.banco.insert("unidade",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public List<Unidade> selectAll(){
        List<Unidade> unidades = new ArrayList<>();
        String[] colunas ={"id","descricao"};
        Cursor cursor = banco.query("unidade",colunas,null,null,
                null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            Unidade unidade = new Unidade();
            unidade.setId(id);
            unidade.setDescricao(descricao);
            unidades.add(unidade);
        }
        cursor.close();
        return unidades;
    }
}
