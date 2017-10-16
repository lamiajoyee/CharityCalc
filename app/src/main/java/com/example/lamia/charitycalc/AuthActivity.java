package com.example.lamia.charitycalc;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;
import com.example.lamia.charitycalc.controls.SquareImageButton;

import static com.example.lamia.charitycalc.utility.UtilityClass.SetTranslucentSystemTray;
import static com.example.lamia.charitycalc.utility.UtilityClass.getDisplayWidth;

public class AuthActivity extends AppCompatActivity {

    SquareImageButton sign_in_facebook, sign_up_facebook, sign_in_email, sign_up_email, sign_in_google, sign_up_google;

    TextView sign_in_text, sign_up_text, or_text;

    LinearLayout sign_in_layout, sign_up_layout, skip_button_layout;

    Button skip_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        InitializeControls();
        SetFont();

        setupWindowAnimations();

        ViewTreeObserver vto = skip_button_layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                float displayWidth = getDisplayWidth(AuthActivity.this);

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SetTranslucentSystemTray(this);
        }

    }

    private void InitializeControls() {
        //buttons
        skip_button = (Button) findViewById(R.id.skip_button);
        sign_in_facebook = (SquareImageButton) findViewById(R.id.sign_in_facebook);
        sign_up_facebook = (SquareImageButton) findViewById(R.id.sign_up_facebook);

        sign_in_google = (SquareImageButton) findViewById(R.id.sign_in_google);
        sign_up_google = (SquareImageButton) findViewById(R.id.sign_up_google);

        sign_in_email = (SquareImageButton) findViewById(R.id.sign_in_email);
        sign_up_email = (SquareImageButton) findViewById(R.id.sign_up_email);

        //layouts
        sign_in_layout = (LinearLayout) findViewById(R.id.sign_in_layout);
        sign_up_layout = (LinearLayout) findViewById(R.id.sign_up_layout);
        skip_button_layout = (LinearLayout) findViewById(R.id.skip_button_layout);

        //textviews
        or_text = (TextView) findViewById(R.id.or_text);
        sign_in_text = (TextView) findViewById(R.id.sign_in_text);
        sign_up_text = (TextView) findViewById(R.id.sign_up_text);
    }

    private void SetFont() {
        //fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Anton-Regular.ttf");
        sign_up_text.setTypeface(custom_font);
        sign_in_text.setTypeface(custom_font);
        skip_button.setTypeface(custom_font);
        or_text.setTypeface(custom_font);
    }

    public void skip_click(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void setupWindowAnimations() {
        //Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        //getWindow().setExitTransition(slide);
    }
}

