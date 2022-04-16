package me.jakirniloy.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> values;


    public CustomEventAdapter(@NonNull Context context, @NonNull ArrayList<Event> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_note_row, parent, false);

        TextView Title = rowView.findViewById(R.id.tvNoteTitle);
        TextView DateTime = rowView.findViewById(R.id.tvEventDateTime);
        TextView Course_topic = rowView.findViewById(R.id.tvCorseTopic);
        TextView Course_code = rowView.findViewById(R.id.tvCourseCode);
        Event e =values.get(position);
        Title.setText(e.Title);
        DateTime.setText(e.Date_of_lecture);
        Course_topic .setText("Course Topic:"+e.Course_topic);
        Course_code.setText("Course Code:"+e.Course_code);

        return rowView;
    }
}
