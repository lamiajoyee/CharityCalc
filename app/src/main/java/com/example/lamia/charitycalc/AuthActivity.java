package com.example.lamia.charitycalc;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;

public class AuthActivity extends AppCompatActivity {

    ImageButton sign_in_facebook, sign_up_facebook, sign_in_google, sign_up_google, sign_in_email;

    TextView sign_in_text, sign_up_text, or_text;

    LinearLayout sign_in_layout, sign_up_layout, skip_button_layout;

    Button skip_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //buttons
        skip_button = (Button) findViewById(R.id.skip_button);
        sign_up_facebook = (ImageButton) findViewById(R.id.sign_up_facebook);

        //layouts
        sign_in_layout = (LinearLayout) findViewById(R.id.sign_in_layout);
        sign_up_layout = (LinearLayout) findViewById(R.id.sign_up_layout);
        skip_button_layout = (LinearLayout) findViewById(R.id.skip_button_layout);

        //textviews
        or_text = (TextView) findViewById(R.id.or_text);
        sign_in_text = (TextView) findViewById(R.id.sign_in_text);
        sign_up_text = (TextView) findViewById(R.id.sign_up_text);

        //fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Anton-Regular.ttf");
        sign_up_text.setTypeface(custom_font);
        sign_in_text.setTypeface(custom_font);
        skip_button.setTypeface(custom_font);
        or_text.setTypeface(custom_font);


        ViewTreeObserver vto = skip_button_layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Put your code here.
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                float displayWidth = size.x;

                float skipButtonHeight = skip_button_layout.getHeight();

                sign_in_layout.setX(-displayWidth);
                sign_up_layout.setX(-displayWidth);
                skip_button_layout.setY(-skipButtonHeight);

                //animations
                final ObjectAnimator signInAnimation = ObjectAnimator.ofFloat(
                        sign_in_layout,
                        "translationX",
                        displayWidth,
                        0f
                );

                final ObjectAnimator signUpAnimation = ObjectAnimator.ofFloat(
                        sign_up_layout,
                        "translationX",
                        displayWidth,
                        0f
                );

                final ObjectAnimator skipAnimation = ObjectAnimator.ofFloat(
                        skip_button_layout,
                        "translationY",
                        skipButtonHeight,
                        0f
                );

                signInAnimation.setDuration(800);
                signUpAnimation.setDuration(1000);
                skipAnimation.setDuration(800);

                // Set the animation interpolator
                signInAnimation.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
                signUpAnimation.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
                skipAnimation.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));

                AnimatorSet animatorSet1 = new AnimatorSet();

                // Play the animations sequentially
                animatorSet1.playSequentially(signInAnimation, signUpAnimation, skipAnimation);
                animatorSet1.start();

                skip_button_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }

        });

    }

}

