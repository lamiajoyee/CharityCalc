package com.example.lamia.charitycalc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MonthWeekActivity extends AppCompatActivity {

    Button month_button, week_button, day_button;
    //TextView month_textview, week_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_week);

        //month_textview = (TextView) findViewById(R.id.month_textview);
        //week_textview = (TextView) findViewById(R.id.week_textview);

        month_button = (Button) findViewById(R.id.monthly);
        week_button = (Button) findViewById(R.id.weekly);
        day_button = (Button) findViewById(R.id.daily);

        SetFont();
}

    private void SetFont() {
        //fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Anton-Regular.ttf");
        month_button.setTypeface(custom_font);
        week_button.setTypeface(custom_font);
        day_button.setTypeface(custom_font);

    }


}
