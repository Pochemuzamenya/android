package com.example.sqlitebasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    ListView lv;
    EditText title;
    EditText artist;
    EditText year;
    EditText duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.playlist);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        displayData();

        title = this.findViewById(R.id.title);
        artist = this.findViewById(R.id.artist);
        year = this.findViewById(R.id.year);
        duration = this.findViewById(R.id.duration);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            ContentValues newValues = new ContentValues();
            String title = this.title.getText().toString();
            String artist = this.artist.getText().toString();
            String year = this.year.getText().toString();
            String duration = this.duration.getText().toString();
            newValues.put("title",title);
            newValues.put("artist",artist);
            newValues.put("year",year);
            newValues.put("duration",duration);
            db.insert(DBHelper.TABLE_NAME, null, newValues);
            displayData();
            this.title.getText().clear();
            this.artist.getText().clear();
            this.year.getText().clear();
            this.duration.getText().clear();

        });

        Button sortButton = findViewById(R.id.sort_button_duration);
        sortButton.setOnClickListener(v -> {
            Cursor ASCCursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME + " ORDER BY duration ASC; ", null);

            String[] ascCursorColumnNames = ASCCursor.getColumnNames();
            int[] views = {R.id.id, R.id.title, R.id.artist, R.id.year, R.id.duration};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, ASCCursor, ascCursorColumnNames, views, 0);
            lv.setAdapter(adapter);
        });

        Button sortByYear = findViewById(R.id.sort_button_year);
        sortByYear.setOnClickListener(v -> {
            Cursor DESCCursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME + " ORDER BY year DESC; ", null);
            String[] descCursorColumnNames = DESCCursor.getColumnNames();
            int[] views = {R.id.id, R.id.title, R.id.artist, R.id.year, R.id.duration};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, DESCCursor, descCursorColumnNames, views, 0);
            lv.setAdapter(adapter);

        });
    }
    // TODO: 1) реализовать добавление записей (данные вводит пользователь в текстовые поля)
    // TODO: 2) отображать общее число записей
    // TODO: 3*) сортировка по полям (по убыванию, возрастанию)

    public void displayData() { // отображаем данные на списке
        Cursor c = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        /* курсор - структура данных, можно обращаться к отдельным записям и полям
        c.getCount();
        c.moveToLast();
        c.getInt(1);

         */

        String[] table_columns = c.getColumnNames();
        int[] views = {R.id.id, R.id.title, R.id.artist, R.id.year, R.id.duration};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, c, table_columns, views, 0);

        Cursor cursorCount = db.rawQuery("SELECT SUM(duration) FROM " + dbHelper.TABLE_NAME,null);
        cursorCount.moveToNext();
        String sumDuration = cursorCount.getString(0);

        TextView view = this.findViewById(R.id.count);
        view.setText(sumDuration);

        lv.setAdapter(adapter);
    }
}