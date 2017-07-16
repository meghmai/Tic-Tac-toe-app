package com.example.android.tic_tac_toe;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.tic_tac_toe.SQL.SQLContract;
import com.example.android.tic_tac_toe.SQL.SQLDbHelper;

public class gameActivity extends AppCompatActivity {

    private ImageButton b1;
    private ImageButton b2;
    private ImageButton b3;
    private ImageButton b4;
    private ImageButton b5;
    private ImageButton b6;
    private ImageButton b7;
    private ImageButton b8;
    private ImageButton b9;
    private TextView move;
    private TextView title;
    private int a1 = 0;
    private int a2 = 0;
    private int a3 = 0;
    private int a4 = 0;
    private int a5 = 0;
    private int a6 = 0;
    private int a7 = 0;
    private int a8 = 0;
    private int a9 = 0;
    private int c[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    private Handler mHandler = new Handler();
    private String player1;
    private String player2;
    private int number;
    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private View v5;
    private View v6;
    private View v7;
    private View v8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("Game");
        Bundle bundle = getIntent().getExtras();
        player1 = bundle.getString("player1");
        player2 = bundle.getString("player2");
        number = bundle.getInt("number");
        b1 = (ImageButton) findViewById(R.id.button1);
        b2 = (ImageButton) findViewById(R.id.button2);
        b3 = (ImageButton) findViewById(R.id.button3);
        b4 = (ImageButton) findViewById(R.id.button4);
        b5 = (ImageButton) findViewById(R.id.button5);
        b6 = (ImageButton) findViewById(R.id.button6);
        b7 = (ImageButton) findViewById(R.id.button7);
        b8 = (ImageButton) findViewById(R.id.button8);
        b9 = (ImageButton) findViewById(R.id.button9);
        v1 = (View) findViewById(R.id.a1);
        v2 = (View) findViewById(R.id.a2);
        v3 = (View) findViewById(R.id.a3);
        v4 = (View) findViewById(R.id.a4);
        v5 = (View) findViewById(R.id.a5);
        v6 = (View) findViewById(R.id.a6);
        v7 = (View) findViewById(R.id.a7);
        v8 = (View) findViewById(R.id.a8);
        title = (TextView) findViewById(R.id.turn);
        title.setText("It is " + player1 + "'s Chance!!");
        move = (TextView) findViewById(R.id.moves);
        int moves = 0;
        String count2 = String.valueOf(moves);
        move.setText(count2);
        if (number == 3 || number == 2) {
            singleplayer(number);
        } else if (number == 1) {
            multiplayer(number);
        } else if (number == 4) {
            Log.d("data", "msg");
            SQLDbHelper mDbHelper = new SQLDbHelper(this);
            final SQLiteDatabase db = mDbHelper.getReadableDatabase();
            String[] projection = {
                    SQLContract.GameEntry._ID,
                    SQLContract.GameEntry.COLUMN_GAME_PLAYER1,
                    SQLContract.GameEntry.COLUMN_GAME_PLAYER2,
                    SQLContract.GameEntry.COLUMN_GAME_MOVE,
                    SQLContract.GameEntry.COLUMN_GAME_B1,
                    SQLContract.GameEntry.COLUMN_GAME_B2,
                    SQLContract.GameEntry.COLUMN_GAME_B3,
                    SQLContract.GameEntry.COLUMN_GAME_B4,
                    SQLContract.GameEntry.COLUMN_GAME_B5,
                    SQLContract.GameEntry.COLUMN_GAME_B6,
                    SQLContract.GameEntry.COLUMN_GAME_B7,
                    SQLContract.GameEntry.COLUMN_GAME_B8,
                    SQLContract.GameEntry.COLUMN_GAME_B9,
                    SQLContract.GameEntry.COLUMN_GAME_DIFFICULTY
            };
            final Cursor cursor = db.query(SQLContract.GameEntry.TABLE_GAME, projection, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                int idColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry._ID);
                int player1ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_PLAYER1);
                int player2ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_PLAYER2);
                int moveColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_MOVE);
                int difficultyColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_DIFFICULTY);
                int b1ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B1);
                int b2ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B2);
                int b3ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B3);
                int b4ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B4);
                int b5ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B5);
                int b6ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B6);
                int b7ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B7);
                int b8ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B8);
                int b9ColumnIndex = cursor.getColumnIndex(SQLContract.GameEntry.COLUMN_GAME_B9);
                String player1Name = cursor.getString(player1ColumnIndex);
                String player2Name = cursor.getString(player2ColumnIndex);
                int moven = cursor.getInt(moveColumnIndex);
                int difficulty = cursor.getInt(difficultyColumnIndex);
                a1 = cursor.getInt(b1ColumnIndex);
                a2 = cursor.getInt(b2ColumnIndex);
                a3 = cursor.getInt(b3ColumnIndex);
                a4 = cursor.getInt(b4ColumnIndex);
                a5 = cursor.getInt(b5ColumnIndex);
                a6 = cursor.getInt(b6ColumnIndex);
                a7 = cursor.getInt(b7ColumnIndex);
                a8 = cursor.getInt(b8ColumnIndex);
                a9 = cursor.getInt(b9ColumnIndex);
                if (moven % 2 != 0)
                    title.setText("It is " + player2 + "'s chance!!");
                else
                    title.setText("It is " + player1 + "'s chance!!");
                move.setText(String.valueOf(moven));
                switch (a1) {
                    case 0:
                        c[1] = 2;
                        break;
                    case 1:
                        b1.setImageResource(R.drawable.right);
                        c[1] = 3;
                        break;
                    case 2:
                        b1.setImageResource(R.drawable.round);
                        c[1] = 5;
                        break;
                }
                switch (a2) {
                    case 0:
                        c[2] = 2;
                        break;
                    case 1:
                        b2.setImageResource(R.drawable.right);
                        c[2] = 3;
                        break;
                    case 2:
                        b2.setImageResource(R.drawable.round);
                        c[2] = 5;
                        break;
                }
                switch (a3) {
                    case 0:
                        c[3] = 2;
                        break;
                    case 1:
                        b3.setImageResource(R.drawable.right);
                        c[3] = 3;
                        break;
                    case 2:
                        b3.setImageResource(R.drawable.round);
                        c[3] = 5;
                        break;
                }
                switch (a4) {
                    case 0:
                        c[4] = 2;
                        break;
                    case 1:
                        b4.setImageResource(R.drawable.right);
                        c[4] = 3;
                        break;
                    case 2:
                        b4.setImageResource(R.drawable.round);
                        c[4] = 5;
                        break;
                }
                switch (a5) {
                    case 0:
                        c[5] = 2;
                        break;
                    case 1:
                        b5.setImageResource(R.drawable.right);
                        c[5] = 3;
                        break;
                    case 2:
                        b5.setImageResource(R.drawable.round);
                        c[5] = 5;
                        break;
                }
                switch (a6) {
                    case 0:
                        c[6] = 2;
                        break;
                    case 1:
                        b6.setImageResource(R.drawable.right);
                        c[6] = 3;
                        break;
                    case 2:
                        b6.setImageResource(R.drawable.round);
                        c[6] = 5;
                        break;
                }
                switch (a7) {
                    case 0:
                        c[7] = 2;
                        break;
                    case 1:
                        b7.setImageResource(R.drawable.right);
                        c[7] = 3;
                        break;
                    case 2:
                        b7.setImageResource(R.drawable.round);
                        c[7] = 5;
                        break;
                }
                switch (a8) {
                    case 0:
                        c[8] = 2;
                        break;
                    case 1:
                        b8.setImageResource(R.drawable.right);
                        c[8] = 3;
                        break;
                    case 2:
                        b8.setImageResource(R.drawable.round);
                        c[8] = 5;
                        break;
                }
                switch (a9) {
                    case 0:
                        c[9] = 2;
                        break;
                    case 1:
                        b9.setImageResource(R.drawable.right);
                        c[9] = 3;
                        break;
                    case 2:
                        b9.setImageResource(R.drawable.round);
                        c[9] = 5;
                        break;
                }
                if (difficulty == 3 || difficulty == 2) {
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a1 == 0) {
                                if (c[1] == 2) {
                                    a1 = 1;
                                    count++;
                                    c[1] = 3;
                                    b1.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                        String count1 = String.valueOf(count);
                                        move.setText(count1);
                                    } else {
                                        if (count <= 9) {
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a2 == 0) {
                                if (c[2] == 2) {
                                    a2 = 1;
                                    count++;
                                    c[2] = 3;
                                    b2.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                        String count1 = String.valueOf(count);
                                        move.setText(count1);
                                    } else {
                                        if (count <= 9) {
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a3 == 0) {
                                if (c[3] == 2) {
                                    a3 = 1;
                                    count++;
                                    c[3] = 3;
                                    b3.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a4 == 0) {
                                if (c[4] == 2) {
                                    a4 = 1;
                                    count++;
                                    c[4] = 3;
                                    b4.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a5 == 0) {
                                if (c[5] == 2) {
                                    a5 = 1;
                                    count++;
                                    c[5] = 3;
                                    b5.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a6 == 0) {
                                if (c[6] == 2) {
                                    a6 = 1;
                                    count++;
                                    c[6] = 3;
                                    b6.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a7 == 0) {
                                if (c[7] == 2) {
                                    b7.setImageResource(R.drawable.right);
                                    a7 = 1;
                                    count++;
                                    c[7] = 3;
                                    b7.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a8 == 0) {
                                if (c[8] == 2) {
                                    b8.setImageResource(R.drawable.right);
                                    a8 = 1;
                                    count++;
                                    c[8] = 3;
                                    b8.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a9 == 0) {
                                if (c[9] == 2) {
                                    b9.setImageResource(R.drawable.right);
                                    a9 = 1;
                                    count++;
                                    c[9] = 3;
                                    b9.setImageResource(R.drawable.right);
                                    title.setText("It is " + player2 + "'s Chance!!");
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                    if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                        if (cursor.moveToFirst()) {
                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                        }
                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                        a1 = 1;
                                        a2 = 1;
                                        a3 = 1;
                                        a4 = 1;
                                        a5 = 1;
                                        a6 = 1;
                                        a7 = 1;
                                        a8 = 1;
                                        a9 = 1;
                                        mHandler.postDelayed(new Runnable() {
                                            public void run() {
                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                showUnsavedChangesDialog(discardButtonClickListener);
                                            }
                                        }, 500);
                                    } else {
                                        if (count <= 9) {
                                            move.setText(count1);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    if (number == 3)
                                                        gameOnePlayer();
                                                    else
                                                        gameEasyPlayer();
                                                    if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                                        if (cursor.moveToFirst()) {
                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                        }
                                                        win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                                        mHandler.postDelayed(new Runnable() {
                                                            public void run() {
                                                                DialogInterface.OnClickListener discardButtonClickListener =
                                                                        new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        };
                                                                showWinnerDialog(discardButtonClickListener);
                                                            }
                                                        }, 1000);
                                                    }
                                                }
                                            }, 500);
                                        }
                                        if (count == 9) {
                                            if (cursor.moveToFirst()) {
                                                String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                            }
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showDrawDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                        }
                                    }
                                }
                            }
                        }
                    });
                } else if (difficulty == 1) {
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a1 == 0) {
                                if (c[1] == 2) {
                                    a1 = 1;
                                    if (count % 2 == 0) {
                                        c[1] = 3;
                                        b1.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[1] = 5;
                                        b1.setImageResource(R.drawable.round);
                                        a1 = 2;
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a2 == 0) {
                                if (c[2] == 2) {
                                    a2 = 1;
                                    if (count % 2 == 0) {
                                        c[2] = 3;
                                        b2.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[2] = 5;
                                        a2 = 2;
                                        b2.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a3 == 0) {
                                if (c[3] == 2) {
                                    a3 = 1;
                                    if (count % 2 == 0) {
                                        c[3] = 3;
                                        b3.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[3] = 5;
                                        a3 = 2;
                                        b3.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a4 == 0) {
                                if (c[4] == 2) {
                                    a4 = 1;
                                    if (count % 2 == 0) {
                                        c[4] = 3;
                                        b4.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[4] = 5;
                                        a4 = 2;
                                        b4.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a5 == 0) {
                                if (c[5] == 2) {
                                    a5 = 1;
                                    if (count % 2 == 0) {
                                        c[5] = 3;
                                        b5.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[5] = 5;
                                        a5 = 2;
                                        b5.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a6 == 0) {
                                if (c[6] == 2) {
                                    a6 = 1;
                                    if (count % 2 == 0) {
                                        c[6] = 3;
                                        b6.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[6] = 5;
                                        a6 = 2;
                                        b6.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a7 == 0) {
                                if (c[7] == 2) {
                                    a7 = 1;
                                    if (count % 2 == 0) {
                                        c[7] = 3;
                                        b7.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[7] = 5;
                                        a7 = 2;
                                        b7.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a8 == 0) {
                                if (c[8] == 2) {
                                    a8 = 1;
                                    if (count % 2 == 0) {
                                        c[8] = 3;
                                        b8.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[8] = 5;
                                        a8 = 2;
                                        b8.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                    b9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int count = Integer.valueOf(move.getText().toString());
                            if (a9 == 0) {
                                if (c[9] == 2) {
                                    a9 = 1;
                                    if (count % 2 == 0) {
                                        c[9] = 3;
                                        b9.setImageResource(R.drawable.right);
                                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player2 + "'s Chance!!");
                                        }
                                    } else {
                                        c[9] = 5;
                                        a9 = 2;
                                        b9.setImageResource(R.drawable.round);
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            a1 = 1;
                                            a2 = 1;
                                            a3 = 1;
                                            a4 = 1;
                                            a5 = 1;
                                            a6 = 1;
                                            a7 = 1;
                                            a8 = 1;
                                            a9 = 1;
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    if (cursor.moveToFirst()) {
                                                                        String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                        db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                    }
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showUnsavedChangesDialog(discardButtonClickListener);
                                                }
                                            }, 500);
                                            count++;
                                            String count1 = String.valueOf(count);
                                            move.setText(count1);
                                        } else {
                                            if (count <= 9) {
                                                count++;
                                                String count1 = String.valueOf(count);
                                                move.setText(count1);
                                            }
                                            if (count == 9) {
                                                mHandler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        if (cursor.moveToFirst()) {
                                                                            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
                                                                            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
                                                                        }
                                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                        startActivity(intent);
                                                                    }
                                                                };
                                                        showDrawDialog(discardButtonClickListener);
                                                    }
                                                }, 500);
                                            }
                                            title.setText("It is " + player1 + "'s Chance!!");
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
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
            case R.id.action_reset:
                b1.setImageResource(0);
                b2.setImageResource(0);
                b3.setImageResource(0);
                b4.setImageResource(0);
                b5.setImageResource(0);
                b6.setImageResource(0);
                b7.setImageResource(0);
                b8.setImageResource(0);
                b9.setImageResource(0);
                a1 = 0;
                a2 = 0;
                a3 = 0;
                a4 = 0;
                a5 = 0;
                a6 = 0;
                a7 = 0;
                a8 = 0;
                a9 = 0;
                c[1] = 2;
                c[2] = 2;
                c[3] = 2;
                c[4] = 2;
                c[5] = 2;
                c[6] = 2;
                c[7] = 2;
                c[8] = 2;
                c[9] = 2;
                move.setText(String.valueOf(0));
                title.setText("It is " + player1 + "'s Chance!!");
                return true;
            case android.R.id.home:
                DialogInterface.OnClickListener ExitButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125 || c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    DialogInterface.OnClickListener SaveButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    savegame();
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showSaveDialog(SaveButtonClickListener);
                                }
                            }
                        };

                showExitDialog(ExitButtonClickListener);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener ExitButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125 || c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            DialogInterface.OnClickListener SaveButtonClickListener =
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            savegame();
                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    };
                            showSaveDialog(SaveButtonClickListener);
                        }
                    }
                };

        showExitDialog(ExitButtonClickListener);
    }

    private void showExitDialog(
            DialogInterface.OnClickListener ExitButtonClickListener) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Do you want to Exit the game?");
        builder1.setPositiveButton("Ok", ExitButtonClickListener);
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog1 = builder1.create();
        alertDialog1.show();
    }

    private void showSaveDialog(
            DialogInterface.OnClickListener SaveButtonClickListener) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Do you want to save the game?");
        builder1.setPositiveButton("Save", SaveButtonClickListener);
        builder1.setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog1 = builder1.create();
        alertDialog1.show();
    }

    private void showWinnerDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(player2 + " is the winner!!");
        builder1.setPositiveButton("Okay", discardButtonClickListener);
        AlertDialog alertDialog1 = builder1.create();
        alertDialog1.show();
    }

    private void showDrawDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("The game is draw!!");
        builder1.setPositiveButton("Okay", discardButtonClickListener);
        AlertDialog alertDialog1 = builder1.create();
        alertDialog1.show();
    }

    private void savegame() {
        String mmove = move.getText().toString().trim();
        int move = Integer.valueOf(mmove);
        SQLDbHelper mDbHelper = new SQLDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String[] projection = {
                SQLContract.GameEntry._ID,
                SQLContract.GameEntry.COLUMN_GAME_PLAYER1,
                SQLContract.GameEntry.COLUMN_GAME_PLAYER2,
                SQLContract.GameEntry.COLUMN_GAME_MOVE,
                SQLContract.GameEntry.COLUMN_GAME_B1,
                SQLContract.GameEntry.COLUMN_GAME_B2,
                SQLContract.GameEntry.COLUMN_GAME_B3,
                SQLContract.GameEntry.COLUMN_GAME_B4,
                SQLContract.GameEntry.COLUMN_GAME_B5,
                SQLContract.GameEntry.COLUMN_GAME_B6,
                SQLContract.GameEntry.COLUMN_GAME_B7,
                SQLContract.GameEntry.COLUMN_GAME_B8,
                SQLContract.GameEntry.COLUMN_GAME_B9,
                SQLContract.GameEntry.COLUMN_GAME_DIFFICULTY
        };
        Cursor cursor = db.query(SQLContract.GameEntry.TABLE_GAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String rowId = cursor.getString(cursor.getColumnIndex(SQLContract.GameEntry._ID));
            db.delete(SQLContract.GameEntry.TABLE_GAME, SQLContract.GameEntry._ID + "=?", new String[]{rowId});
        }
        ContentValues values = new ContentValues();
        values.put(SQLContract.GameEntry.COLUMN_GAME_PLAYER1, player1);
        values.put(SQLContract.GameEntry.COLUMN_GAME_PLAYER2, player2);
        values.put(SQLContract.GameEntry.COLUMN_GAME_MOVE, move);
        values.put(SQLContract.GameEntry.COLUMN_GAME_DIFFICULTY, number);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B1, a1);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B2, a2);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B3, a3);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B4, a4);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B5, a5);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B6, a6);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B7, a7);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B8, a8);
        values.put(SQLContract.GameEntry.COLUMN_GAME_B9, a9);
        db.insert(SQLContract.GameEntry.TABLE_GAME, null, values);
    }

    void gameOnePlayer() {
        int k, j;
        int count1;
        count1 = Integer.valueOf(move.getText().toString());
        if (count1 == 1) {
            if (c[5] == 2) {
                c[5] = 5;
                a5 = 2;
                b5.setImageResource(R.drawable.round);
                title.setText("It is " + player1 + "'s Chance!!");
            } else {
                c[1] = 5;
                a1 = 2;
                b1.setImageResource(R.drawable.round);
                title.setText("It is " + player1 + "'s Chance!!");
            }
        }
        if (count1 == 3) {
            k = possWinX();
            if (k != 0) {
                c[k] = 5;
                title.setText("It is " + player1 + "'s Chance!!");
                if (k == 1) {
                    a1 = 2;
                    b1.setImageResource(R.drawable.round);
                } else if (k == 2) {
                    a2 = 2;
                    b2.setImageResource(R.drawable.round);
                } else if (k == 3) {
                    a3 = 2;
                    b3.setImageResource(R.drawable.round);
                } else if (k == 4) {
                    a4 = 2;
                    b4.setImageResource(R.drawable.round);
                } else if (k == 5) {
                    a5 = 2;
                    b5.setImageResource(R.drawable.round);
                } else if (k == 6) {
                    a6 = 2;
                    b6.setImageResource(R.drawable.round);
                } else if (k == 7) {
                    a7 = 2;
                    b7.setImageResource(R.drawable.round);
                } else if (k == 8) {
                    a8 = 2;
                    b8.setImageResource(R.drawable.round);
                } else {
                    a9 = 2;
                    b9.setImageResource(R.drawable.round);
                }
            } else {
                if (c[5] == 5) {
                    if (c[1] == 2) {
                        c[1] = 5;
                        a1 = 2;
                        b1.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    } else if (c[3] == 2) {
                        c[2] = 5;
                        a2 = 2;
                        b2.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    } else if (c[7] == 2) {
                        c[7] = 5;
                        a7 = 2;
                        b7.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    } else {
                        a9 = 2;
                        c[9] = 5;
                        b9.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    }
                } else {
                    if (c[3] == 2) {
                        c[2] = 5;
                        a2 = 2;
                        b2.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    } else if (c[7] == 2) {
                        c[7] = 5;
                        a7 = 2;
                        b7.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    } else {
                        c[9] = 5;
                        a9 = 2;
                        b9.setImageResource(R.drawable.round);
                        title.setText("It is " + player1 + "'s Chance!!");
                    }
                }
            }
        }
        if (count1 == 5 || count1 == 7) {
            j = possWinO();
            k = possWinX();
            if (j != 0) {
                c[j] = 5;
                title.setText("It is " + player1 + "'s Chance!!");
                if (j == 1) {
                    a1 = 2;
                    b1.setImageResource(R.drawable.round);
                } else if (j == 2) {
                    a2 = 2;
                    b2.setImageResource(R.drawable.round);
                } else if (j == 3) {
                    a3 = 2;
                    b3.setImageResource(R.drawable.round);
                } else if (j == 4) {
                    a4 = 2;
                    b4.setImageResource(R.drawable.round);
                } else if (j == 5) {
                    a5 = 2;
                    b5.setImageResource(R.drawable.round);
                } else if (j == 6) {
                    a6 = 2;
                    b6.setImageResource(R.drawable.round);
                } else if (j == 7) {
                    a7 = 2;
                    b7.setImageResource(R.drawable.round);
                } else if (j == 8) {
                    a8 = 2;
                    b8.setImageResource(R.drawable.round);
                } else {
                    a9 = 2;
                    b9.setImageResource(R.drawable.round);
                }
            } else if (k != 0) {
                c[k] = 5;
                title.setText("It is " + player1 + "'s Chance!!");
                if (k == 1) {
                    a1 = 2;
                    b1.setImageResource(R.drawable.round);
                } else if (k == 2) {
                    a2 = 2;
                    b2.setImageResource(R.drawable.round);
                } else if (k == 3) {
                    a3 = 2;
                    b3.setImageResource(R.drawable.round);
                } else if (k == 4) {
                    a4 = 2;
                    b4.setImageResource(R.drawable.round);
                } else if (k == 5) {
                    a5 = 2;
                    b5.setImageResource(R.drawable.round);
                } else if (k == 6) {
                    a6 = 2;
                    b6.setImageResource(R.drawable.round);
                } else if (k == 7) {
                    a7 = 2;
                    b7.setImageResource(R.drawable.round);
                } else if (k == 8) {
                    a8 = 2;
                    b8.setImageResource(R.drawable.round);
                } else {
                    a9 = 2;
                    b9.setImageResource(R.drawable.round);
                }
            } else {
                for (int s = 1; s <= 9; s++) {
                    if (c[s] == 2) {
                        c[s] = 5;
                        title.setText("It is " + player1 + "'s Chance!!");
                        if (s == 1) {
                            a1 = 2;
                            b1.setImageResource(R.drawable.round);
                        } else if (s == 2) {
                            a2 = 2;
                            b2.setImageResource(R.drawable.round);
                        } else if (s == 3) {
                            a3 = 2;
                            b3.setImageResource(R.drawable.round);
                        } else if (s == 4) {
                            a4 = 2;
                            b4.setImageResource(R.drawable.round);
                        } else if (s == 5) {
                            a5 = 2;
                            b5.setImageResource(R.drawable.round);
                        } else if (s == 6) {
                            a6 = 2;
                            b6.setImageResource(R.drawable.round);
                        } else if (s == 7) {
                            a7 = 2;
                            b7.setImageResource(R.drawable.round);
                        } else if (s == 8) {
                            a8 = 2;
                            b8.setImageResource(R.drawable.round);
                        } else {
                            a9 = 2;
                            b9.setImageResource(R.drawable.round);
                        }
                        break;
                    }
                }
            }
        }
        if (count1 < 9) {
            count1++;
            String c = String.valueOf(count1);
            move.setText(c);
        }

    }

    void gameEasyPlayer() {
        int j;
        int count1;
        count1 = Integer.valueOf(move.getText().toString());
        if (count1 == 1) {
            if (c[5] == 2) {
                c[5] = 5;
                a5 = 2;
                b5.setImageResource(R.drawable.round);
                title.setText("It is " + player1 + "'s Chance!!");
            } else {
                c[1] = 5;
                a1 = 2;
                b1.setImageResource(R.drawable.round);
                title.setText("It is " + player1 + "'s Chance!!");
            }
        }
        if (count1 == 3) {
            if (c[5] == 5) {
                if (c[1] == 2) {
                    c[1] = 5;
                    a1 = 2;
                    b1.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                } else if (c[3] == 2) {
                    c[3] = 5;
                    a3 = 2;
                    b3.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                } else if (c[7] == 2) {
                    c[7] = 5;
                    a7 = 2;
                    b7.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                } else {
                    c[9] = 5;
                    a9 = 2;
                    b9.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                }
            } else {
                if (c[2] == 2) {
                    c[2] = 5;
                    a2 = 2;
                    b2.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                } else if (c[7] == 2) {
                    c[7] = 5;
                    a7 = 2;
                    b7.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                } else {
                    c[9] = 5;
                    a9 = 2;
                    b9.setImageResource(R.drawable.round);
                    title.setText("It is " + player1 + "'s Chance!!");
                }
            }
        }
        if (count1 == 5 || count1 == 7) {
            j = possWinO();
            if (j != 0) {
                c[j] = 5;
                title.setText("It is " + player1 + "'s Chance!!");
                if (j == 1) {
                    a1 = 2;
                    b1.setImageResource(R.drawable.round);
                } else if (j == 2) {
                    a2 = 2;
                    b2.setImageResource(R.drawable.round);
                } else if (j == 3) {
                    a3 = 2;
                    b3.setImageResource(R.drawable.round);
                } else if (j == 4) {
                    a4 = 2;
                    b4.setImageResource(R.drawable.round);
                } else if (j == 5) {
                    a5 = 2;
                    b5.setImageResource(R.drawable.round);
                } else if (j == 6) {
                    a6 = 2;
                    b6.setImageResource(R.drawable.round);
                } else if (j == 7) {
                    a7 = 2;
                    b7.setImageResource(R.drawable.round);
                } else if (j == 8) {
                    a8 = 2;
                    b8.setImageResource(R.drawable.round);
                } else {
                    a9 = 2;
                    b9.setImageResource(R.drawable.round);
                }
            } else {
                for (int s = 1; s <= 9; s++) {
                    if (c[s] == 2) {
                        c[s] = 5;
                        title.setText("It is " + player1 + "'s Chance!!");
                        if (s == 1) {
                            a1 = 2;
                            b1.setImageResource(R.drawable.round);
                        } else if (s == 2) {
                            a2 = 2;
                            b2.setImageResource(R.drawable.round);
                        } else if (s == 3) {
                            a3 = 2;
                            b3.setImageResource(R.drawable.round);
                        } else if (s == 4) {
                            a4 = 2;
                            b4.setImageResource(R.drawable.round);
                        } else if (s == 5) {
                            a5 = 2;
                            b5.setImageResource(R.drawable.round);
                        } else if (s == 6) {
                            a6 = 2;
                            b6.setImageResource(R.drawable.round);
                        } else if (s == 7) {
                            a7 = 2;
                            b7.setImageResource(R.drawable.round);
                        } else if (s == 8) {
                            a8 = 2;
                            b8.setImageResource(R.drawable.round);
                        } else {
                            a9 = 2;
                            b9.setImageResource(R.drawable.round);
                        }
                        break;
                    }
                }
            }
        }
        if (count1 < 9) {
            count1++;
            String c = String.valueOf(count1);
            move.setText(c);
        }
    }

    int possWinX() {
        int k = 0;
        for (int i = 1; i <= 9; i = i + 3) {
            if (c[i] * c[i + 1] * c[i + 2] == 18)
                if (c[i] == 2)
                    k = i;
                else if (c[i + 1] == 2)
                    k = i + 1;
                else
                    k = i + 2;
        }
        for (int i = 1; i <= 3; i++) {
            if (c[i] * c[i + 3] * c[i + 6] == 18)
                if (c[i] == 2)
                    k = i;
                else if (c[i + 3] == 2)
                    k = i + 3;
                else
                    k = i + 6;
        }
        if (c[1] * c[5] * c[9] == 18) {
            if (c[1] == 2)
                k = 1;
            else if (c[5] == 2)
                k = 5;
            else
                k = 9;
        }
        if (c[3] * c[5] * c[7] == 18) {
            if (c[3] == 2)
                k = 3;
            else if (c[5] == 2)
                k = 5;
            else
                k = 7;
        }
        return k;
    }

    int possWinO() {
        int k = 0;
        for (int i = 1; i <= 9; i = i + 3) {
            if (c[i] * c[i + 1] * c[i + 2] == 50)
                if (c[i] == 2)
                    k = i;
                else if (c[i + 1] == 2)
                    k = i + 1;
                else
                    k = i + 2;
        }
        for (int i = 1; i <= 3; i++) {
            if (c[i] * c[i + 3] * c[i + 6] == 50)
                if (c[i] == 2)
                    k = i;
                else if (c[i + 3] == 2)
                    k = i + 3;
                else
                    k = i + 6;
        }
        if (c[1] * c[5] * c[9] == 50) {
            if (c[1] == 2)
                k = 1;
            else if (c[5] == 2)
                k = 5;
            else
                k = 9;
        }
        if (c[3] * c[5] * c[7] == 50) {
            if (c[3] == 2)
                k = 3;
            else if (c[5] == 2)
                k = 5;
            else
                k = 7;
        }
        return k;
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(player1 + " is the Winner!!");
        builder.setPositiveButton("Okay", discardButtonClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void singleplayer(final int number) {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a1 == 0) {
                    if (c[1] == 2) {
                        a1 = 1;
                        count++;
                        c[1] = 3;
                        b1.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                            String count1 = String.valueOf(count);
                            move.setText(count1);
                        } else {
                            if (count <= 9) {
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            }
                        }
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a2 == 0) {
                    if (c[2] == 2) {
                        a2 = 1;
                        count++;
                        c[2] = 3;
                        b2.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                            String count1 = String.valueOf(count);
                            move.setText(count1);
                        } else {
                            if (count <= 9) {
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            }
                        }
                    }
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a3 == 0) {
                    if (c[3] == 2) {
                        a3 = 1;
                        count++;
                        c[3] = 3;
                        b3.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a4 == 0) {
                    if (c[4] == 2) {
                        a4 = 1;
                        count++;
                        c[4] = 3;
                        b4.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a5 == 0) {
                    if (c[5] == 2) {
                        a5 = 1;
                        count++;
                        c[5] = 3;
                        b5.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a6 == 0) {
                    if (c[6] == 2) {
                        a6 = 1;
                        count++;
                        c[6] = 3;
                        b6.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a7 == 0) {
                    if (c[7] == 2) {
                        b7.setImageResource(R.drawable.right);
                        a7 = 1;
                        count++;
                        c[7] = 3;
                        b7.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a8 == 0) {
                    if (c[8] == 2) {
                        b8.setImageResource(R.drawable.right);
                        a8 = 1;
                        count++;
                        c[8] = 3;
                        b8.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a9 == 0) {
                    if (c[9] == 2) {
                        b9.setImageResource(R.drawable.right);
                        a9 = 1;
                        count++;
                        c[9] = 3;
                        b9.setImageResource(R.drawable.right);
                        title.setText("It is " + player2 + "'s Chance!!");
                        String count1 = String.valueOf(count);
                        move.setText(count1);
                        if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                            a1 = 1;
                            a2 = 1;
                            a3 = 1;
                            a4 = 1;
                            a5 = 1;
                            a6 = 1;
                            a7 = 1;
                            a8 = 1;
                            a9 = 1;
                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DialogInterface.OnClickListener discardButtonClickListener =
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            };
                                    showUnsavedChangesDialog(discardButtonClickListener);
                                }
                            }, 500);
                        } else {
                            if (count <= 9) {
                                move.setText(count1);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        if (number == 3)
                                            gameOnePlayer();
                                        else
                                            gameEasyPlayer();
                                        if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                            win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                            mHandler.postDelayed(new Runnable() {
                                                public void run() {
                                                    DialogInterface.OnClickListener discardButtonClickListener =
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            };
                                                    showWinnerDialog(discardButtonClickListener);
                                                }
                                            }, 1000);
                                        }
                                    }
                                }, 500);
                            }
                            if (count == 9) {
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showDrawDialog(discardButtonClickListener);
                                    }
                                }, 500);
                            }
                        }
                    }
                }
            }
        });
    }

    private void multiplayer(int number) {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a1 == 0) {
                    if (c[1] == 2) {
                        a1 = 1;
                        if (count % 2 == 0) {
                            c[1] = 3;
                            b1.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[1] = 5;
                            b1.setImageResource(R.drawable.round);
                            a1 = 2;
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a2 == 0) {
                    if (c[2] == 2) {
                        a2 = 1;
                        if (count % 2 == 0) {
                            c[2] = 3;
                            b2.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[2] = 5;
                            a2 = 2;
                            b2.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a3 == 0) {
                    if (c[3] == 2) {
                        a3 = 1;
                        if (count % 2 == 0) {
                            c[3] = 3;
                            b3.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[3] = 5;
                            a3 = 2;
                            b3.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a4 == 0) {
                    if (c[4] == 2) {
                        a4 = 1;
                        if (count % 2 == 0) {
                            c[4] = 3;
                            b4.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[4] = 5;
                            a4 = 2;
                            b4.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a5 == 0) {
                    if (c[5] == 2) {
                        a5 = 1;
                        if (count % 2 == 0) {
                            c[5] = 3;
                            b5.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[5] = 5;
                            a5 = 2;
                            b5.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a6 == 0) {
                    if (c[6] == 2) {
                        a6 = 1;
                        if (count % 2 == 0) {
                            c[6] = 3;
                            b6.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[6] = 5;
                            a6 = 2;
                            b6.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a7 == 0) {
                    if (c[7] == 2) {
                        a7 = 1;
                        if (count % 2 == 0) {
                            c[7] = 3;
                            b7.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[7] = 5;
                            a7 = 2;
                            b7.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a8 == 0) {
                    if (c[8] == 2) {
                        a8 = 1;
                        if (count % 2 == 0) {
                            c[8] = 3;
                            b8.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[8] = 5;
                            a8 = 2;
                            b8.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(move.getText().toString());
                if (a9 == 0) {
                    if (c[9] == 2) {
                        a9 = 1;
                        if (count % 2 == 0) {
                            c[9] = 3;
                            b9.setImageResource(R.drawable.right);
                            if (c[1] * c[2] * c[3] == 27 || c[4] * c[5] * c[6] == 27 || c[7] * c[8] * c[9] == 27 || c[1] * c[4] * c[7] == 27 || c[5] * c[2] * c[8] == 27 || c[9] * c[6] * c[3] == 27 || c[1] * c[5] * c[9] == 27 || c[5] * c[7] * c[3] == 27) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player2 + "'s Chance!!");
                            }
                        } else {
                            c[9] = 5;
                            a9 = 2;
                            b9.setImageResource(R.drawable.round);
                            if (c[1] * c[2] * c[3] == 125 || c[4] * c[5] * c[6] == 125 || c[7] * c[8] * c[9] == 125 || c[1] * c[4] * c[7] == 125 || c[5] * c[2] * c[8] == 125 || c[9] * c[6] * c[3] == 125 || c[1] * c[5] * c[9] == 125 || c[5] * c[7] * c[3] == 125) {
                                a1 = 1;
                                a2 = 1;
                                a3 = 1;
                                a4 = 1;
                                a5 = 1;
                                a6 = 1;
                                a7 = 1;
                                a8 = 1;
                                a9 = 1;
                                win(c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        DialogInterface.OnClickListener discardButtonClickListener =
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                };
                                        showUnsavedChangesDialog(discardButtonClickListener);
                                    }
                                }, 500);
                                count++;
                                String count1 = String.valueOf(count);
                                move.setText(count1);
                            } else {
                                if (count <= 9) {
                                    count++;
                                    String count1 = String.valueOf(count);
                                    move.setText(count1);
                                }
                                if (count == 9) {
                                    mHandler.postDelayed(new Runnable() {
                                        public void run() {
                                            DialogInterface.OnClickListener discardButtonClickListener =
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(gameActivity.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    };
                                            showDrawDialog(discardButtonClickListener);
                                        }
                                    }, 500);
                                }
                                title.setText("It is " + player1 + "'s Chance!!");
                            }
                        }
                    }
                }
            }
        });
    }

    private void win(int b1, int b2, int b3, int b4, int b5, int b6, int b7, int b8, int b9) {
        if (b1 * b2 * b3 == 27 || b1 * b2 * b3 == 125)
            v1.setVisibility(View.VISIBLE);
        else if (b4 * b5 * b6 == 27 || b4 * b5 * b6 == 125)
            v2.setVisibility(View.VISIBLE);
        else if (b7 * b8 * b9 == 27 || b7 * b8 * b9 == 125)
            v3.setVisibility(View.VISIBLE);
        else if (b1 * b4 * b7 == 27 || b1 * b4 * b7 == 125)
            v4.setVisibility(View.VISIBLE);
        else if (b2 * b5 * b8 == 27 || b2 * b5 * b8 == 125)
            v5.setVisibility(View.VISIBLE);
        else if (b3 * b6 * b9 == 27 || b3 * b6 * b9 == 125)
            v6.setVisibility(View.VISIBLE);
        else if (b1 * b5 * b9 == 27 || b1 * b5 * b9 == 125)
            v8.setVisibility(View.VISIBLE);
        else if (b3 * b5 * b7 == 27 || b3 * b5 * b7 == 125)
            v7.setVisibility(View.VISIBLE);
    }
}