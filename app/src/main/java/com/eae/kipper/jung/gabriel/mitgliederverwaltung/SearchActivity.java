package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    Button suchen, loschen;
    TextView textSearchName, textSearchNummerPrivat, textSearchNummerMobil, textSearchEmail, textSearchStrasse, textSearchPlz, textSearchOrt;
    EditText Search_Name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        suchen = (Button)findViewById(R.id.Button_Suchen);
        loschen = (Button)findViewById(R.id.Button_Loschen);

        suchen.setOnClickListener(handler);
        loschen.setOnClickListener(handler);

        Search_Name = (EditText) findViewById(R.id.name_search);
        textSearchName = (TextView) findViewById(R.id.Text_Search_Name);
        textSearchNummerPrivat = (TextView) findViewById(R.id.Text_Search_Nummer_Privat);
        textSearchNummerMobil = (TextView) findViewById(R.id.Text_Search_Nummer_Mobil);
        textSearchEmail = (TextView) findViewById(R.id.Text_Search_Email);
        textSearchStrasse = (TextView) findViewById(R.id.Text_Search_Strasse);
        textSearchPlz = (TextView) findViewById(R.id.Text_Search_Plz);
        textSearchOrt = (TextView) findViewById(R.id.Text_Search_Ort);

        textSearchName.setVisibility(View.GONE);
        textSearchNummerPrivat.setVisibility(View.GONE);
        textSearchNummerMobil.setVisibility(View.GONE);
        textSearchEmail.setVisibility(View.GONE);
        textSearchStrasse.setVisibility(View.GONE);
        textSearchPlz.setVisibility(View.GONE);
        textSearchOrt.setVisibility(View.GONE);
    }


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == suchen.getId()){
                search_name = Search_Name.getText().toString();
                userDbHelper = new UserDbHelper(getApplicationContext());
                sqLiteDatabase = userDbHelper.getReadableDatabase();
                Cursor cursor = userDbHelper.getContact(search_name, sqLiteDatabase);

                if(cursor.moveToFirst()){
                    String NAME = cursor.getString(0);
                    String NUMMERP = cursor.getString(1);
                    String NUMMERM = cursor.getString(2);
                    String EMAIL = cursor.getString(3);
                    String STRASSE = cursor.getString(4);
                    String PLZ = cursor.getString(5);
                    String ORT = cursor.getString(6);

                    textSearchName.setText(NAME);
                    textSearchNummerPrivat.setText(NUMMERP);
                    textSearchNummerMobil.setText(NUMMERM);
                    textSearchEmail.setText(EMAIL);
                    textSearchStrasse.setText(STRASSE);
                    textSearchPlz.setText(PLZ);
                    textSearchOrt.setText(ORT);

                    textSearchName.setVisibility(View.VISIBLE);
                    textSearchNummerPrivat.setVisibility(View.VISIBLE);
                    textSearchNummerMobil.setVisibility(View.VISIBLE);
                    textSearchEmail.setVisibility(View.VISIBLE);
                    textSearchStrasse.setVisibility(View.VISIBLE);
                    textSearchPlz.setVisibility(View.VISIBLE);
                    textSearchOrt.setVisibility(View.VISIBLE);
                }
            }
            if(v.getId() == loschen.getId()){

            }
        }
    };

/*
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ITEM_SUCHEN:
                search_name = Search_Name.getText().toString();
                userDbHelper = new UserDbHelper(getApplicationContext());
                sqLiteDatabase = userDbHelper.getReadableDatabase();
                Cursor cursor = userDbHelper.getContact(search_name, sqLiteDatabase);

                if (cursor.moveToFirst()) {
                    String NAME = cursor.getString(0);
                    String NUMMERP = cursor.getString(1);
                    String NUMMERM = cursor.getString(2);
                    String EMAIL = cursor.getString(3);
                    String STRASSE = cursor.getString(4);
                    String PLZ = cursor.getString(5);
                    String ORT = cursor.getString(6);

                    textSearchName.setText(NAME);
                    textSearchNummerPrivat.setText(NUMMERP);
                    textSearchNummerMobil.setText(NUMMERM);
                    textSearchEmail.setText(EMAIL);
                    textSearchStrasse.setText(STRASSE);
                    textSearchPlz.setText(PLZ);
                    textSearchOrt.setText(ORT);

                    textSearchName.setVisibility(View.VISIBLE);
                    textSearchNummerPrivat.setVisibility(View.VISIBLE);
                    textSearchNummerMobil.setVisibility(View.VISIBLE);
                    textSearchEmail.setVisibility(View.VISIBLE);
                    textSearchStrasse.setVisibility(View.VISIBLE);
                    textSearchPlz.setVisibility(View.VISIBLE);
                    textSearchOrt.setVisibility(View.VISIBLE);
                    return true;
                }
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
    */
}


