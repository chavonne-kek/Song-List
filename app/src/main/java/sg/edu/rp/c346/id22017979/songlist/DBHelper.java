package sg.edu.rp.c346.id22017979.songlist;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 3;
    private static final String DATABASE_NAME = "songsTable.db";

    private static final String TABLE_TASK = "song";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "songTitle";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT,"
                + COLUMN_YEAR + " TEXT,"
                + COLUMN_RATING + " INTEGER)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        // Create table(s) again
        onCreate(db);

    }

    public void insertSong(String song, String singer, String year, int rating) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song);
        values.put(COLUMN_SINGER, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        db.insert(TABLE_TASK, null, values);
        db.close();
    }


    public ArrayList<Song> getSongs() {
        ArrayList<Song> tasks = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String song = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                int rating = cursor.getInt(4);

                Song obj = new Song(id, song, singer, year, rating);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public ArrayList<Song> getFilteredSongs() {
        ArrayList<Song> song = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_RATING};
        String selection = COLUMN_RATING + " Like ?";
        String[] args = {"5"};
        Cursor cursor = db.query(TABLE_TASK, columns, selection, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String songs = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                int rating = cursor.getInt(4);

                Song obj = new Song(id, songs, singer, year, rating);
                song.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return song;
    }
    public int updateSong(Song newSong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newSong.getSong());
        values.put(COLUMN_SINGER, newSong.getSinger());
        values.put(COLUMN_RATING, newSong.getRating());
        values.put(COLUMN_YEAR, newSong.getYear());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(newSong.getId())};
        int result = db.update(TABLE_TASK, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TASK, condition, args);
        db.close();
        return result;

    }

}

