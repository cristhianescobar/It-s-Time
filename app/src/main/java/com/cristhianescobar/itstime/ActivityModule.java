package com.cristhianescobar.itstime;

import android.app.Activity;

import com.squareup.picasso.Picasso;

import dagger.Module;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
@ActivityScope
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    public Picasso getPicasso(AppComponent appComponent) {
        return appComponent.providePicasso();
    }
}
