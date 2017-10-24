package com.arba.appcrudsqlite.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arba.appcrudsqlite.Model.Person;
import com.arba.appcrudsqlite.Resources.ConnectionOpenHelper;
import com.arba.appcrudsqlite.utilities.Utility;

/**
 * Created by Erik on 19/10/2017.
 */

public class PersonDao {
    private Context context;

    public PersonDao(Context context) {
        this.context = context;
    }
    public ConnectionOpenHelper GetConnection(){
        return new ConnectionOpenHelper(context,"db_person",null,1);
    }
    public Boolean RegisterPerson(Person person){
        SQLiteDatabase db=GetConnection().getWritableDatabase();
        try{
            ContentValues values= new ContentValues();
            values.put(Utility.FIELD_NAME,person.getName());
            values.put(Utility.FIELD_EMAIL,person.getEmail());
            values.put(Utility.FIELD_PHONE,person.getPhone());
            values.put(Utility.FIELD_GENDER,person.getGender());
            db.insert(Utility.TABLE_PERSON,null,values);
            return true;
        }catch (Exception e){
            return false;
        }finally {
            if(db.isOpen()){db.close();};
        }
    }

    public Person FindPersonById(int id){
        Person person=new Person();
        SQLiteDatabase db=GetConnection().getReadableDatabase();
        try {
            String[] arguments={String.valueOf(id)};
            String[] values={Utility.FIELD_ID,Utility.FIELD_NAME,Utility.FIELD_EMAIL,Utility.FIELD_PHONE,Utility.FIELD_GENDER};
            Cursor cursor=db.query(Utility.TABLE_PERSON,values,Utility.FIELD_ID + "=?",arguments,null,null,null);
            cursor.moveToFirst();
            person.setId(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setEmail(cursor.getString(2));
            person.setPhone(cursor.getString(3));
            person.setGender(cursor.getInt(4));
            return person;
        }catch (Exception e){
            return null;
        }finally {
            if(db.isOpen()){db.close();};
        }
    }

    public Boolean DeletePerson(int id){
        SQLiteDatabase db=GetConnection().getWritableDatabase();
        try {
            String[] arguments={String.valueOf(id)};
            int flag= db.delete(Utility.TABLE_PERSON,Utility.FIELD_ID + "=?",arguments);
            if(flag!=0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }finally {
            if(db.isOpen()){db.close();};
        }
    }
    public Boolean UpdatePerson(Person person){
        SQLiteDatabase db=GetConnection().getWritableDatabase();
        try {
            String[] arguments={String.valueOf(person.getId())};
            ContentValues values= new ContentValues();
            values.put(Utility.FIELD_NAME,person.getName());
            values.put(Utility.FIELD_EMAIL,person.getEmail());
            values.put(Utility.FIELD_PHONE,person.getPhone());
            values.put(Utility.FIELD_GENDER,person.getGender());
            int flag= db.update(Utility.TABLE_PERSON,values,Utility.FIELD_ID + "=?",arguments);
            if(flag!=0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }finally {
            if(db.isOpen()){db.close();};
        }
    }
}
