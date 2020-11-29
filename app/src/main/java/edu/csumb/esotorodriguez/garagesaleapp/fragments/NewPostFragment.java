package edu.csumb.esotorodriguez.garagesaleapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import edu.csumb.esotorodriguez.garagesaleapp.NewItemActivity;
import edu.csumb.esotorodriguez.garagesaleapp.R;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.ItemAdapter;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG ="NewPostFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etGarageSaleName;
    private EditText etLocation;
    private Button btnPost;
    private Post post;

    public NewPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPostFragment newInstance(String param1, String param2) {
        NewPostFragment fragment = new NewPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
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

                /*
                FragmentManager fragmentManager = getChildFragmentManager();
                Fragment fragment = new NewItemFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(TAG, post);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.compose_fragment, fragment).commit();
                */
            }
        });
    }

}