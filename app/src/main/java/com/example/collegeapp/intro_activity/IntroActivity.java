package com.example.collegeapp.intro_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.college_app_sdk.classes.IntroViewPagerItem;
import com.example.collegeapp.HomeActivity;
import com.example.collegeapp.R;
import com.example.collegeapp.SignUpActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private static String SHARED_PREFERENCES = "privatePreferences";
    private static String IS_INTRO_OPENED = "isIntroOpened";
    private ViewPager mScreenPager;
    private IntroViewPagerAdapter mIntroViewPagerAdapter;
    private TabLayout mTabIndicator;
    private Button mButtonNext;
    private Button mButtonGetStarted;
    private Button mButtonSkip;
    private int pagerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: Check if the user is already logged in
        if(isIntroOpened()){
            openRegisterScreen();
        }

        makeActivityFullScreen();

        setContentView(R.layout.activity_intro);

        initViews();
        initAdapter();
        mScreenPager.setAdapter(mIntroViewPagerAdapter);
        mTabIndicator.setupWithViewPager(mScreenPager);
        setListeners();
    }

    private void initViews() {
        mScreenPager = findViewById(R.id.view_pager_intro);
        mTabIndicator = findViewById(R.id.intro_tab_indicator);
        mButtonNext = findViewById(R.id.intro_button_next);
        mButtonGetStarted = findViewById(R.id.intro_get_started_button);
        mButtonSkip = findViewById(R.id.intro_skip_button);
    }

    private void initAdapter() {
        List<IntroViewPagerItem> pagerItemList = new ArrayList<>();
        pagerItemList.add(new IntroViewPagerItem("Welcome students", "Welcome to our CollegeApp. Here you can find suggestions for the subject for your college.", R.drawable.ic_intro_student_reading));
        pagerItemList.add(new IntroViewPagerItem("Help to Others", "Help your younger colleagues chose the best fitting subjects for them and their interests.", R.drawable.ic_intro_students_experimenting));
        pagerItemList.add(new IntroViewPagerItem("Collaborate", "By collaborating with your colleagues you can make their life easier, by not making the mistakes you made. Happy collaborating!", R.drawable.ic_intro_students_learning));

        mIntroViewPagerAdapter = new IntroViewPagerAdapter(this, pagerItemList);
    }

    private void setListeners() {
        setButtonNextListener();
        setTabIndicatorListener();
        setGetStartedListener();
        setSkipButtonListener();
    }


    private void animateGetStartedButton() {
        Animation getStartedButtonAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_get_started_animation);
        getStartedButtonAnimation.setDuration(300);
        mButtonGetStarted.setAnimation(getStartedButtonAnimation);
    }


    private void setSkipButtonListener() {
        mButtonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupAndStartRegisterActivity();
            }
        });
    }

    private void setTabIndicatorListener() {
        mTabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mIntroViewPagerAdapter.getCount()-1){
                    showGetStartedButton();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setGetStartedListener(){
        mButtonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupAndStartRegisterActivity();
            }
        });
    }

    private void setupAndStartRegisterActivity() {
        setIntroActivityNotShowable();
        openRegisterScreen();
        finish();
    }

    private void setIntroActivityNotShowable() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_INTRO_OPENED, true);
        editor.apply();
    }

    private void setButtonNextListener() {
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerPosition = mScreenPager.getCurrentItem();
                if(pagerPosition < mIntroViewPagerAdapter.getCount()){
                    pagerPosition++;
                    mScreenPager.setCurrentItem(pagerPosition);
                }
                if(pagerPosition == mIntroViewPagerAdapter.getCount()-1){
                    showGetStartedButton();
                }
            }
        });
    }


    private boolean isIntroOpened() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_INTRO_OPENED,false);
    }

    private void openRegisterScreen() {
        //TODO: set intent for RegisterAcitivity
        Intent exampleIntent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(exampleIntent);
    }


    private void showGetStartedButton() {
        mButtonNext.setVisibility(View.GONE);
        mTabIndicator.setVisibility(View.GONE);
        mButtonSkip.setVisibility(View.GONE);
        mButtonGetStarted.setVisibility(View.VISIBLE);
        animateGetStartedButton();
    }

    private void makeActivityFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}