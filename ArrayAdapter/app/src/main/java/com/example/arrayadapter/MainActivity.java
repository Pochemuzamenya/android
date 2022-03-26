package com.example.arrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<String> names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);

        String[] first_names = getResources().getStringArray(R.array.first_names); //Значение из values/strings.xml
        String[] last_names = getResources().getStringArray(R.array.last_names);
        int i = 0;
        for (String s : first_names) {
            String a = first_names[i].concat(" ").concat(last_names[i]);
            i++;
            names.add(a);
        }

        List<String> randomList = randomList(names);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, randomList); //Добавление значений в адаптер на layout item.xml
        lv.setAdapter(adapter);

        Button sortButton = findViewById(R.id.sort_button);
        sortButton.setOnClickListener(v -> {
            Collections.sort(randomList);
            adapter.notifyDataSetChanged();
        });

    }

    public List<String> randomList(List<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        int numberOfElements = 6;
        Random random = new Random();
        for (int i = 0; i < numberOfElements; i++) {
            int rIndex = random.nextInt(list.size());
            newList.add(list.get(rIndex));
        }
        return newList;
    }


}