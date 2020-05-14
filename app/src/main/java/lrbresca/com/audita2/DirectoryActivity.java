package lrbresca.com.audita2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lrbresca.com.audita2.Utils.DirectoriesUtils;

public class DirectoryActivity extends Activity {

    private boolean isDirectoryCreated = false;

    private static String toastMessage = "You have created the  %s directory";

    //View's components
    private TextView tvPlaceToBeAddedToDirectory;
    private EditText etPlaceToBeAddedToDirectory;
    private TextView tvRoomsToBeChosen;
    private EditText etRoomsToBeAddedToDirectory;
    private Button bCreateDirectory;
    private Button bFinishCreationDirectories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_directory);

        tvPlaceToBeAddedToDirectory = findViewById(R.id.tvPlacesToBeChosen);
        etPlaceToBeAddedToDirectory = findViewById(R.id.etPlaceToBeAddedToDirectory);
        tvRoomsToBeChosen = findViewById(R.id.tvRoomsToBeChosen);
        etRoomsToBeAddedToDirectory = findViewById(R.id.etRoomsToBeAddedToDirectory);
        bCreateDirectory = findViewById(R.id.bCreateDirectory);
        bCreateDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append("/");
                sb.append(etPlaceToBeAddedToDirectory.getText().toString());
                sb.append("/");
                sb.append(etRoomsToBeAddedToDirectory.getText().toString());
                sb.append("/");
                isDirectoryCreated = DirectoriesUtils.createDirectory(getApplicationContext(), sb.toString());
                if (isDirectoryCreated) {
                    showToastMessage(sb.toString());

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DirectoryActivity.this);
                    builder.setMessage(R.string.directoryActivity_directory_existed_alert_dialog);
                    builder.setPositiveButton(R.string.directoryActivity_directory_existed_alert_dialog_possitive_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        });

        bFinishCreationDirectories = findViewById(R.id.bFinishCreateDirectories);
        bFinishCreationDirectories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showToastMessage(String nameDirectory) {
        String value = String.format(toastMessage, nameDirectory);
        Toast directoryCreatedToast = Toast.makeText(getApplicationContext(),
                value,
                Toast.LENGTH_SHORT);
        directoryCreatedToast.show();
    }
}
