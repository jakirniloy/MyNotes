package me.jakirniloy.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //   Reference objects for handling event lists
    private ListView lNotes;
    private ArrayList<Event> events;
    private CustomEventAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCreateNew = findViewById(R.id.btncreat);
        Button btnExit= findViewById(R.id.btnexit);


        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,NoteInformation.class);
                startActivity(i);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lNotes = findViewById(R.id.lvNotes);
        loadData();

    }


    private void loadData(){
        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String NoteData = rows.getString(1);
            String[] fieldValues = NoteData.split("-::-");

            String Title = fieldValues[0];
            String Course_code = fieldValues[1];
            String Course_topic = fieldValues[2];
            String Date_of_lecture = fieldValues[3];
            String description =fieldValues[4];


            Event e = new Event(key, Title, Course_code, Course_topic,Date_of_lecture,description);
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
        lNotes.setAdapter(adapter);

        // handle the click on an event-list item
        lNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                Event e = events.get(position);
                System.out.println(position);
                Intent i = new Intent(MainActivity.this, NoteInformation.class);
                i.putExtra("EventKey", e.key);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //String message = "Do you want to delete event - "+events[position].name +" ?";
                String message = "Do you want to delete event - "+events.get(position).Title+" ?";
                System.out.println(message);
                //showDialog(message, "Delete Event", events.get(position).key);
                return true;
            }
        });
    }


    @Override
    public void onStart(){
        super.onStart();
        System.out.println("@MainActivity-onStart()");
    }
    @Override
    public void onResume(){
        super.onResume();
        System.out.println("@MainActivity-onResume()");
    }
    @Override
    public void onPause(){
        super.onPause();
        System.out.println("@MainActivity-onPause()");
    }
    @Override
    public void onStop(){
        super.onStop();
        System.out.println("@MainActivity-onStop()");
        // clear the event data from memory as the page is completely hidden by now
        events.clear();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        System.out.println("@MainActivity-onRestart()");
        // re-load events from database after coming back from the next page
        loadData();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("@MainActivity-onDestroy()");
    }


}

  
