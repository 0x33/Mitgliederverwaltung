package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    Button suchen, loschen;
    TextView textSearchName, textSearchNummerPrivat, textSearchNummerMobil, textSearchEmail, textSearchStrasse, textSearchPlz, textSearchOrt;
    TextView labelSearchName, labelSearchNummerPrivat, labelSearchNummerMobil, labelSearchEmail, labelSearchStrasse, labelSearchPlz, labelSearchOrt;
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

        labelSearchName = (TextView)findViewById(R.id.Label_Search_Name);
        labelSearchNummerPrivat = (TextView)findViewById(R.id.Label_Search_Numemr_Privat);
        labelSearchNummerMobil = (TextView)findViewById(R.id.Label_Search_Numemr_Mobil);
        labelSearchEmail = (TextView)findViewById(R.id.Label_Search_Email);
        labelSearchStrasse = (TextView)findViewById(R.id.Label_Search_Strasse);
        labelSearchPlz = (TextView)findViewById(R.id.Label_Search_Plz);
        labelSearchOrt = (TextView)findViewById(R.id.Label_Search_Ort);

        textSearchName.setVisibility(View.GONE);
        textSearchNummerPrivat.setVisibility(View.GONE);
        textSearchNummerMobil.setVisibility(View.GONE);
        textSearchEmail.setVisibility(View.GONE);
        textSearchStrasse.setVisibility(View.GONE);
        textSearchPlz.setVisibility(View.GONE);
        textSearchOrt.setVisibility(View.GONE);

        labelSearchName.setVisibility(View.GONE);
        labelSearchNummerPrivat.setVisibility(View.GONE);
        labelSearchNummerMobil.setVisibility(View.GONE);
        labelSearchEmail.setVisibility(View.GONE);
        labelSearchStrasse.setVisibility(View.GONE);
        labelSearchPlz.setVisibility(View.GONE);
        labelSearchOrt.setVisibility(View.GONE);
        loschen.setVisibility(View.GONE);
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

                    labelSearchName.setVisibility(View.VISIBLE);
                    labelSearchNummerPrivat.setVisibility(View.VISIBLE);
                    labelSearchNummerMobil.setVisibility(View.VISIBLE);
                    labelSearchEmail.setVisibility(View.VISIBLE);
                    labelSearchStrasse.setVisibility(View.VISIBLE);
                    labelSearchPlz.setVisibility(View.VISIBLE);
                    labelSearchOrt.setVisibility(View.VISIBLE);
                    loschen.setVisibility(View.VISIBLE);
                }
            }
            if(v.getId() == loschen.getId()){
                deleteContact();                                                                    //wegen this das gibt einen fehler

            }
        }
    };

    private void deleteContact(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.confirm_title);
        alertDialog.setMessage(R.string.confirm_message);
        TextView text = new TextView(this);                                                         //hier
        text.setPadding(10, 10, 10, 10);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        alertDialog.setPositiveButton(R.string.button_loschen,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                R.string.toast_loschen,
                                Toast.LENGTH_LONG).show();
                        userDbHelper = new UserDbHelper(getApplicationContext());
                        sqLiteDatabase = userDbHelper.getReadableDatabase();
                        userDbHelper.deleteInformation(search_name, sqLiteDatabase);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton(R.string.button_abbrechen,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                R.string.toast_abbrechen,
                                Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialog.create();
        alertDialog.show();
    }
}


