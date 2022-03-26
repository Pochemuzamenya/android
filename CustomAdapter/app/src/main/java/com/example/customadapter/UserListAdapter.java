package com.example.customadapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class UserListAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<User> users;

    public UserListAdapter(Context ctx, ArrayList<User> users) {
        this.ctx = ctx;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date begin = new Date();
        User user = getItem(position);

        convertView = LayoutInflater.from(ctx).inflate(R.layout.useritem, parent, false);

        ImageView userPickView = convertView.findViewById(R.id.userpic);
        userPickView.setOnClickListener(v -> v.setBackgroundColor(Color.RED));

        TextView tvName = convertView.findViewById(R.id.name);
        TextView tvPhone = convertView.findViewById(R.id.phone);

        tvName.setText(user.name);
        tvPhone.setText(user.phoneNumber);
        switch (user.sex) {
            case MAN: userPickView.setImageResource(R.drawable.user_man);
                break;
            case WOMAN: userPickView.setImageResource(R.drawable.user_woman);
                break;
            case UNKNOWN: userPickView.setImageResource(R.drawable.user_unknown);
                break;
        }

        Date finish = new Date();
        Log.d("mytag", "getView time: " + (finish.getTime() - begin.getTime()));
        return convertView;
    }
}
