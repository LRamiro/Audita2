package lrbresca.com.audita2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
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

import lrbresca.com.audita2.Adapters.PlacesChosenAdapter;

public class MainActivity extends Activity {

    private String APP_DIRECTORY = "/Audita2/";
    private String location_type = "location";
    private String room_type = "room";

    private String MEDIA_DIRECTORY = "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";
    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getBaseContext();
        ApplicationInfo appInfo = context.getApplicationInfo();
        int stringId = appInfo.labelRes;

        createDirectory(room_type, "test/subtest");




    }

    private void createDirectory(String type, String nameDirectory){
        if(type.equalsIgnoreCase(location_type)){

        }
        File file = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), nameDirectory);
        file.mkdirs();

    }


/*
    //View's components
    EditText etPlacesToBeChosen;
    Button bChosePlaces;
    ListView lvPlacesChosen;
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

    private boolean verifyPlace(String placeToVerify) {
        for (String s : places) {
            if ((s.equalsIgnoreCase(placeToVerify))) {
                return false;
            }
        }
        return true;
    }

    private void openCamera() {

        //We create an intent to start the camera.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createPartialDirectory();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PHOTO_CODE);
            }
        }
    }

    private File createPartialDirectory() throws IOException {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+ timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_CODE :
                if (resultCode == RESULT_OK) {
                    showDialog(places);

                }
                break;
        }
    }

    private void showDialog(final ArrayList<String> places) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Select your place:");
        CharSequence[] placesCharSequences = places.toArray(new CharSequence[places.size()]);
        builder.setItems(placesCharSequences, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.print("Muestro el dialog");
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
    }*/
}
