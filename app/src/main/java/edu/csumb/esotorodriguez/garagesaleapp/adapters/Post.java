package edu.csumb.esotorodriguez.garagesaleapp.adapters;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@ParseClassName("Post")
@Parcel(analyze = Post.class)
public class Post extends ParseObject {

    public static final String KEY_USER = "userID";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_CREATED_KEY = "createdAt";

    public Post(){}

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public String getSaleName() {
        return getString(KEY_NAME);
    }

    public void setSaleName(String saleName) {
        put(KEY_NAME, saleName);
    }

    public String getLocation() {
        return getString(KEY_LOCATION);
    }

    public void setLocation(String location) {
        put(KEY_LOCATION, location);
    }
}
