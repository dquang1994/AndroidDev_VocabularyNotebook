package com.example.vocabularynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MainMenuItem> items = new ArrayList<MainMenuItem>();
    private static final int MYVOCA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainMenuItem item1 = new MainMenuItem(R.drawable.baseline_list_black_48, "My vocabulary");
        MainMenuItem item2 = new MainMenuItem(R.drawable.baseline_games_black_48, "Game");
        MainMenuItem item3 = new MainMenuItem(R.drawable.baseline_settings_black_48, "Setting");

        items.add(item1);
        items.add(item2);
        items.add(item3);

        AddressListAdapter adapter = new AddressListAdapter(items);
        ListView main_menu = findViewById(R.id.main_menu);
        main_menu.setAdapter(adapter);

        main_menu.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int whichItem, long id) {
                if(whichItem == MYVOCA){
                    Intent intent = new Intent(getApplicationContext(), MyVocabularyActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public class AddressListAdapter extends ArrayAdapter<MainMenuItem> {
        public AddressListAdapter(ArrayList<MainMenuItem> items) {
            super(getApplicationContext(), R.layout.main_menu_item, items);
        }

        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
                view = inflater.inflate(R.layout.main_menu_item, null);

                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setImageDrawable(getDrawable(items.get(whichItem).getImageID()));

                TextView textView = view.findViewById(R.id.textView);
                textView.setText(items.get(whichItem).getContent());
            }

            return view;
        }
    }
}
