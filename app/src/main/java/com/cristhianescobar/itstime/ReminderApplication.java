package com.cristhianescobar.itstime;

import android.app.Application;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
public class ReminderApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Connection between AppComponent and DaggerApplication
        component().inject(this);
    }

    public AppComponent component(){
        if(appComponent == null){
            // IMPORTANT:: Build
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
        return appComponent;
    }
}
