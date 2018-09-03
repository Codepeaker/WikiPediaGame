package com.codepeaker.wikipediagame.utils;

import android.text.TextUtils;

import java.util.Random;

public class StringUtils {

    public static StringBuilder[] cleanEmptySpace(StringBuilder[] tempBuilder) {

        int size = 0;
        while (size < tempBuilder.length) {
            if (TextUtils.isEmpty(tempBuilder[size])) {
                break;
            }
            size++;
        }
        StringBuilder[] stringBuilders = new StringBuilder[size];
        for (int j = 0; j < size; j++) {
            stringBuilders[j] = tempBuilder[j];
        }

        return stringBuilders;
    }

    public static StringBuilder[] setChoosenString(String choosen, String replaceWith, StringBuilder[] stringBuilders
            , int position) {

        StringBuilder stringBuilder = stringBuilders[position];

        stringBuilders[position] = stringBuilder.replace(
                stringBuilder.indexOf(replaceWith)
                , stringBuilder.indexOf(replaceWith) + replaceWith.length(), choosen);

        return stringBuilders;
    }

    public static String[] randomize(String arr[], int n) {
        Random r = new Random();

        for (int i = n - 1; i > 0; i--) {

            int j = r.nextInt(i);

            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;

    }

}
