package com.android.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper
{


    public static final String Database_Name = "userdata.db";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String Column_Id ="ID";
    public static final String Column_Username = "USER_NAME";
    public static final String Column_Email = "USER_EMAIL";
    public static final String Column_Password= "USER_PASSWORD";

      User user;


    public DataBaseHelper(@Nullable Context context) {
        super(context,Database_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(

                "CREATE TABLE " + USER_TABLE + "("
                        + Column_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Column_Username + " TEXT,"
                        + Column_Email + " TEXT," + Column_Password + " TEXT" + ")"

        );

        //       db.execSQL("create table " + TABLE_NAME
        //                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
        //                + "  TASK TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE );
        onCreate(db);
    }



    public boolean addData(User user)
    {



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // if error occurs put id here as well
        cv.put(Column_Username, user.getUname());
        cv.put(Column_Email,user.getEmail_id());
        cv.put(Column_Password, user.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }

        else
        {
            return true;
        }



    }

    // Validate Username and Password and redirect user to Restaurant Menu

    public int Login(String username,String password)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = new String[]{username, password};
        try {
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("select * from USER_TABLE where USER_EMAIL=? and USER_PASSWORD=?", selectionArgs);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //





}
