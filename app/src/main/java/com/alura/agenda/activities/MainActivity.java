package com.alura.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alura.agenda.R;
import com.alura.agenda.dao.ContatoDAO;
import com.alura.agenda.entities.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaContatos;

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


    }

    @Override
    protected void onResume() {
        carregarContatos();
        super.onResume();
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