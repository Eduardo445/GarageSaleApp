package edu.csumb.esotorodriguez.garagesaleapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.io.File;

import edu.csumb.esotorodriguez.garagesaleapp.adapters.Item;
import edu.csumb.esotorodriguez.garagesaleapp.adapters.Post;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.GarageItemFragment;
import edu.csumb.esotorodriguez.garagesaleapp.fragments.NewPostFragment;

public class NewItemActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPrice;
    private EditText etDescription;
    private ImageView ivImage;
    private Button btnTakePicture;
    private Button btnAdd;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    public String photoFileName = "photo.jpg";
    private File photoFile;
    public static final String TAG = "NewItemActivity";
    private Item item;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        ivImage = findViewById(R.id.ivImage);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnAdd = findViewById(R.id.btnAdd);
        item = new Item();

        //some problem with capturing image
        /*
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });
        */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                Double price =  Double.parseDouble(etPrice.getText().toString());
                String description = etDescription.getText().toString();
                if (name.isEmpty() || price.isNaN() || description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "Some field is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                item.setDescription(description);
                item.setItemName(name);
                item.setPrice(price);
                //go back to NewPostFragment, parse back item object
                Bundle bundle = new Bundle();
                bundle.putParcelable(TAG, item);

                Fragment fragment = new NewPostFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.new_item_activity, fragment).commit();
                //onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(NewItemActivity.this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(NewItemActivity.this.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(NewItemActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                ivImage.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(NewItemActivity.this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}