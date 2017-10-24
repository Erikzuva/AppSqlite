package com.arba.appcrudsqlite.Resources;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arba.appcrudsqlite.utilities.Utility;

/**
 * Created by Erik on 19/10/2017.
 */

public class ConnectionOpenHelper extends SQLiteOpenHelper {
    public ConnectionOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utility.CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Utility.DROP_TABLE_PERSON);
        onCreate(db);
    }
}
