package com.example.android.tic_tac_toe.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "game.db";

    private static final int DATABASE_VERSION = 1;

    public SQLDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_GAME_TABLE = "CREATE TABLE " + SQLContract.GameEntry.TABLE_GAME + " ("
                + SQLContract.GameEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLContract.GameEntry.COLUMN_GAME_PLAYER1 + " TEXT NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_PLAYER2 + " TEXT NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_MOVE + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_DIFFICULTY + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B1 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B2 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B3 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B4 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B5 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B6 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B7 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B8 + " INTEGER NOT NULL, "
                + SQLContract.GameEntry.COLUMN_GAME_B9 + " INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_GAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
