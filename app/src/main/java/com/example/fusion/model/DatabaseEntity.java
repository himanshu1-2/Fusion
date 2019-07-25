package com.example.fusion.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseEntity extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

public static final String username ="username";
    public static final String designation ="designation";

    public DatabaseEntity(Context context) {
        super(context, "Database_name", null, 1);
        this.context = context;
        db = this.getWritableDatabase();


    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table userdata( ID Integer primary key Autoincrement,username text,designation text )";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addName(String name,String des) {
        ContentValues values = new ContentValues();
        values.put(username, name);
        values.put(designation,des);
        long rid = db.insert("userdata", null, values);
        if (rid > 0)
            Toast.makeText(context, "insert sucess", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "insert issue", Toast.LENGTH_SHORT).show();


    }

    public ArrayList getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UsersData> arrayList = new ArrayList<>();
        Cursor cursor = db.query("userdata", null, null, null,
                null, null, null);
        cursor.moveToFirst();
        do {
            UsersData userData=new UsersData();
            if (cursor.getCount() > 0)
            {
                String name=cursor.getString(1);
                String designation=cursor.getString(2);
                userData.setName(name);
                userData.setDesignation(designation);
                arrayList.add(userData);
            }

        } while (cursor.moveToNext());


        return arrayList;
    }

    public void onUpdate(String name,String name1) {
        ContentValues values1 = new ContentValues();
        values1.put("username", name1);

        long rid = db.update("userdata", values1, "username"+"=?" , new String[]{name});
        if (rid == 0)
        {
            Log.d("update", "onUpdate: "+rid);
            Toast.makeText(context, "no update", Toast.LENGTH_SHORT).show();}
        else
            Toast.makeText(context, "update sucess", Toast.LENGTH_SHORT).show();
    }


    public void onUpdatedes(String designation,String designation1) {
        ContentValues values1 = new ContentValues();
        values1.put("designation",designation1 );

        long rid = db.update("userdata", values1, "designation"+"=?" ,new String[]{designation});
        if (rid == 0)
        {
            Log.d("update", "onUpdate: "+rid);
            Toast.makeText(context, "no update", Toast.LENGTH_SHORT).show();}
        else
            Toast.makeText(context, "update sucess", Toast.LENGTH_SHORT).show();
    }





    public void onDelete(String name) {
        long rid = (long) db.delete("userdata", "username" + "=?",new String[] {name});
        if (rid == 0)
            Toast.makeText(context, "no delete", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

}
