package com.example.foodbae;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;

public class registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText userName,useremail;
    EditText userpassword;
    EditText date;
    private Button Signup;
    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener OnDateSetListener;
    private Button userLogin;
    private RadioGroup radioGroup;
    EditText editTextDate;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper = new DatabaseHelper(this);

        userName=(EditText)findViewById(R.id.userName);
        userpassword=(EditText)findViewById(R.id.password);
        useremail=(EditText)findViewById(R.id.useremail);
        Signup=(Button)findViewById(R.id.button);
        userLogin=(Button)findViewById(R.id.userLogin);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        editTextDate=(EditText)findViewById(R.id.date);

        Spinner spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(registration.this, OnDateSetListener ,year,month,day);
                datePickerDialog.show();

            }


        });
        OnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date= dayOfMonth+"-"+(month+1)+"-"+year;
                editTextDate.setText(date);
            }
        };



        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString();
                String password = userpassword.getText().toString();

                if(username.equals("") || password.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkusername = databaseHelper.CheckUsername(username);
                    if(checkusername == true){
                        Boolean insert = databaseHelper.Insert(username, password);
                        if(insert == true){
                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(registration.this,nav.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log_intent = new Intent(registration.this, login.class);
                startActivity(log_intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

