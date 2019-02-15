package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.domain.app.minhagelada.conexaoDB.ConexaoDB;
import br.com.domain.app.minhagelada.entidades.Filtro;


public class FiltroDao {

    private ConexaoDB conexaoDB;
    private SQLiteDatabase banco;

    public FiltroDao(Context context){
        this.conexaoDB = new ConexaoDB(context);
        this.banco = conexaoDB.getWritableDatabase();
    }

    public boolean insert(Filtro filtro){
        ContentValues values = new ContentValues();

        values.put("descricao",filtro.getDescricao());

        long result = this.banco.insert("filtro",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public List<Filtro> selectAll(){
        List<Filtro> filtros = new ArrayList<>();
        String[] colunas ={"id","descricao"};
        Cursor cursor = banco.query("filtro",colunas,null,null,
                null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            Filtro filtro = new Filtro();
            filtro.setId(id);
            filtro.setDescricao(descricao);
            filtros.add(filtro);
        }
        cursor.close();
        return filtros;
    }
}
