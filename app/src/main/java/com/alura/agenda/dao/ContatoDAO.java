package com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alura.agenda.entities.Contato;

import java.util.ArrayList;
import java.util.List;

import static com.alura.agenda.dao.SQLiteHelper.FIELD_ID;
import static com.alura.agenda.dao.SQLiteHelper.TABLE_CONTATO;

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

        if (contato.getId() != null) {
            sqLiteDatabase.update(TABLE_CONTATO, values, "id = ?", new String[]{contato.getId().toString()});
        } else {
            sqLiteDatabase.insert(TABLE_CONTATO, null, values);
        }

        sqLiteDatabase.close();
    }

    public List<Contato> buscarContatos() {
        String sql = "SELECT * FROM " + TABLE_CONTATO + ";";
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

        sqLiteDatabase.close();

        return contatos;

    }

    public void removerContato(Contato contato) {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_CONTATO, FIELD_ID + " = ?", new String[]{contato.getId().toString()});

        sqLiteDatabase.close();
    }
}
