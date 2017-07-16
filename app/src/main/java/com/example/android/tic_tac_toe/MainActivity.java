package com.example.android.tic_tac_toe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.tic_tac_toe.SQL.SQLContract;
import com.example.android.tic_tac_toe.SQL.SQLDbHelper;

public class MainActivity extends AppCompatActivity {

    private Button continuegame;

    private Button newgame;

    private Button instructions;

    private Button exit;
    private String player1Name=null,player2Name=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuegame=(Button)findViewById(R.id.continuegame);
        newgame=(Button)findViewById(R.id.newgame);
        instructions=(Button)findViewById(R.id.instructions);
        exit=(Button)findViewById(R.id.exit);

        SQLDbHelper mDbHelper = new SQLDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                SQLContract.GameEntry._ID,
                SQLContract.GameEntry.COLUMN_GAME_PLAYER1,
                SQLContract.GameEntry.COLUMN_GAME_PLAYER2
        };
        Cursor cursor = db.query(SQLContract.GameEntry.TABLE_GAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int player1ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_PLAYER1);
            int player2ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_PLAYER2);
            player1Name = cursor.getString(player1ColumnIndex);
            player2Name = cursor.getString(player2ColumnIndex);
        }
        continuegame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1Name==null && player2Name ==null)
                    Toast.makeText(MainActivity.this,"No saved Game",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, gameActivity.class);
                    intent.putExtra("number", 4);
                    intent.putExtra("player1", player1Name);
                    intent.putExtra("player2", player2Name);
                    startActivity(intent);
                }
            }
        });
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewGameActivity.class);
                startActivity(intent);
            }
        });
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InstructionsActivity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                System.exit(0);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
            }
        });
    }
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to quit?");
        builder.setPositiveButton("Ok", discardButtonClickListener);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        System.exit(0);
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }
}
