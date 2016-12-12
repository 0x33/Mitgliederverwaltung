package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    TextView nameText, nummerpText, nummermText, emailText, strasseText, plzText, ortText;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameText = (TextView) findViewById(R.id.Text_Name);
        nummerpText = (TextView) findViewById(R.id.Text_Nummer_Privat);
        nummermText = (TextView) findViewById(R.id.Input_Text_Nummer_Mobil);
        emailText = (TextView) findViewById(R.id.Text_Email);
        strasseText = (TextView) findViewById(R.id.Text_Strasse);
        plzText = (TextView) findViewById(R.id.Text_Plz);
        ortText = (TextView) findViewById(R.id.Text_Ort);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
                return true;
            case R.id.action_delete:
                deleteContact();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteContact(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.confirm_title);
        alertDialog.setMessage(R.string.confirm_message);
        TextView text = new TextView(this);
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
                    }
                });

        alertDialog.create();
        alertDialog.show();
    }
}
