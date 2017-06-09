package pranav.com.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by pranav on 23/5/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<Data> data = Collections.emptyList();
    Realm realm;

    public MyAdapter(Context context, List<Data> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Realm.init(parent.getContext());
        realm = Realm.getDefaultInstance();
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String text, id, position;
                TextView textView = (TextView) view.findViewById(R.id.listText);
                TextView idView = (TextView) view.findViewById(R.id.id);
                TextView positionText = (TextView) view.findViewById(R.id.position);
                id = idView.getText().toString();
                text = textView.getText().toString();
                position = positionText.getText().toString();
                Intent intent = new Intent(view.getContext(), ToDoEdit.class);
                intent.putExtra("sentText", text);
                intent.putExtra("id", id);
                intent.putExtra("position", position);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Data current = data.get(position);
        holder.text.setText(current.getText());
        holder.idView.setText(current.getId());
        String pos =  String.valueOf(position + 1);
        holder.positionText.setText(pos);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmResults<Data> results = realm.where(Data.class).findAll();
                realm.beginTransaction();
                results.deleteFromRealm(position);
                realm.commitTransaction();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageButton deleteButton;
        TextView positionText;
        TextView idView;

        public MyViewHolder(View itemView) {
            super(itemView);
            idView = (TextView) itemView.findViewById(R.id.id);
            text = (TextView) itemView.findViewById(R.id.listText);
            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
            positionText = (TextView) itemView.findViewById(R.id.position);
        }
    }

}