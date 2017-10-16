package com.example.lamia.charitycalc.utility;

import android.app.Activity;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.WindowManager;

import com.example.lamia.charitycalc.R;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class UtilityClass {

    static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }
    public static String appendK(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return appendK(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + appendK(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void SetTranslucentSystemTray(Activity passedActivity) {
        passedActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        passedActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        passedActivity.getWindow().setStatusBarColor(ContextCompat.getColor(passedActivity, R.color.colorPrimaryDark));
    }

    public static float getDisplayWidth(Activity passedActivity) {
        Display display = passedActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (float) size.x;
    }


}
