package com.example.android.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewGameActivity extends AppCompatActivity {

    private Button player1;

    private Button player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        setTitle("New Game");

        player1=(Button)findViewById(R.id.single);
        player2=(Button)findViewById(R.id.multi);

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewGameActivity.this,difficultyActivity.class);
                startActivity(intent);
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewGameActivity.this,playerNameActivity.class);
                intent.putExtra("name",false);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){}
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NewGameActivity.this,MainActivity.class);
        startActivity(intent);
    }

}
