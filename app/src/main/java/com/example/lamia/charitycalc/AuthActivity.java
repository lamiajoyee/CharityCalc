package com.example.lamia.charitycalc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AuthActivity extends AppCompatActivity {

    ImageButton sign_in_facebook, sign_up_facebook, sign_in_google, sign_up_google, sign_in_email;

    TextView sign_in_text, sign_up_text, or_text;

    Button skip_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        skip_button = (Button) findViewById(R.id.skip_button);
        sign_up_facebook = (ImageButton) findViewById(R.id.sign_up_facebook);

        sign_in_google = (ImageButton) findViewById(R.id.sign_in_google);
        sign_up_google = (ImageButton) findViewById(R.id.sign_up_google);

        or_text = (TextView) findViewById(R.id.or_text);

        sign_in_text = (TextView) findViewById(R.id.sign_in_text);
        sign_up_text = (TextView) findViewById(R.id.sign_up_text);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Anton-Regular.ttf");
        sign_up_text.setTypeface(custom_font);
        sign_in_text.setTypeface(custom_font);
        skip_button.setTypeface(custom_font);
        or_text.setTypeface(custom_font);
    }

}

