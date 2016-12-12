package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton)findViewById(R.id.foating_add);
        add.setOnClickListener(handler);

        listView = (ListView)findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Details.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
            }
        });

        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformations(sqLiteDatabase);

        if(cursor.moveToFirst()){                       //return true wenn Datensatz vorhanden
            do{
                String name, strasse, plz, ort;
                name = cursor.getString(0);
                strasse = cursor.getString(1);
                plz = cursor.getString(2);
                ort = cursor.getString(3);

                DataProvider dataProvider = new DataProvider(name, strasse, plz, ort);
                listDataAdapter.add(dataProvider);

            }while(cursor.moveToNext());                //return true wenn noch ein Datensatz da ist
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == add.getId()){
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
            }
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ITEM_SUCHEN:
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
