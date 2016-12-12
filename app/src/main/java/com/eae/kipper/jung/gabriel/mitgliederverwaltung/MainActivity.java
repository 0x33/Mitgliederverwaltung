package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (FloatingActionButton)findViewById(R.id.foating_add);
        add.setOnClickListener(handler);

        listView = (ListView)findViewById(R.id.list_view);

        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformations(sqLiteDatabase);

        if(cursor.moveToFirst()){                       //return true wenn Datensatz vorhanden
            do{
                String name;
                name = cursor.getString(0);

                DataProvider dataProvider = new DataProvider(name);
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
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
