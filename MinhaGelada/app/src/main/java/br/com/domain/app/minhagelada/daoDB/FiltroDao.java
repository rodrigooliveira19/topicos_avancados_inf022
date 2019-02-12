package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
