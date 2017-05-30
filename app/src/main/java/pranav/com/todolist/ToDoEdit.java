package pranav.com.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import static layout.FragmentRecyclerView.editData;
import static layout.FragmentRecyclerView.putData;

public class ToDoEdit extends AppCompatActivity {

    String text, position;
    EditText editToDoText;
    ImageButton backButton;
    Button saveButton;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);

        editToDoText = (EditText) findViewById(R.id.toDoEdit);
        text = getIntent().getStringExtra("sentText");
        position = getIntent().getStringExtra("position");
        saveButton = (Button) findViewById(R.id.saveButton);
        backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText = editToDoText.getText().toString();
                pos = Integer.parseInt(position);
                if(pos == -1)
                    putData(editText);
                else
                    editData(editText, pos - 1);
                finish();
            }
        });

        editToDoText.setText(text);

    }

}
