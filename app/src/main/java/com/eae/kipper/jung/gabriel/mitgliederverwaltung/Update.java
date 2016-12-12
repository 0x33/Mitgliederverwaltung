package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    FloatingActionButton update;
    EditText updateTextName, updateTextNummerPrivat, updateTextNummerMobil, updateTextEmail, updateTextStrasse, updateTextPlz, updateTextOrt;
    String Search_Name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update = (FloatingActionButton)findViewById(R.id.foating_update);
        update.setOnClickListener(handler);

        updateTextName = (EditText) findViewById(R.id.Update_Text_Name);
        updateTextNummerPrivat = (EditText) findViewById(R.id.Update_Text_Nummer_Privat);
        updateTextNummerMobil = (EditText) findViewById(R.id.Update_Text_Nummer_Mobil);
        updateTextEmail = (EditText) findViewById(R.id.Update_Text_Email);
        updateTextStrasse = (EditText) findViewById(R.id.Update_Text_Strasse);
        updateTextPlz = (EditText) findViewById(R.id.Update_Text_Plz);
        updateTextOrt = (EditText) findViewById(R.id.Update_Text_Strasse);
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == update.getId()){
                userDbHelper = new UserDbHelper(getApplicationContext());
                sqLiteDatabase = userDbHelper.getWritableDatabase();
                String name, nummerp, nummerm, email, strasse, plz, ort;
                name = updateTextName.getText().toString();
                nummerp = updateTextNummerPrivat.getText().toString();
                nummerm = updateTextNummerMobil.getText().toString();
                email = updateTextEmail.getText().toString();
                strasse = updateTextStrasse.getText().toString();
                plz = updateTextPlz.getText().toString();
                ort = updateTextOrt.getText().toString();
                int count = userDbHelper.updateInformation(Search_Name, name, nummerp, nummerm, email, strasse, plz, ort, sqLiteDatabase);
                Toast.makeText(getApplicationContext(), count + "Mitglieder geändert", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    };
}
