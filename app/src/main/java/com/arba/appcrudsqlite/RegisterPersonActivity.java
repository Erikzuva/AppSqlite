package com.arba.appcrudsqlite;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.arba.appcrudsqlite.Controller.PersonDao;
import com.arba.appcrudsqlite.Model.Person;

public class RegisterPersonActivity extends AppCompatActivity {
    EditText name,phoneNumber,emailAddress;
    RadioButton  male,female;
    Button add,clean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);
        initializer();
    }

    private void initializer() {
        name= (EditText) findViewById(R.id.editName);
        phoneNumber= (EditText) findViewById(R.id.editPhone);
        emailAddress= (EditText) findViewById(R.id.editEmail);
        male= (RadioButton) findViewById(R.id.rbtnMale);
        female= (RadioButton) findViewById(R.id.rbtnFemale);
        add= (Button) findViewById(R.id.btnAdd);
        clean= (Button) findViewById(R.id.btnClean);
        generateEvent();
    }

    private Person getPerson() {
        Person person= new Person();
        person.setName(name.getText().toString());
        person.setPhone(phoneNumber.getText().toString());
        person.setEmail(emailAddress.getText().toString());
        if(male.isChecked()){
            person.setGender(1);
        }else if(female.isChecked()){
            person.setGender(2);
        }
        return person;
    }

    private void generateEvent() {
        add.setOnClickListener(v->{registerPerson();});
        clean.setOnClickListener(v->{cleaner();});
    }

    private void cleaner() {
        name.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");
        male.setChecked(false);
        female.setChecked(false);
        name.requestFocus();
    }

    private void registerPerson() {
        PersonDao personDao= new PersonDao(this);
        String message=checkAllfield();
        if(message=="ok"){
            Boolean flag=personDao.RegisterPerson(getPerson());
            if(flag){
                makeMessage("Information","Register successful",0);
            }else {
                makeMessage("Error","Register Failed",0);
            }
        }else {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }

    }

    private String checkAllfield() {
        String message;
        if(!checkField(name)){
            message= "Enter Name";
        }else if(!checkField(emailAddress)){
            message= "Enter Email Address";
        }else if(!checkField(phoneNumber)){
            message= "Enter Phone Number";
        }else if(!male.isChecked()&&!female.isChecked()){
            message= "Select Gender";
        }else {
            message= "ok";
        }
        return message;

    }
    private Boolean checkField(EditText editText){
        if(editText.getText().length()==0){editText.requestFocus(); return false;} return true;
    }

    private void makeMessage(String tittle,String message,int icon) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage(message).setIcon(icon).setTitle(tittle).show();
    }
}
