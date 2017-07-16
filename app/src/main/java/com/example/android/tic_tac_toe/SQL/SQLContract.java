package com.example.android.tic_tac_toe.SQL;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class SQLContract {
    private SQLContract(){

    }
    public static final String CONTENT_AUTHORITY = "com.example.android.tic_tac_toe";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public final static String PATH_GAME = "game";
    public static final class GameEntry implements BaseColumns{
        public static final Uri GAME_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_GAME);
        public static final String GAME_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GAME;
        public static final String GAME_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GAME;

        public final static String TABLE_GAME = "game";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_GAME_PLAYER1="player1";

        public final static String COLUMN_GAME_PLAYER2="player2";

        public final static String COLUMN_GAME_B1="b1";
        public final static String COLUMN_GAME_B2="b2";
        public final static String COLUMN_GAME_B3="b3";
        public final static String COLUMN_GAME_B4="b4";
        public final static String COLUMN_GAME_B5="b5";
        public final static String COLUMN_GAME_B6="b6";
        public final static String COLUMN_GAME_B7="b7";
        public final static String COLUMN_GAME_B8="b8";
        public final static String COLUMN_GAME_B9="b9";

        public final static String COLUMN_GAME_MOVE="move";
        public final static String COLUMN_GAME_DIFFICULTY="difficulty";

    }
}
