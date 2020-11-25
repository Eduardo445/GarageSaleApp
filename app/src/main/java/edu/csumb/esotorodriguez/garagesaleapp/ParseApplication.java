package edu.csumb.esotorodriguez.garagesaleapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Item.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FxfjD8cDhRUX8TjYsvZbrbf83UrOHb5kL5nIrOmN")
                .clientKey("pGrKMbrgriUFJLkqL3ZOMJGw8pxc1JP2btjmXXUO")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
