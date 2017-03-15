package com.example.cilo.userlocalaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by cilo on 3/14/17.
 */

public class LocalUserDatabase extends SQLiteOpenHelper {

    public LocalUserDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "userdb.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void insertUser(String username, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",username);
        contentValues.put("EMAIl",email);
        contentValues.put("PASSWORD",password);
        this.getWritableDatabase().insertOrThrow("user","",contentValues);
    }

    public void deleteUser(String username){
        this.getWritableDatabase().delete("user","USERNAME='"+username+"'",null);
    }

    public void updateUser(String old_username, String new_username){
        this.getWritableDatabase().execSQL("UPDATE user SET username='"+new_username+"' WHERE " +
                "username='"+old_username+"'");
    }

    public void listAllUsers(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM user", null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1) +" "+cursor.getString(2));
        }
    }
}
