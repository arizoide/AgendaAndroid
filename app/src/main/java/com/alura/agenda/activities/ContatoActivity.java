package com.alura.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.alura.agenda.R;
import com.alura.agenda.dao.ContatoDAO;
import com.alura.agenda.entities.Contato;
import com.alura.agenda.helper.ContatoHelper;

public class ContatoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.contato_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ContatoHelper contatoHelper = new ContatoHelper(this);
        ContatoDAO contatoDAO = new ContatoDAO(this);
        switch (item.getItemId()){
            case R.id.salvar_contato_menu_item:
                Contato contato = contatoHelper.getContato();
                contatoDAO.save(contato);
                Toast.makeText(ContatoActivity.this, "Contato Salvo!", Toast.LENGTH_LONG).show();

                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
