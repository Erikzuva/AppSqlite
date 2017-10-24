package com.arba.appcrudsqlite;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.arba.appcrudsqlite.Controller.PersonDao;
import com.arba.appcrudsqlite.Model.Person;

public class PersonActivity extends AppCompatActivity {
    EditText id,name,phoneNumber,emailAddress;
    RadioButton male,female;
    Button update,delete,clean,search;
    PersonDao personDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initializer();
    }

    private void initializer() {
        id= (EditText) findViewById(R.id.editId);
        name= (EditText) findViewById(R.id.editName);
        phoneNumber= (EditText) findViewById(R.id.editPhone);
        emailAddress= (EditText) findViewById(R.id.editEmail);
        male= (RadioButton) findViewById(R.id.rbtnMale);
        female= (RadioButton) findViewById(R.id.rbtnFemale);
        update= (Button) findViewById(R.id.btnUpdate);
        search= (Button) findViewById(R.id.btnSearch);
        delete= (Button) findViewById(R.id.btnDelete);
        clean= (Button) findViewById(R.id.btnClean);
        generateEvents();
    }

    private void generateEvents() {
        update.setOnClickListener(v->{updatePerson();});
        delete.setOnClickListener(v->{deletePerson();});
        search.setOnClickListener(v->{personSearch();});
        clean.setOnClickListener(v->{cleaner();});
    }

    private void updatePerson() {
        String message=checkAllField();
        if(message=="ok"){
            personDao=new PersonDao(this);
            Boolean flag=personDao.UpdatePerson(getPerson());
            if(flag){
                makeMessage("Information","Update Successful",0);
            }else {
                makeMessage("Error","Update failed",0);
            }
        }else {
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }
    }

    private String checkAllField() {
        String message;
        if(!checkField(id)){
            message= "Enter Id";
        }else if(!checkField(name)){
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

    private void personSearch() {
        if(checkField(id)){
            personDao= new PersonDao(this);
            Person data=personDao.FindPersonById(getPerson().getId());
            if(data!=null){
                name.setText(data.getName());
                emailAddress.setText(data.getEmail());
                phoneNumber.setText(data.getPhone());
                if(data.getGender()==1){
                    male.setChecked(true);
                }else if(data.getGender()==2){
                    female.setChecked(true);
                }
            }else{
                cleaner();
                makeMessage("Error","Data no found",0);
            }
        }else {
            Toast.makeText(this, "Enter Id", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean checkField(EditText editText) {
        if(editText.getText().length()==0){editText.requestFocus();return false;}return true;
    }

    private void deletePerson() {
        if(checkField(id)){
            personDao= new PersonDao(this);
            boolean flag=personDao.DeletePerson(getPerson().getId());
            if(flag){
                cleaner();
                makeMessage("Information","Elimination successful",0);
            }else{
                makeMessage("Error","Elimination Failed",0);
            } 
        }else {
            Toast.makeText(this, "Enter Id", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeMessage(String tittle,String message,int icon) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(tittle).setMessage(message).setIcon(icon).show();
    }

    private void cleaner() {
        id.setText("");
        name.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");
        male.setChecked(false);
        female.setChecked(false);
        id.requestFocus();
    }

    private Person getPerson(){
        Person person= new Person();
        person.setId(Integer.valueOf(id.getText().toString()));
        person.setName(name.getText().toString());
        person.setPhone(phoneNumber.getText().toString());
        person.setEmail(emailAddress.getText().toString());
        if(male.isChecked()){
            person.setGender(1);
        }else if(female.isChecked()){
            person.setGender(2);
        }else {
            person.setGender(0);
        }
        return person;
    }

}
