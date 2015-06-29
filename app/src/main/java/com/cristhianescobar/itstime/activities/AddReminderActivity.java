package com.cristhianescobar.itstime.activities;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cristhianescobar.itstime.ActivityComponent;
import com.cristhianescobar.itstime.ActivityModule;
import com.cristhianescobar.itstime.DaggerActivityComponent;
import com.cristhianescobar.itstime.R;
import com.cristhianescobar.itstime.ReminderApplication;
import com.cristhianescobar.itstime.data.Reminder;
import com.cristhianescobar.itstime.utils.RevealTransition;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by cristhian.escobar on 6/20/15.
 */
public class AddReminderActivity extends Activity {

    private Toolbar toolbar;
    private View searchView;

    private static final int COLOR_TRANSITION_TIME = 500;
    public static final String CENTER_X = "CENTER_X";
    public static final String CENTER_Y = "CENTER_Y";

    public static final String MESSAGE_TO_SCHEDULE = "message_schedule";

    private int revealAnimationCX;
    private int revealAnimationCY;

    @Bind(R.id.message_body)
    EditText mMessage;

    private ActivityComponent component;

    @Inject Picasso picasso;

    @Bind(R.id.image) ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        component().inject(this);
        ButterKnife.bind(this);

        setupRevealTransition();

    }

    public ActivityComponent component() {
        if(component == null) {
            // IMPORTANT:: Build
            component = DaggerActivityComponent.builder()
                    .appComponent(((ReminderApplication) getApplication()).component())
                    .activityModule(new ActivityModule(this)).build();
        }
        return component;
    }

    @OnClick(R.id.add_new_reminder)
    public void addNewReminder(View view) {
        Intent returnIntent = new Intent();
        Reminder m = new Reminder();
        m.name = String.valueOf(mMessage.getText());

        returnIntent.putExtra(MESSAGE_TO_SCHEDULE, m);
        setResult(HomeActivity.PICKED_TEXT_REMINDER,returnIntent);
        finish();
    }

    @OnClick(R.id.button)
    public void imageLoad(){
        picasso.load("http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg")
                .into(imageView);
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

    @TargetApi(21)
    private void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(color);
        }
    }
}