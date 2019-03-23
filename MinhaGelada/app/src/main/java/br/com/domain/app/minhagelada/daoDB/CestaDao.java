package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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


    public List<Cesta> selectAll(){
        List<Cesta> cestas = new ArrayList<>();
        String[] colunas ={"id","descricao"};
        Cursor cursor = banco.query("cesta",colunas,null,null,
                null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            Cesta cesta = new Cesta();
            cesta.setId(id);
            cesta.setDescricao(descricao);
            cestas.add(cesta);
        }
        cursor.close();
        return cestas;
    }
}
