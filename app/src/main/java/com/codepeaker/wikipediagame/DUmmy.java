package com.codepeaker.wikipediagame;

import android.text.SpannableStringBuilder;

public class DUmmy {
    public static void main(String[] args) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(
                "I am Vishal Sharma"
        );

        spannableStringBuilder.replace(1,5,"dd");

        System.out.println(spannableStringBuilder);
    }
}
