package com.cristhianescobar.itstime;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ReminderApplication application);
    Picasso providePicasso();
}
