package com.daviddev.mise_en_oeuvre_recherche_balise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity implements View.OnClickListener {

    TextView question, indice;
    int propositionsNbr, badReponsesNbr = 0;
    Button[] propositions_buttons;
    CGeocache geocache;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_question);

        intent = getIntent();
        int GéocacheID = intent.getIntExtra("GéocacheID", -1);

        DataHolder dh = new DataHolder();

        geocache = dh.getParcours().getGeocache(GéocacheID);
        String questionText = geocache.getQuestion();

        question = findViewById(R.id.question);
        question.setText(questionText);

        indice = findViewById(R.id.indice);

        Toast.makeText(this, "geocache number: " + GéocacheID + " :)", Toast.LENGTH_SHORT).show();

        LinearLayout question_linear_layout = findViewById(R.id.question_linear_layout);
        propositionsNbr = geocache.getPropositionsNbr();
        propositions_buttons = new Button[propositionsNbr];

        int i;
        for(i = 0 ; i < propositionsNbr; i++){

            propositions_buttons[i] = new Button(this);
            propositions_buttons[i].setText(geocache.getProposition(i));
            propositions_buttons[i].setOnClickListener(this);
            propositions_buttons[i].setId(i);
            question_linear_layout.addView(propositions_buttons[i]);

        }
    }

    @Override
    public void onClick(View view) {

        if (geocache.getAnswerNbr() ==  view.getId()) {
            Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, PostAnswerActivity.class);
            startActivity(intent);
        }
        else {

            Toast.makeText(this, "Mauvaisse réponse", Toast.LENGTH_SHORT).show();

            propositions_buttons[view.getId()].setBackgroundColor(getResources().getColor(R.color.colorAccent));

            if (badReponsesNbr < geocache.getPropositionsNbr() -2)
                indice.setText(geocache.getClue(badReponsesNbr));
            else {
                Toast.makeText(this, "La bonne réponse était" + geocache.getProposition(geocache.getAnswerNbr()), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, PostAnswerActivity.class);
                startActivity(intent);
            }
            badReponsesNbr ++;
        }

    }
}
