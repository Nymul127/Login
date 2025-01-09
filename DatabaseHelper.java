package com.example.practice;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdetails.db";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "Password";
    private static final int VERSION_NUMBER = 2;
    private final Context context;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), "
            + EMAIL + " TEXT NOT NULL, "
            + USER_NAME + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME; //DROP TABLE IF EXISTS.

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context, "onCreate method is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {
            Toast.makeText(context, "Exception:" + e, Toast.LENGTH_SHORT).show();// show a toast or message
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "onUpgrade method is called", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "Exception:" + e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertdata(userdetails userdetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, userdetails.getName());
        contentValues.put(EMAIL, userdetails.getEmail());
        contentValues.put(USER_NAME, userdetails.getUsername());
        contentValues.put(PASSWORD, userdetails.getPassword());

        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;

    }
    public Boolean findPassword(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_NAME, null);
        Boolean result = false;

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No data is found", Toast.LENGTH_LONG).show();

        } else {
            while (cursor.moveToNext())
               {
               String usernam = cursor.getString(3);
               String passwor = cursor.getString(4);


            if (usernam.equals(username) && passwor.equals(password)) {
                result= true;
                break;
            }
           }
        }

return result;
    }


}