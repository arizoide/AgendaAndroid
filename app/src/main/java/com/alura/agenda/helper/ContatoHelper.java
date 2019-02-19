package com.alura.agenda.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import com.alura.agenda.R;
import com.alura.agenda.activities.ContatoActivity;
import com.alura.agenda.entities.Contato;

public class ContatoHelper {

    EditText nome;
    EditText telefone;
    RatingBar pontuacao;

    public ContatoHelper(ContatoActivity activity) {
        nome = activity.findViewById(R.id.nomeEditText);
        telefone = activity.findViewById(R.id.telefoneEditText);
        pontuacao = activity.findViewById(R.id.pontuacaoRatingBar);
    }

    public Contato getContato(){
        return new Contato(nome.getText().toString(), telefone.getText().toString(), Double.valueOf(pontuacao.getProgress()));
    }
}
