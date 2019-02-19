package com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alura.agenda.entities.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ContatoDAO(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void save(Contato contato) {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.FIELD_NAME, contato.getNome());
        values.put(SQLiteHelper.FIELD_PHONE, contato.getTelefone());
        values.put(SQLiteHelper.FIELD_PONTUACAO, contato.getPontuacao());

        sqLiteDatabase.insert(SQLiteHelper.TABLE_CONTATO, null, values);

        sqLiteDatabase.close();
    }

    public List<Contato> buscarContatos() {
        String sql = "SELECT * FROM CONTATO;";
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(sql, null);

        List<Contato> contatos = new ArrayList<Contato>();
        while (c.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(c.getInt(c.getColumnIndex(SQLiteHelper.FIELD_ID)));
            contato.setNome(c.getString(c.getColumnIndex(SQLiteHelper.FIELD_NAME)));
            contato.setTelefone(c.getString(c.getColumnIndex(SQLiteHelper.FIELD_PHONE)));
            contato.setPontuacao(c.getDouble(c.getColumnIndex(SQLiteHelper.FIELD_PONTUACAO)));

            contatos.add(contato);
        }
        c.close();

        return contatos;
    }
}
