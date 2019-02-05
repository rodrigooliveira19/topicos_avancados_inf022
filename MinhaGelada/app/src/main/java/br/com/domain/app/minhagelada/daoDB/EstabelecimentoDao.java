package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.domain.app.minhagelada.conexaoDB.ConexaoDB;
import br.com.domain.app.minhagelada.entidades.Estabelecimento;

public class EstabelecimentoDao {

    private ConexaoDB conexaoDB;
    private SQLiteDatabase banco;

    public EstabelecimentoDao(Context context){
        this.conexaoDB = new ConexaoDB(context);
        this.banco = conexaoDB.getWritableDatabase();
    }

    public boolean insert(Estabelecimento estabelecimento){
        ContentValues values = new ContentValues();

        values.put("descricao",estabelecimento.getDescricao());
        values.put("localizacao",estabelecimento.getLocalizacao());

        long result = this.banco.insert("estabelecimento",null,values);

        if(result == -1)
            return false;
        else
            return true;
    }
}
