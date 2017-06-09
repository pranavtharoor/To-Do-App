package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import pranav.com.todolist.Data;
import pranav.com.todolist.MyAdapter;
import pranav.com.todolist.R;


public class FragmentRecyclerView extends Fragment {

    private RecyclerView toDoList;
    private static MyAdapter adapter;
    private static List<Data> toDos = new ArrayList<Data>();

    Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getContext());
        realm = Realm.getDefaultInstance();
    }

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
        Realm realm = Realm.getDefaultInstance();
        toDos = realm.where(Data.class).findAll();
        return toDos;
    }

    public static void dataSetChanged() {
        adapter.notifyDataSetChanged();
    }

}