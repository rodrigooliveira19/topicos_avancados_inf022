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

        String sqlTabelaMarca ="create table marca(\n" +
                "    \n" +
                "   id integer not null primary key autoincrement, \n" +
                "   descricao varchar(20) not null \n" +
                "\n" +
                ")";


        String sqlTabelaUnidade ="create table unidade(\n" +
                "    \n" +
                "   id integer not null primary key autoincrement, \n" +
                "   descricao varchar(7) not null \n" +
                "\n" +
                ")";

        String sqlTabelaFiltro ="create table filtro(\n" +
                "    \n" +
                "   id integer not null primary key autoincrement, \n" +
                "   descricao varchar(7) not null \n" +
                "\n" +
                ")";

        String sqlTabelaCesta ="create table cesta(\n" +
                "    \n" +
                "   id integer not null primary key autoincrement, \n" +
                "   descricao varchar(30) not null \n" +
                "\n" +
                ")";

        String sqlTabelaItemCesta ="create table item_cesta(\n" +
                "    \n" +
                "   id integer not null primary key autoincrement, \n" +
                "   cesta_id integer not null, \n" +
                "   estabelecimento_desc varchar(30) not null, \n" +
                "   marca_desc varchar(20) not null, \n" +
                "   unidade_desc varchar(7) not null, \n" +
                "   filtro_desc varchar(7) not null, \n" +
                "   valor numeric(10,2) not null, \n" +
                "   constraint fk_cesta_i_cesta " +
                "       foreign key(cesta_id) REFERENCES cesta(id) " +
                ")";



        db.execSQL(sqlTabelaEslabelecimento);
        db.execSQL(sqlTabelaMarca);
        db.execSQL(sqlTabelaUnidade);
        db.execSQL(sqlTabelaFiltro);
        db.execSQL(sqlTabelaCesta);
        db.execSQL(sqlTabelaItemCesta);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
