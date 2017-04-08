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

public class DemoListView extends LinearLayout {

    private final View view;
    private MainViewPager context;
    private ListViewAdapter adapter;
    private ArrayList<GameObject> gameList = new ArrayList<>();

    @BindView(R.id.main_listview)
    ListView listView;

    public DemoListView(Context context) {
        super(context);
        this.context = (MainViewPager) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_view, this);
        ButterKnife.bind(this, view);
        initializeList();
    }

    private void initializeList() {
        adapter = new ListViewAdapter(this.context, gameList);
        populateGameList(gameList);
        listView.setAdapter(adapter);
    }

    private void populateGameList(ArrayList<GameObject> list) {
        list.add(new GameObject("Angels that Kill", 410680, "Demo", false));
        list.add(new GameObject("Blob From Space", 437150, "Demo", false));
        list.add(new GameObject("Blue Rose", 365630, "Demo", false));
        list.add(new GameObject("Concursion", 318080, "Demo", false));
        list.add(new GameObject("Cursed Sight", 360560, "Demo", false));
        list.add(new GameObject("Flat Kingdom", 407370, "Demo", false));
        list.add(new GameObject("Mu Complex", 434640, "Demo", false));
        list.add(new GameObject("She Remembered Caterpillars", 470800, "Demo", false));
        list.add(new GameObject("Stanley Parable", 247750, "Demo", false));
        list.add(new GameObject("Teslagrad", 254560, "Demo", false));
        list.add(new GameObject("Without Within 2", 399010, "Demo", false));
    }
}
