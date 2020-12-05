package edu.csumb.esotorodriguez.garagesaleapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import edu.csumb.esotorodriguez.garagesaleapp.R;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;

public class NewPostFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG ="NewPostFragment";
    private String mParam1;
    private String mParam2;
    private EditText etGarageSaleName;
    private EditText etLocation;
    private Button btnPost;
    private Post post;

    public NewPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getArguments() != null){
            post = getArguments().getParcelable("MainActivity");
        }
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etGarageSaleName = view.findViewById(R.id.etGarageSaleName);
        etLocation = view.findViewById(R.id.etLocation);
        btnPost = view.findViewById(R.id.btnPost);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String garageSaleName = etGarageSaleName.getText().toString();
                String location = etLocation.getText().toString();
                if (garageSaleName.isEmpty() || location.isEmpty()){
                    Toast.makeText(view.getContext(), "Field input is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                //save post into database
                Post post = new Post();
                post.setUser(ParseUser.getCurrentUser());
                post.setSaleName(garageSaleName);
                post.setLocation(location);
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null){
                            Log.e(TAG, "Error while saving", e);
                            Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.i(TAG, "Post save was successful");
                    }
                });
                assert getFragmentManager() != null;
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.garageContainer, new NewItemFragment(), "NewItemFragment");
                ft.commit();
            }
        });
    }

}