package com.cristhianescobar.itstime;

import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cristhian.escobar on 6/26/15.
 */
@Module
public class AppModule {

    private final ReminderApplication app;

    public AppModule(ReminderApplication app) {this.app = app;}

    @Singleton
    @Provides
    Picasso providePicasso() {
        return new Picasso.Builder(app).downloader(new OkHttpDownloader(createOkHttoClient())).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                //TODO:  Log Error properly
            }
        }).build();
    }

    private static OkHttpClient createOkHttoClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}
