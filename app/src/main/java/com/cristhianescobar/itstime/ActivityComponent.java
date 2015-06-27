package com.cristhianescobar.itstime;

import com.cristhianescobar.itstime.activities.HomeActivity;

import dagger.Component;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(HomeActivity mainActivity);

}