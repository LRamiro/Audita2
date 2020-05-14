package lrbresca.com.audita2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lrbresca.com.audita2.Adapters.PlacesChosenAdapter;
import lrbresca.com.audita2.Utils.DirectoriesUtils;

public class MainActivity extends AppCompatActivity {

    private String APP_DIRECTORY = "Audita2/";

    private Button bTakePhotoActivity;
    private Button bCreateDirectoryActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bTakePhotoActivity = findViewById(R.id.takePhotoActivity);
        View.OnClickListener bTakePhotoActivityListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentTakePhotoActivity = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(intentTakePhotoActivity);
            }
        };
        bTakePhotoActivity.setOnClickListener(bTakePhotoActivityListener);

        bCreateDirectoryActivity = findViewById(R.id.createDirectoryActivity);
        View.OnClickListener bCreateDirectoryActivityListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateDirectoryActivity = new Intent(getApplicationContext(), DirectoryActivity.class);
                startActivity(intentCreateDirectoryActivity);
            }
        };
        bCreateDirectoryActivity.setOnClickListener(bCreateDirectoryActivityListener);
    }


   /* private String APP_DIRECTORY = "Audita2/";
    private String MEDIA_DIRECTORY = "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";
    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    //View's components
    private EditText etPlacesToBeChosen;
    private Button bChosePlaces;
    private ListView lvPlacesChosen;
    ArrayList<String> places;
    Button bTakePhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the view`s components
        etPlacesToBeChosen = findViewById(R.id.etPlacesToBeChosen);
        bChosePlaces = findViewById(R.id.bChosePlaces);
        lvPlacesChosen = findViewById(R.id.lvPlacesChosen); 
        //Data resource
        places = new ArrayList<>();
        final PlacesChosenAdapter adapter = new PlacesChosenAdapter(this, android.R.layout.simple_list_item_1, places);
        lvPlacesChosen.setAdapter(adapter);
        bTakePhotos = findViewById(R.id.bStartTheCamera);
        bTakePhotos.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               openCamera();
                                           }
                                       }
        );
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String placeToAdd = etPlacesToBeChosen.getText().toString();
                if(verifyPlace(placeToAdd)){
                    places.add(placeToAdd);
                } else {
                    Toast.makeText(getApplicationContext(), "The place has already been chosen", Toast.LENGTH_SHORT).show();

                }
                etPlacesToBeChosen.getText().clear();
                adapter.notifyDataSetChanged();
            }
        };
        bChosePlaces.setOnClickListener(onClickListener);
    }

    //This method avoid to put a place already present into the list.
    private boolean verifyPlace(String placeToVerify) {
        for (String s : places) {
            if ((s.equalsIgnoreCase(placeToVerify))) {
                return false;
            }
        }
        return true;
    }

    private void openCamera() {
        File file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        file.mkdirs();
        String path = Environment.getRootDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                BuildConfig.APPLICATION_ID + ".fileprovider",
                file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PHOTO_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_CODE :
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    //showDialog(imageBitmap, places);

                }
                break;
        }
    }

    private void showOptionsToSave (final ArrayList<String> places) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Select your place:");
        CharSequence[] placesCharSequences = places.toArray(new CharSequence[places.size()]);
        builder.setItems(placesCharSequences, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //createDirectoryAndSaveFile(image, places.get(which));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //the user clicked on Cancel
            }
        });
        builder.show();
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/DirName");
        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/DirName/");
            wallpaperDirectory.mkdirs();
        }
        File file = new File(new File("/sdcard/DirName/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        *//*etPlacesToBeChosen.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if(i == EditorInfo.IME_ACTION_DONE){
                    places.add(etPlacesToBeChosen.getText().toString());
                    return true;
                }
                return false;
            }
        });*//*


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void dispatchTakePictureIntent() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                return;
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(createImageFile());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, PHOTO_CODE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  *//* prefix *//*
                ".jpg",         *//* suffix *//*
                storageDir      *//* directory *//*
        );

        // Save a file: path for use with ACTION_VIEW intents
        //mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }*/

}
