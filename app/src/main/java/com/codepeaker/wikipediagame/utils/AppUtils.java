package com.codepeaker.wikipediagame.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.codepeaker.wikipediagame.R;

import java.text.BreakIterator;
import java.util.Locale;

public class AppUtils {

    public static final String TAG = AppUtils.class.getSimpleName();

    private static ProgressDialog pDialog;

    public static void hidePDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public static void showPleaseWaitDialog(Context context) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(context);
            pDialog.setProgressDrawable(context.getResources().getDrawable(R.color.colorPrimaryDark));
            pDialog.setMessage(context.getString(R.string.please_wait));
            pDialog.setCancelable(true);
        }
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public static void ptalToast(Context context) {
        Toast.makeText(context, context.getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show();
    }

    public static StringBuilder[] getSentenceArray(String value) {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(value);
        int start = iterator.first();
        StringBuilder[] stringBuilders = new StringBuilder[10];
        stringBuilders = initStringBuilder(stringBuilders);
        int i = 0;
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            if (i == 10 || TextUtils.isEmpty(value.substring(start, end))) {
                break;
            }
            stringBuilders[i++].append(value, start, end);
        }

        return stringBuilders;

    }

    private static StringBuilder[] initStringBuilder(StringBuilder[] stringBuilders) {
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder("");
        }
        return stringBuilders;
    }

    public static final String DASH_STRING = "______";


}
