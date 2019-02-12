package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
