package com.cristhianescobar.itstime.activities;

import android.app.Activity;
import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cristhianescobar.itstime.R;
import com.cristhianescobar.itstime.utils.RevealTransition;


/**
 * Created by cristhian.escobar on 6/20/15.
 */
public class AddReminderActivity extends Activity {

    private Toolbar toolbar;
    private View searchView;

    private static final int COLOR_TRANSITION_TIME = 500;
    public static final String CENTER_X = "CENTER_X";
    public static final String CENTER_Y = "CENTER_Y";

    private int revealAnimationCX;
    private int revealAnimationCY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        changeStatusBarColor(getResources().getColor(R.color.accent_color));


        setupRevealTransition();
//        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @TargetApi(21)
    private void setupRevealTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = getIntent();
            revealAnimationCX = intent.getIntExtra(CENTER_X, 0);
            revealAnimationCY = intent.getIntExtra(CENTER_Y, 0);

            RevealTransition revealTransition = new RevealTransition(revealAnimationCX, revealAnimationCY);
            revealTransition.excludeTarget(android.R.id.navigationBarBackground, true);

            Animator.AnimatorListener appearAnimationListener = new Animator.AnimatorListener() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(searchView != null) {
                        TransitionDrawable transition = (TransitionDrawable) searchView.getBackground();
                        transition.startTransition(COLOR_TRANSITION_TIME);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    if(searchView != null) {
                        TransitionDrawable transition = (TransitionDrawable) searchView.getBackground();
                        transition.startTransition(COLOR_TRANSITION_TIME);
                    }
                }

                @Override
                public void onAnimationRepeat(Animator animation) {}

                @Override
                public void onAnimationStart(Animator animation) {}
            };

            Animator.AnimatorListener disAppearAnimationListener = new Animator.AnimatorListener() {

                @Override
                public void onAnimationEnd(Animator animation) {}

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}

                @Override
                public void onAnimationStart(Animator animation) {
                    if(searchView != null) {
                        TransitionDrawable transition = (TransitionDrawable) searchView.getBackground();
                        transition.reverseTransition(COLOR_TRANSITION_TIME / 2);
                    }
                }
            };

            revealTransition.setOnAppearAnimationListener(appearAnimationListener);
            revealTransition.setDisappearAnimationListener(disAppearAnimationListener);
            getWindow().setEnterTransition(revealTransition);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent returnIntent = new Intent();
        setResult(3,returnIntent);
        finish();
    }

    @TargetApi(21)
    private void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(color);
        }
    }
}