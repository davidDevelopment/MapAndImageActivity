package com.daviddev.mise_en_oeuvre_recherche_balise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ImageActivity extends Activity implements View.OnClickListener {

    Button map_button, find_image_button;
    ListView image_list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        this.overridePendingTransition(R.anim.left_slide_in,R.anim.left_slide_out);

        map_button = findViewById(R.id.map_button);
        map_button.setOnClickListener(this);

        find_image_button = findViewById(R.id.find_image_button);
        find_image_button.setOnClickListener(this);

        image_list = findViewById(R.id.image_list);

        int flags[] = {R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img2, R.drawable.img1, R.drawable.img2,  R.drawable.img1, R.drawable.img2,  R.drawable.img1, R.drawable.img2};

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),  flags);
        image_list.setAdapter(customAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.map_button:
                intent = new Intent(this, MapActivity.class);
              //  this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(intent);
                break;
            case R.id.find_image_button:
                intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
        }

    }
}
