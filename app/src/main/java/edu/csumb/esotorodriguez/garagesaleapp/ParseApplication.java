package edu.csumb.esotorodriguez.garagesaleapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import edu.csumb.esotorodriguez.garagesaleapp.Adapters.Post;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FxfjD8cDhRUX8TjYsvZbrbf83UrOHb5kL5nIrOmN")
                .clientKey("pGrKMbrgriUFJLkqL3ZOMJGw8pxc1JP2btjmXXUO")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
