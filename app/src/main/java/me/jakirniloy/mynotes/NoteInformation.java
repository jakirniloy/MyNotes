package me.jakirniloy.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteInformation extends AppCompatActivity {
    private EditText editTitle,editCousreCode,editTopic,editDateofLecture,editDescrip;
    private String existingKey = null;
    TextView Error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_information);
        editTitle= findViewById(R.id.etTitle);
        editCousreCode = findViewById(R.id.coursecode);
        editTopic = findViewById(R.id.etCourseTopic);
        editDateofLecture =findViewById(R.id.etdate);
        editDescrip = findViewById(R.id.etDescription);
        Error = findViewById(R.id.errors);
        findViewById(R.id.btncancel).setOnClickListener(view -> finish());
        findViewById(R.id.btnsave).setOnClickListener(view -> save());
        // check in intent if there is any key set in putExtra
        Intent i = getIntent();
        existingKey = i.getStringExtra("EventKey");
        if(existingKey != null && !existingKey.isEmpty()) {
            initializeFormWithExistingData(existingKey);
        }


        }

    private void initializeFormWithExistingData(String eventKey){

        String value = Util.getInstance().getValueByKey(this, eventKey);
        System.out.println("Key: " + eventKey + "; Value: "+value);

        if(value != null) {
            String[] fieldValues = value.split("-::-");
            String Title = fieldValues[0];
            String Course_code = fieldValues[1];
            String Course_topic = fieldValues[2];
            String Date_of_lecture = fieldValues[3];
            String description =fieldValues[4];
            editTitle.setText(Title);
            editTopic.setText(Course_topic);
            editCousreCode.setText(Course_code);
            editDateofLecture.setText(Date_of_lecture);
            editDescrip.setText(description);

        }}

    private void save() {
        String Title = editTitle.getText().toString().trim();
        String Course_code = editCousreCode.getText().toString().trim();
        String Course_topic = editTopic.getText().toString().trim();
        String Date_of_lecture = editDateofLecture.getText().toString().trim();
        String Description =editDescrip.getText().toString().trim();
        String errors = "";
        if(Title.isEmpty() || Course_code.isEmpty() || Course_topic.isEmpty()){
            errors += "Title , Course Code ,Course Topic can't be Empty\n";
        }
        if (errors == "") {
            String key  = Title+"-::-"+Course_code;
            if(existingKey != null){
                key = existingKey;
            }
            String value = Title+"-::-"+Course_code+"-::-"+Course_topic+"-::-"+Date_of_lecture+"-::-"+Description;
           Util.getInstance().setKeyValue(this,key,value);
            Toast.makeText(this, "Event Information has been Saved Successfully", Toast.LENGTH_LONG).show();
            finish();
        } else {
           Error.setText(errors);
            //System.out.println("Error: " + errors);
        }




    }
}