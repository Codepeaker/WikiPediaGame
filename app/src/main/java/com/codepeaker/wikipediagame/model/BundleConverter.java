package com.codepeaker.wikipediagame.model;

import android.util.SparseArray;
import android.util.SparseIntArray;

public class BundleConverter {

    public static String[] getStringArray(StringBuilder[] stringBuilders) {
        String[] strings = new String[stringBuilders.length];
        for (int i = 0; i < stringBuilders.length; i++) {
            strings[i] = stringBuilders[i].toString();
        }
        return strings;
    }

    public static StringBuilder[] getStringBuilderArray(String[] strings) {
        StringBuilder[] stringBuilders = new StringBuilder[strings.length];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder("").append(strings[i]);
        }
        return stringBuilders;
    }

    public static String[] getStringArray(SparseArray<String> stringSparseArray) {
        String[] strings = new String[stringSparseArray.size()];
        for (int i = 0; i < stringSparseArray.size(); i++) {
            strings[i] = stringSparseArray.get(i);
        }
        return strings;
    }

    public static SparseArray<String> getSparshArray(String[] strings) {
        SparseArray<String> stringSparseArray = new SparseArray<>();
        for (int i = 0; i < strings.length; i++) {
            stringSparseArray.put(i, strings[i]);
        }
        return stringSparseArray;
    }

    public static int[] getIntArray(SparseIntArray sparseIntArray) {
        int[] ints = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            ints[i] = sparseIntArray.get(i);
        }
        return ints;
    }

    public static SparseIntArray getSparseIntArray(int[] ints) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        for (int i = 0; i < ints.length; i++) {
            sparseIntArray.put(i, ints[i]);
        }
        return sparseIntArray;
    }

}
