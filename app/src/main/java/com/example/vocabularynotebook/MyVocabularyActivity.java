package com.example.vocabularynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyVocabularyActivity extends AppCompatActivity implements WordAdditionDialog.WordAdditionDialogCallback{
    private DatabaseWrapper databaseWrapper;
    private WordAdditionDialog wordAdditionDialog;
    private ListView vocabularyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vocabulary);

        databaseWrapper = new DatabaseWrapper(this);
        wordAdditionDialog = new WordAdditionDialog();
        wordAdditionDialog.setCallback(this);

        Cursor cursor = databaseWrapper.selectAll();
        VocabularyListAdapter adapter = new VocabularyListAdapter(getApplicationContext(), cursor);
        vocabularyList = findViewById(R.id.activity_my_vocabulary_vocabulary_list);
        vocabularyList.setAdapter(adapter);
    }

    @Override
    public void onOkClick() {
        CursorAdapter adapter = (CursorAdapter) vocabularyList.getAdapter();
        Cursor newCursor = databaseWrapper.selectAll();
        adapter.changeCursor(newCursor);
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
                wordAdditionDialog.show(getSupportFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class VocabularyListAdapter extends CursorAdapter{
        public VocabularyListAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.vocabulary_list_item, parent, false);

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView txtWord = view.findViewById(R.id.vocabulary_list_item_word);
            TextView txtMeaning = view.findViewById(R.id.vocabulary_list_item_meaning);

            txtWord.setText(cursor.getString(1));
            txtMeaning.setText(cursor.getString(2));

        }
    }
}