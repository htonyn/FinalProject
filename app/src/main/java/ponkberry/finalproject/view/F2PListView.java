package ponkberry.finalproject.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.MainViewPager;
import ponkberry.finalproject.R;
import ponkberry.finalproject.adapter.ListViewAdapter;
import ponkberry.finalproject.dialog.GameDialog;
import ponkberry.finalproject.gameobject.GameObject;

/**
 * Created by Ponk on 4/6/2017.
 */

public class F2PListView extends LinearLayout implements AdapterView.OnItemClickListener {

    private final View view;
    private View view2;
    private LayoutInflater inflater;
    private MainViewPager context;
    private ListViewAdapter adapter;
    private ArrayList<GameObject> gameList = new ArrayList<>();

    @BindView(R.id.main_listview)
    ListView listView;

    public F2PListView(Context context) {
        super(context);
        this.context = (MainViewPager) context;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_view, this);
        ButterKnife.bind(this, view);
        initializeList();
    }

    private void initializeList() {
        view2 = inflater.inflate(R.layout.list_header, this);
        populateGameList(gameList);
        adapter = new ListViewAdapter(this.context, gameList);
        View header = LayoutInflater.from(view2.getContext())
                .inflate(R.layout.list_header, listView, false);
                listView.addHeaderView(header);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final GameDialog dialog = new GameDialog(getContext(), new GameDialog.IGameDialogEventListener() {
                    @Override
                    public void setUntouched() {

                    }
                    @Override
                    public void setProgress() {

                    }
                    @Override
                    public void setComplete() {

                    }
                });
                dialog.show();
            }
        });
        listView.setAdapter(adapter);

    }

    private void populateGameList(ArrayList<GameObject> list) {
        list.add(new GameObject("404Sight", 361360, "F2P", 2));
        list.add(new GameObject("Altitude", 41300, "F2P", 0));
        list.add(new GameObject("Antenna", 443580, "F2P", 2));
        list.add(new GameObject("Blind Trust", 468560, "F2P", 0));
        list.add(new GameObject("Carpe Diem", 423880, "F2P", 2));
        list.add(new GameObject("Cloney", 400030, "F2P", 2));
        list.add(new GameObject("Dev Guy", 351800, "F2P", 2));
        list.add(new GameObject("Emily is Away", 417860, "F2P", 2));
        list.add(new GameObject("Free to Play", 245550, "F2P", 2));
        list.add(new GameObject("Iron Snout", 424280, "F2P", 2));
        list.add(new GameObject("Mandagon", 461560, "F2P", 1));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
