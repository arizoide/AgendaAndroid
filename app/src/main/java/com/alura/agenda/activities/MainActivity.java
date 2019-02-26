package com.alura.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alura.agenda.R;
import com.alura.agenda.dao.ContatoDAO;
import com.alura.agenda.entities.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaContatos;

    private final String MENU_REMOVER = "Remover";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = findViewById(R.id.listaAlunos);

        //Recupera o botão novo aluno para abrir a tela de cadastro
        Button novoAluno = findViewById(R.id.novoAluno);

        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contato = new Intent(MainActivity.this, ContatoActivity.class);
                startActivity(contato);
            }
        });

        //menu de contexto para o delete
        registerForContextMenu(listaContatos);


        //Clique do item da lista
        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemListView, int position, long id) {
                //Recupera o aluno que foi clicado.
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);

                Intent contatoIntent = new Intent(MainActivity.this, ContatoActivity.class);
                contatoIntent.putExtra("contato", contato);
                startActivity(contatoIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        carregarContatos();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem remover = menu.add(MENU_REMOVER);

        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);

                ContatoDAO dao = new ContatoDAO(MainActivity.this);
                dao.removerContato(contato);

                carregarContatos();

                Toast.makeText(MainActivity.this, "Contato removido!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    //******************************************************************************
    //************************* METODOS PRIVADOS ***********************************
    //******************************************************************************

    private void carregarContatos() {
        ContatoDAO contatoDAO = new ContatoDAO(this);

        //Cria lista de aluno
        List<Contato> contatos = contatoDAO.buscarContatos();

        //Para conseguirmos popular um componente da tela, que é um XML, precisamos criar um adaptador para isso
        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        //Coloca no componente da tela
        listaContatos.setAdapter(adapter);
    }
}