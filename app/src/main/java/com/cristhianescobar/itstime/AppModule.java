package com.cristhianescobar.itstime;

import dagger.Module;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
@Module
public class AppModule {

    private final ReminderApplication app;

    public AppModule(ReminderApplication app) {this.app = app;}
}
