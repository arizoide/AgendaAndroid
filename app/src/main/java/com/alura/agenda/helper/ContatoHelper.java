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

    Contato contato;

    public ContatoHelper(ContatoActivity activity) {
        nome = activity.findViewById(R.id.nomeEditText);
        telefone = activity.findViewById(R.id.telefoneEditText);
        pontuacao = activity.findViewById(R.id.pontuacaoRatingBar);

        contato = new Contato();
    }

    public Contato getContato(){
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        contato.setPontuacao(Double.valueOf(pontuacao.getProgress()));

        return contato;
    }

    public void preencheFormulario(Contato contato) {
        this.contato = contato;

        nome.setText(contato.getNome());
        telefone.setText(contato.getTelefone());
        pontuacao.setProgress(contato.getPontuacao().intValue());
    }
}
