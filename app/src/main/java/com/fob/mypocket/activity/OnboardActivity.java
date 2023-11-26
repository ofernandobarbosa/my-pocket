package com.fob.mypocket.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fob.mypocket.R;
import com.fob.mypocket.adapter.AdapterOnboarding;
import com.fob.mypocket.config.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class OnboardActivity extends AppCompatActivity {

    private WormDotsIndicator wormDotsIndicator;
    private ViewPager viewPager;
    private ImageView nextButton, backButton;
    private Button registerButton;
    private TextView alreadyRegistered;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        viewPager = findViewById(R.id.view_pager);
        nextButton = findViewById(R.id.next_img_button);
        backButton = findViewById(R.id.back_img_button);

        registerButton = findViewById(R.id.onboard_register_button);
        alreadyRegistered = findViewById(R.id.onboard_already_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardActivity.this, RegisterUserActivity.class ));
            }
        });

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardActivity.this, LoginActivity.class ));
            }
        });

        AdapterOnboarding adapter = new AdapterOnboarding(this);
        viewPager.setAdapter(adapter);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1, true);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    backButton.setVisibility(View.INVISIBLE);
                } else {
                    backButton.setVisibility(View.VISIBLE);
                }

                int lastPosition = adapter.getCount() - 1;
                if (position == lastPosition) {
                    nextButton.setVisibility(View.INVISIBLE);
                    registerButton.setVisibility(View.VISIBLE);
                    alreadyRegistered.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setVisibility(View.VISIBLE);
                    registerButton.setVisibility(View.INVISIBLE);
                    alreadyRegistered.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position < (adapter.getCount() - 1)) {
                            viewPager.setCurrentItem(position + 1, true);
                        } else {
                            startActivity(new Intent(OnboardActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(position - 1, true);
                    }
                });

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);
        wormDotsIndicator.attachTo(viewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseConfig.getFirebaseAuth();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(OnboardActivity.this, MainActivity.class));
            finish();
        }
    }

}