package lrbresca.com.audita2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lrbresca.com.audita2.Utils.DirectoriesUtils;

public class PhotoActivity extends Activity implements AdapterView.OnItemSelectedListener {

    //View's components
    private TextView tvPlacesToBeChosen;
    private Spinner sPlacesToBeChosen;
    private TextView tvRoomsToBeChosen;
    private Spinner sRoomsToBeChosen;
    Button bTakePhotos;
    private final int REQUEST_IMAGE_CODE = 100;
    private String place = "";
    private String room = "";
    String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        DirectoriesUtils.verifyExternalStorageIsAvailable();

        tvPlacesToBeChosen = findViewById(R.id.tvPlacesToBeChosen);
        sPlacesToBeChosen = findViewById(R.id.sPlacesToBeChosen);
        tvRoomsToBeChosen = findViewById(R.id.tvRoomsToBeChosen);
        sRoomsToBeChosen = findViewById(R.id.sRoomsToBeChosen);
        bTakePhotos = findViewById(R.id.bTakePhotos);

        sPlacesToBeChosen.setOnItemSelectedListener(this);

        List<String> places = DirectoriesUtils.getDirectories(this);
        fillUpSpinners((ArrayList<String>) places);
        bTakePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        //sRoomsToBeChosen.set
    }

    private void fillUpSpinners(ArrayList<String> place){
        ArrayAdapter<String> arrayAdapterSpinnerPlaces = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, place);
        arrayAdapterSpinnerPlaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sPlacesToBeChosen.setAdapter(arrayAdapterSpinnerPlaces);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.sPlacesToBeChosen:
                place = (String) parent.getItemAtPosition(position);
                List<String> rooms = DirectoriesUtils.getSubdirectories(place);
                ArrayAdapter<String> arrayAdapterSpinnerRoom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
                arrayAdapterSpinnerRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sRoomsToBeChosen.setAdapter(arrayAdapterSpinnerRoom);
                sRoomsToBeChosen.setOnItemSelectedListener(this);

            case R.id.sRoomsToBeChosen:
                room = (String) parent.getItemAtPosition(position);

                //Obtener o mandar la dirección.
        }

        //sRoomsToBeChosen.setOnItemSelectedListener(this);
    }

    private void openCamera() {
//        String path = Environment.getRootDirectory() + File.separator
//                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        //Uri photoURI = FileProvider.getUriForFile(this,
          //      BuildConfig.APPLICATION_ID + ".fileprovider", file);

        //intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile(place, room);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI1 = FileProvider.getUriForFile(this,
                        "lrbresca.com.audita2.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI1);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_CODE:
                if (resultCode == RESULT_OK) {
                    Toast toast = Toast.makeText(this, "pruebaToast", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private File createImageFile(String place, String room) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(place);
        sb.append("/");
        sb.append(room);
        sb.append("/");
        String dir = sb.toString();
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES + dir);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    /*private boolean verificateStoreDisponibility() {


    }*/

   /* private List<String> placesToBeChosen(){

    }*/


}
