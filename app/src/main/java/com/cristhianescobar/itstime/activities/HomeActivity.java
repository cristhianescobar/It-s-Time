package com.cristhianescobar.itstime.activities;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cristhianescobar.itstime.R;
import com.cristhianescobar.itstime.adapters.CEPageAdapter;
import com.cristhianescobar.itstime.fragments.ReminderListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar) Toolbar mToolbar;
    @InjectView(R.id.viewpager) ViewPager mViewPager;
    @InjectView(R.id.sliding_tabs) TabLayout tabLayout;
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;
    private CEPageAdapter moviesPagerAdapter;

    @InjectView(R.id.add_reminder)
    FloatingActionButton mAddReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        initToolbar();
        initDrawer();
        initTabs();
    }

    @OnClick(R.id.add_reminder)
    public void addToList(View view) {
//        Toast.makeText(this, "What", Toast.LENGTH_SHORT).show();
        startSearchActivity(findViewById(R.id.add_reminder));
    }

    private void initTabs() {
        moviesPagerAdapter = new CEPageAdapter(getSupportFragmentManager());
        moviesPagerAdapter.addPage(new ReminderListFragment(), getResources().getString(R.string.reminder_list));
//        moviesPagerAdapter.addPage(new ReminderListFragment(), getResources().getString(R.string.reminder_list));

        mViewPager.setAdapter(moviesPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initToolbar() {
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.black));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(21)
    private void startSearchActivity(View view) {

        Intent intent = new Intent(HomeActivity.this, AddReminderActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int[] coordinates = new int[2];
            view.getLocationInWindow(coordinates);

            int cx = (int) (coordinates[0] + view.getWidth() / 2.0);
            int cy = (int) (coordinates[1] + view.getHeight() / 2.0);

            intent.putExtra(AddReminderActivity.CENTER_X, cx);
            intent.putExtra(AddReminderActivity.CENTER_Y, cy);

            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
