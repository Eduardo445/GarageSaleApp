package edu.csumb.esotorodriguez.garagesaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.ComposeFragment;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.GaragePostsFragment;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.NewItemFragment;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.NewPostFragment;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.ProfileFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //a very shitty work around to get the compose to work
        doesPostExist();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = new GaragePostsFragment();
                        break;
                    case R.id.action_compose:
                        /*
                        //tried this but it doesnt work
                        fragment = new ComposeFragment();
                         */
                        if(doesPostExist()){
                            fragment = new NewItemFragment();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(TAG, post);
                            fragment.setArguments(bundle);
                        }else{
                            fragment = new NewPostFragment();
                        }
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.garageContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
    private boolean doesPostExist(){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo("userID", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                }
                if (!objects.isEmpty()){
                    post = objects.get(0);
                }
            }
        });
        return post != null;
    }
}