package com.example.customadapter;

import java.util.Comparator;

public class SortComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(Integer.parseInt(o1.getPhoneNumber()), Integer.parseInt(o2.getPhoneNumber()));
    }
}
