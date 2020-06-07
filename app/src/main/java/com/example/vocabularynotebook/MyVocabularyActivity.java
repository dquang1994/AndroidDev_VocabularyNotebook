package com.example.vocabularynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyVocabularyActivity extends AppCompatActivity {
    DatabaseWrapper databaseWrapper;
    WordAdditionDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vocabulary);

        databaseWrapper = new DatabaseWrapper(this);
        dialog = new WordAdditionDialog();

        /*Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = databaseWrapper.selectAll();
                if(cursor.moveToFirst()) {
                    Log.i("Debug", cursor.getString(0));
                    Log.i("Debug", cursor.getString(1));
                }
            }
        });*/

        Cursor cursor = databaseWrapper.selectAll();
        //cursor.moveToFirst();
        /*Log.i("Debug", cursor.getString(1));
        Log.i("Debug", cursor.getString(2));*/

        CursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{DatabaseWrapper.COL_ENGLISH, DatabaseWrapper.COL_VIETNAMESE},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
        ListView listView = findViewById(R.id.voca_list);
        listView.setAdapter(adapter);

        /*CursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_my_vocabulary,
                cursor,
                new String[]{DatabaseWrapper.COL_ENGLISH, DatabaseWrapper.COL_VIETNAMESE},
                new int[]{R.id.txtMyVocaEng, R.id.txtMyVocaVn},
                0);
        ListView listView = findViewById(R.id.voca_list);
        listView.setAdapter(adapter);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseWrapper.closeDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_vocabulary_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                dialog.show(getSupportFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}