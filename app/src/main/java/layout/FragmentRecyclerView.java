package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pranav.com.todolist.Data;
import pranav.com.todolist.MyAdapter;
import pranav.com.todolist.R;


public class FragmentRecyclerView extends Fragment {

    private RecyclerView toDoList;
    private static MyAdapter adapter;
    private static List<Data> toDos = new ArrayList<Data>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_recycler_view, container, false);

        toDoList = (RecyclerView) layout.findViewById(R.id.toDoList);
        adapter = new MyAdapter(getActivity(), getData());
        toDoList.setAdapter(adapter);
        toDoList.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        return layout;
    }

    public static List<Data> getData() {
        return toDos;
    }

    public static void putData(String str) {
        Data data = new Data(str);
        toDos.add(data);
        adapter.notifyDataSetChanged();
    }

    public static void editData(String str, int index) {
        Data data = new Data(str);
        toDos.set(index, data);
        adapter.notifyDataSetChanged();

    }

    public static void deleteData(int position) {
        toDos.remove(position);
    }

}
