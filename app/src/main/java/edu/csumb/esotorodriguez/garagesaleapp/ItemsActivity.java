package edu.csumb.esotorodriguez.garagesaleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.GarageItemFragment;

import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

public class ItemsActivity extends AppCompatActivity {

    public static final String TAG = "ItemsActivity";
    private TextView tvGarageName;
    private TextView tvGarageOwner;
    private TextView tvGarageLocation;

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        tvGarageName = findViewById(R.id.tvGarageName);
        tvGarageOwner = findViewById(R.id.tvGarageOwner);
        tvGarageLocation = findViewById(R.id.tvGarageLocation);

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("garagePost"));
        assert post != null;
        tvGarageName.setText(post.getSaleName());
        tvGarageOwner.setText(post.getUser().getUsername());
        tvGarageLocation.setText(post.getLocation());

        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, post);

        Fragment fragment = new GarageItemFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.itemListing, fragment).commit();
    }
}