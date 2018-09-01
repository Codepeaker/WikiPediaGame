package com.codepeaker.wikipediagame.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.codepeaker.wikipediagame.R;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.Random;

public class AppUtils {

    public static final String TAG = AppUtils.class.getSimpleName();

    private static ProgressDialog pDialog;

    public static void hidePDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }
    }

    public static void showPleaseWaitDialog(Context context) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(context);
            pDialog.setProgressDrawable(context.getResources().getDrawable(R.color.colorPrimaryDark));
            pDialog.setMessage(context.getString(R.string.please_wait));
            pDialog.setCancelable(false);
            if (!pDialog.isShowing()) {
//                pDialog.show();
            }
        }
    }

    public static void ptalToast(Context context) {
        Toast.makeText(context, context.getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show();
    }

    public static String[] getSentenceArray(String value) {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(value);
        int start = iterator.first();
        String[] stringArray = new String[10];
        int i = 0;
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            if (i == 10) {
                break;
            }
            stringArray[i++] = value.substring(start, end);
        }

        return stringArray;

    }

    public static int getRandomNumber(int start, int end) {
        Random rn = new Random();
        return  start + rn.nextInt(start - end + 1);
    }

    public static SpannableStringBuilder makeClickableSpan(final Context context, String s, final int i) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(s);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {

                Toast.makeText(context, i+"", Toast.LENGTH_SHORT).show();
            }

        };

        stringBuilder.setSpan(clickableSpan, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return stringBuilder;
    }
}
