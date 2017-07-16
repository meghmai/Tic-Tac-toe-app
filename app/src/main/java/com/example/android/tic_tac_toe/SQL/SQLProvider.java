package com.example.android.tic_tac_toe.SQL;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SQLProvider extends ContentProvider {
    private static final int GAME = 108;
    private static final int GAME_ID = 109;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(SQLContract.CONTENT_AUTHORITY, SQLContract.PATH_GAME, GAME);
        sUriMatcher.addURI(SQLContract.CONTENT_AUTHORITY, SQLContract.PATH_GAME + "/#", GAME_ID);


    }

    private SQLDbHelper mDbHelper;
    @Override
    public boolean onCreate() {
        mDbHelper = new SQLDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case GAME:

                cursor = database.query(SQLContract.GameEntry.TABLE_GAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case GAME_ID:

                selection = SQLContract.GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(SQLContract.GameEntry.TABLE_GAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case GAME:
                return SQLContract.GameEntry.GAME_LIST_TYPE;
            case GAME_ID:
                return SQLContract.GameEntry.GAME_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case GAME:
                return insertGame(match, uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    private Uri insertGame(int match, Uri uri, ContentValues values) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = -1;

        if (match == GAME || match == GAME_ID)
            id = database.insert(SQLContract.GameEntry.TABLE_GAME, null, values);
        else
            id = -1;

        if (id == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case GAME:
                rowsDeleted = database.delete(SQLContract.GameEntry.TABLE_GAME, selection, selectionArgs);
                break;
            case GAME_ID:
                selection = SQLContract.GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SQLContract.GameEntry.TABLE_GAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case GAME:
                return updateGame(match, uri, values, selection, selectionArgs);
            case GAME_ID:
                selection = SQLContract.GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateGame(match, uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
    private int updateGame(int match, Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = 0;

        if (match == GAME || match == GAME_ID)
            rowsUpdated = database.update(SQLContract.GameEntry.TABLE_GAME, values, selection, selectionArgs);
        else
            rowsUpdated = 0;

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
