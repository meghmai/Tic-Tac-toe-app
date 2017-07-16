package com.example.android.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class playerNameActivity extends AppCompatActivity {

    private EditText player1;

    private EditText player2;

    private int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        player1 = (EditText) findViewById(R.id.player1);
        player2 = (EditText) findViewById(R.id.player2);
        TextView playername = (TextView) findViewById(R.id.player2name);

        Bundle bundle = getIntent().getExtras();
        Boolean name = bundle.getBoolean("name");
        t=bundle.getInt("type");
        if (name) {
            player2.setVisibility(View.GONE);
            playername.setVisibility(View.GONE);
            setTitle("Single Player");
            player2.setText("Computer");
        } else {
            setTitle("Multi Player");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                    String player1name = player1.getText().toString().trim();
                    String player2name = player2.getText().toString().trim();
                if(TextUtils.isEmpty(player1name) || TextUtils.isEmpty(player2name))
                    Toast.makeText(this,"Enter valid details",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(playerNameActivity.this, gameActivity.class);
                    intent.putExtra("player1", player1name);
                    intent.putExtra("player2", player2name);
                    intent.putExtra("number", t);
                    startActivity(intent);
                }
                return true;
            case R.id.action_reset:
                player1.setText("");
                player2.setText("");
                return true;
            case android.R.id.home:
                    Intent i = NavUtils.getParentActivityIntent(this);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, i);
                    return true;
                }
        return super.onOptionsItemSelected(item);
        }
    @Override
    public void onBackPressed() {
        Intent i = NavUtils.getParentActivityIntent(this);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        NavUtils.navigateUpTo(this, i);
    }
    }

