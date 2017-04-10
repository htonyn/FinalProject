package ponkberry.finalproject.view;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
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
import ponkberry.finalproject.dialog.GameDialogFrag;
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
    public int achievementCountF2P = 0;

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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (position != 0) {
                // Regular Dialog
//                    final GameDialog dialog = new GameDialog(getContext(), new GameDialog.IGameDialogEventListener() {
//                        @Override
//                        public void setUntouched() {
//                            gameList.get(position-1).setCompletion(0);
//                        }
//                        @Override
//                        public void setProgress() {
//                            gameList.get(position-1).setCompletion(1);
//                        }
//                        @Override
//                        public void setComplete() {
//                            gameList.get(position-1).setCompletion(2);
//                        }
//                    });
//                    dialog.show();

                    // Using DialogFragment
                    FragmentManager fm = context.getFragmentManager();
                    GameDialogFrag frag = GameDialogFrag.newInstance(gameList.get(position-1).getName(), gameList.get(position-1).getGenre(),
                            gameList.get(position-1).getCompletion(), gameList.get(position-1).getAchievements(), gameList.get(position-1).getTime(), new GameDialogFrag.IGameDialogEventListener() {
                                @Override
                                public void setUntouched() {
                                    if (gameList.get(position-1).getCompletion()==2) {
                                        achievementCountF2P+=gameList.get(position-1).getAchievements();
                                    }
                                    gameList.get(position-1).setCompletion(0);

                                }
                                @Override
                                public void setProgress() {
                                    gameList.get(position-1).setCompletion(1);
                                }
                                @Override
                                public void setComplete() {
                                    gameList.get(position-1).setCompletion(2);
                                    achievementCountF2P+=gameList.get(position-1).getAchievements();
                                }

                                @Override
                                public void writeToParcel(Parcel dest, int flags) {

                                }

                                @Override
                                public int describeContents() {
                                    return 0;
                                }
                            });
                    frag.show(fm, "game_dialog");
                    //int num, String name, String genre, int status, int achievements, String time, String changeButton, GameDialogFrag.IGameDialogEventListener listener
                }
            }
        });
        listView.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
//        SharedPreferences demoPreferences = context.getSharedPreferences("loginPrefs", context.MODE_PRIVATE);
//        SharedPreferences.Editor demoEditor = demoPreferences.edit();
//        int count = demoPreferences.getInt("achievements", 0);
//        demoEditor.putInt("achievements", count+achievementCountF2P);
        adapter.notifyDataSetChanged();
    }


    private void populateGameList(ArrayList<GameObject> list) {
        list.add(new GameObject("404Sight", 361360, "F2P", 2, 19, "1.5 Hours"));
        list.add(new GameObject("Altitude", 41300, "F2P", 0, 52, "0.4 Hours"));
        list.add(new GameObject("Antenna", 443580, "F2P", 2, 5, "0.4 Hours"));
        list.add(new GameObject("Blind Trust", 468560, "F2P", 0, 12, "1.5 Hours"));
        list.add(new GameObject("Carpe Diem", 423880, "F2P", 2, 1, "0.1 Hours"));
        list.add(new GameObject("Cloney", 400030, "F2P", 2, 7, "3.0 Hours"));
        list.add(new GameObject("Dev Guy", 351800, "F2P", 2, 7, "1.2 Hours"));
        list.add(new GameObject("Emily is Away", 417860, "F2P", 2, 20, "0.1 Hours"));
        list.add(new GameObject("Free to Play", 245550, "F2P", 2, 5, "0.9 Hours"));
        list.add(new GameObject("Iron Snout", 424280, "F2P", 2, 37, "5.0 Hours"));
        list.add(new GameObject("Mandagon", 461560, "F2P", 1, 5, "1.0 Hours"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
