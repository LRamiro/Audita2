package lrbresca.com.audita2.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class PlacesChosenAdapter extends ArrayAdapter<String> {

    public PlacesChosenAdapter(@NonNull Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
    }
}
