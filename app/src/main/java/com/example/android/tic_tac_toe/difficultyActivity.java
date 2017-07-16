package com.example.android.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class difficultyActivity extends AppCompatActivity {
    private Button easy;

    private Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        setTitle("Difficulty");

        easy = (Button) findViewById(R.id.easy);
        hard = (Button) findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(difficultyActivity.this, playerNameActivity.class);
                intent.putExtra("name", true);
                intent.putExtra("type",2);
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(difficultyActivity.this, playerNameActivity.class);
                intent.putExtra("name", true);
                intent.putExtra("type",3);
                startActivity(intent);
            }
        });

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(difficultyActivity.this,NewGameActivity.class);
        startActivity(intent);
    }
}
