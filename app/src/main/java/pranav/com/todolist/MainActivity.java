package pranav.com.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    Button addTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTodo = (Button) findViewById(R.id.addTodo);

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ToDoEdit.class);
                intent.putExtra("sentText", "");
                intent.putExtra("position", "-1");
                view.getContext().startActivity(intent);
            }
        });

    }

}
