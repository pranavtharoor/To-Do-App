package pranav.com.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

import static layout.FragmentRecyclerView.editData;
import static layout.FragmentRecyclerView.putData;

public class ToDoEdit extends AppCompatActivity {

    String text, position, id;
    EditText editToDoText;
    ImageButton backButton;
    Button saveButton;
    int pos;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);

        editToDoText = (EditText) findViewById(R.id.toDoEdit);
        text = getIntent().getStringExtra("sentText");
        position = getIntent().getStringExtra("position");
        id = getIntent().getStringExtra("id");
        saveButton = (Button) findViewById(R.id.saveButton);
        backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText = editToDoText.getText().toString();
                pos = Integer.parseInt(position);
                if(pos == -1) {
                    realm.beginTransaction();
                    Data data = realm.createObject(Data.class);
                    data.setText(editText);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    data.setId(dateFormat.format(date));
                    realm.commitTransaction();
                    putData(editText);
                }
                else {
                    realm.beginTransaction();
                    Data edit = realm.where(Data.class).equalTo("id", id).findFirst();
                    edit.setText(editText);
                    realm.commitTransaction();
                    editData(editText, pos - 1);
                }
                finish();
            }
        });

        editToDoText.setText(text);

    }

}