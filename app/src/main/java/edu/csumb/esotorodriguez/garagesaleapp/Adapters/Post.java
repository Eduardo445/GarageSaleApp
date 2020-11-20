package edu.csumb.esotorodriguez.garagesaleapp.Adapters;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_LOCATION = "location";
    public static final String KEY_NAME = "name";
    public static final String KEY_3 = "";

    public Post() {
        super();
    }

    public String getKeyLocation() {
        return getString(KEY_LOCATION);
    }

    public String getKeyName() {
        return getString(KEY_NAME);
    }
}
