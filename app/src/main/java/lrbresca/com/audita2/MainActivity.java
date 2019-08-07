package lrbresca.com.audita2;

import android.app.Activity;
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

import java.util.ArrayList;

import lrbresca.com.audita2.Adapters.PlacesChosenAdapter;

public class MainActivity extends AppCompatActivity {

    //View's components
    EditText etPlacesToBeChosen;
    Button bChosePlaces;
    ListView lvPlacesChosen;
    ArrayList<String> places;

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
        final PlacesChosenAdapter adapter = new PlacesChosenAdapter(this, android.R.layout.simple_list_item_2, places);
        lvPlacesChosen.setAdapter(adapter);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                places.add(etPlacesToBeChosen.getText().toString());
                etPlacesToBeChosen.getText().clear();
                adapter.notifyDataSetChanged();
            }
        };
        bChosePlaces.setOnClickListener(onClickListener);

        /*etPlacesToBeChosen.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if(i == EditorInfo.IME_ACTION_DONE){
                    places.add(etPlacesToBeChosen.getText().toString());
                    return true;
                }
                return false;
            }
        });*/

    }

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
}
