package com.daviddev.mise_en_oeuvre_recherche_balise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostAnswerActivity extends Activity implements View.OnClickListener {

    Button valid_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postanswer);

        valid_button = findViewById(R.id.valid_button);
        valid_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.valid_button){
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
    }
}
