package com.arba.appcrudsqlite.utilities;

/**
 * Created by Erik on 19/10/2017.
 */

public class Utility {
    public static final String TABLE_PERSON = "PERSON";
    public static final String FIELD_ID = "ID";
    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_PHONE = "PHONE";
    public static final String FIELD_EMAIL = "EMAIL";
    public static final String FIELD_GENDER = "GENDER";

    public static final String CREATE_TABLE_PERSON = "CREATE TABLE " + TABLE_PERSON +"(" +
            FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_NAME + " TEXT," +
            FIELD_EMAIL + " TEXT," +
            FIELD_PHONE + " TEXT," +
            FIELD_GENDER + " INTEGER)";
    public static final String DROP_TABLE_PERSON = "DROP TABLE IF EXISTS " + TABLE_PERSON;
}
