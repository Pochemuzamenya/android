package com.example.customadapter;

import java.util.Comparator;

public class SexComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o2.getSex().name().compareToIgnoreCase(o1.getSex().name());
    }
}
