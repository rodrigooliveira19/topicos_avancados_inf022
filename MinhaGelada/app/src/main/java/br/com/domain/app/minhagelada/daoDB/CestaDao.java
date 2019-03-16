package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.domain.app.minhagelada.conexaoDB.ConexaoDB;
import br.com.domain.app.minhagelada.entidades.Cesta;

public class CestaDao {

    private ConexaoDB conexaoDB;
    private SQLiteDatabase banco;

    public CestaDao(Context context){
        this.conexaoDB = new ConexaoDB(context);
        this.banco = conexaoDB.getWritableDatabase();
    }

    public boolean insert(Cesta cesta){
        ContentValues values = new ContentValues();

        values.put("descricao",cesta.getDescricao());

        long result = this.banco.insert("cesta",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }
}
