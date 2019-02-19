package com.alura.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    // VERSAO DO BANCO
    private static final Integer DB_VERSION = 1;

    // Nome do banco de dados
    private static final String DB_NAME = "agenda.alura.db";

    // Nome dos campos das tabelas
    // Tabela CONTATO
    static final String FIELD_NAME = "NOME";
    static final String FIELD_PHONE = "TELEFONE";
    static final String FIELD_ID = "ID";
    static final String FIELD_PONTUACAO = "PONTUACAO";

    // Nomes das tabelas
    // CONTATO
    static final String TABLE_CONTATO = "CONTATO";

    // Comandos para criacao de tabelas
    // CONTATO
    private final String DB_CREATE = "CREATE TABLE " + TABLE_CONTATO +
            "(" +
            FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_NAME + " TEXT NOT NULL, " +
            FIELD_PHONE + " TEXT, " +
            FIELD_PONTUACAO + " REAL" +
            ");";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
