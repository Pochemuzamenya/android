package com.example.customadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    UserListAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);


        // TODO: реализовать загрузку данных из JSON-файла
        // который загрузить в папку assets

        InputStream stream = null;
        try {
            stream = getAssets().open("test.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader reader = new InputStreamReader(stream);
        Gson gson = new Gson();
        User[] users1 = gson.fromJson(reader, User[].class);
        ArrayList<User> users = new ArrayList<User>(Arrays.asList(users1));

        adapter = new UserListAdapter(this, users);
        listView.setAdapter(adapter);

        Button phoneSortButton = findViewById(R.id.phone_sort_button);
        phoneSortButton.setOnClickListener(v -> {
            Collections.sort(users, new SortComparator());
            adapter.notifyDataSetChanged();
        });

        Button nameSortButton = findViewById(R.id.name_sort_button);
        nameSortButton.setOnClickListener(v -> {
            Collections.sort(users, new NameComparator());
            adapter.notifyDataSetChanged();
        });

        Button sexSortButton = findViewById(R.id.sex_sort_button);
        sexSortButton.setOnClickListener(v -> {
            Collections.sort(users, new SexComparator());
            adapter.notifyDataSetChanged();
        });
    }
}