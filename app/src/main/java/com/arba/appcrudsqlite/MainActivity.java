package com.arba.appcrudsqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.arba.appcrudsqlite.Model.Person;

public class MainActivity extends AppCompatActivity {
    Button btnRegister,btnConsult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
    }

    private void initializer() {
        btnRegister= (Button) findViewById(R.id.btnRegister);
        btnConsult= (Button) findViewById(R.id.btnCrud);
        GenerateEvents();
    }

    private void GenerateEvents() {
        btnRegister.setOnClickListener(v->{pressButton(this, RegisterPersonActivity.class);});
        btnConsult.setOnClickListener(v->{pressButton(this, PersonActivity.class);});
    }

    private void pressButton(Context context,Class aClass){
        Intent intent= new Intent(context,aClass);
        startActivity(intent);
    }


}
