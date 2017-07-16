package com.example.android.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        setTitle("Instructions");
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
        }
    }
        @Override
        public void onBackPressed() {
            Intent intent=new Intent(InstructionsActivity.this,MainActivity.class);
            startActivity(intent);
        }
}
