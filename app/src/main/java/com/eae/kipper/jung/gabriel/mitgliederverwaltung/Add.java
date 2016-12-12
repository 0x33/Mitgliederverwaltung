package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    FloatingActionButton save;
    EditText nameInputText, nummerPrivatInputText, nummerMobilInputText, emailInputText, strasseInputText, plzInputText, ortInputText;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        save = (FloatingActionButton)findViewById(R.id.foating_save);
        save.setOnClickListener(handler);

        nameInputText = (EditText)findViewById(R.id.Input_Text_Name);
        nummerPrivatInputText = (EditText)findViewById(R.id.Input_Text_Nummer_Privat);
        nummerMobilInputText = (EditText)findViewById(R.id.Input_Text_Nummer_Mobil);
        emailInputText = (EditText)findViewById(R.id.Input_Text_Email);
        strasseInputText = (EditText)findViewById(R.id.Input_Text_Strasse);
        plzInputText = (EditText)findViewById(R.id.Input_Text_Plz);
        ortInputText = (EditText)findViewById(R.id.Input_Text_Ort);
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == save.getId()){
                String name = nameInputText.getText().toString();
                String nummerp = nummerPrivatInputText.getText().toString();
                String nummerm = nummerMobilInputText.getText().toString();
                String email = emailInputText.getText().toString();
                String strasse = strasseInputText.getText().toString();
                String plz = plzInputText.getText().toString();
                String ort = ortInputText.getText().toString();

                userDbHelper = new UserDbHelper(context);
                sqLiteDatabase = userDbHelper.getWritableDatabase();
                userDbHelper.addInformations(name, nummerp, nummerm, email, strasse, plz, ort, sqLiteDatabase);

                Toast.makeText(getApplicationContext(), "Mitglied wurde gespeichert", Toast.LENGTH_LONG).show();
                userDbHelper.close();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    };

}
