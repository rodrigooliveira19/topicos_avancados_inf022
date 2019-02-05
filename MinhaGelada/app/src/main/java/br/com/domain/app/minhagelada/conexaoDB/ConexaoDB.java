package br.com.domain.app.minhagelada.conexaoDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoDB extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "minhagelada";
    private static final int VERSAO = 1;

    public ConexaoDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTabelaEslabelecimento ="create table estabelecimento(\n" +
                "    \n" +
                "   id integer not null primary key autoincrement, \n" +
                "   descricao varchar(30) not null, \n" +
                "   localizacao varchar(30)  \n" +
                "\n" +
                ")";

        db.execSQL(sqlTabelaEslabelecimento);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}