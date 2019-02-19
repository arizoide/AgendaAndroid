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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContatoDAO contatoDAO = new ContatoDAO(this);

        //Recupera a referencia da lista da tela
        ListView listaAlunos = findViewById(R.id.listaAlunos);

        //Cria lista de aluno
        List<Contato> contatos = contatoDAO.buscarContatos();

        //Para conseguirmos popular um componente da tela, que é um XML, precisamos criar um adaptador para isso
        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        //Coloca no componente da tela
        listaAlunos.setAdapter(adapter);

        //Recupera o botão novo aluno para abrir a tela de cadastro
        Button novoAluno = findViewById(R.id.novoAluno);

        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contato = new Intent(MainActivity.this, ContatoActivity.class);
                startActivity(contato);
                finish();
            }
        });

    }
}
