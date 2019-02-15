package br.com.domain.app.minhagelada.daoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<Estabelecimento> selectAll(){
        List<Estabelecimento> estabelecimentos = new ArrayList<>();
        String[] colunas ={"id","descricao","localizacao"};
        Cursor cursor = banco.query("estabelecimento",colunas,null,null,
                                    null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            String localizacao = cursor.getString(cursor.getColumnIndex("localizacao"));

            Estabelecimento estabelecimento = new Estabelecimento();
            estabelecimento.setId(id);
            estabelecimento.setDescricao(descricao);
            estabelecimento.setLocalizacao(localizacao);
            estabelecimentos.add(estabelecimento);
        }
        cursor.close();
        return estabelecimentos;
    }
}
