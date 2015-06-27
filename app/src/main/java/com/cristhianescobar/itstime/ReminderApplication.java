package com.cristhianescobar.itstime;

import android.app.Application;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
public class ReminderApplication extends Application {

    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //Connection between AppComponent and DaggerApplication
        component().inject(this);
    }

    public AppComponent component() {
        if(component == null) {
            // IMPORTANT:: Build
            component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
        return component;
    }

}
