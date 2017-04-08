package ponkberry.finalproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.MainViewPager;
import ponkberry.finalproject.R;
import ponkberry.finalproject.adapter.ListViewAdapter;
import ponkberry.finalproject.gameobject.GameObject;

/**
 * Created by Ponk on 4/6/2017.
 */

public class F2PListView extends LinearLayout {

    private final View view;
    private MainViewPager context;
    private ListViewAdapter adapter;
    private ArrayList<GameObject> gameList = new ArrayList<>();

    @BindView(R.id.main_listview)
    ListView listView;

    public F2PListView(Context context) {
        super(context);
        this.context = (MainViewPager) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_view, this);
        ButterKnife.bind(this, view);
        initializeList();
    }

    private void initializeList() {
        populateGameList(gameList);
        adapter = new ListViewAdapter(this.context, gameList);
        listView.setAdapter(adapter);
    }

    private void populateGameList(ArrayList<GameObject> list) {
        list.add(new GameObject("404Sight", 361360, "F2P", false));
        list.add(new GameObject("Altitude", 41300, "F2P", false));
        list.add(new GameObject("Antenna", 443580, "F2P", false));
        list.add(new GameObject("Blind Trust", 468560, "F2P", false));
        list.add(new GameObject("Carpe Diem", 423880, "F2P", false));
        list.add(new GameObject("Cloney", 400030, "F2P", false));
        list.add(new GameObject("Dev Guy", 351800, "F2P", false));
        list.add(new GameObject("Emily is Away", 417860, "F2P", false));
        list.add(new GameObject("Free to Play", 245550, "F2P", false));
        list.add(new GameObject("Iron Snout", 424280, "F2P", false));
        list.add(new GameObject("Mandagon", 461560, "F2P", false));
    }
}
