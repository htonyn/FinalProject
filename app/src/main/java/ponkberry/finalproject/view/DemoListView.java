package ponkberry.finalproject.view;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponkberry.finalproject.MainViewPager;
import ponkberry.finalproject.R;
import ponkberry.finalproject.adapter.ListViewAdapter;
import ponkberry.finalproject.dialog.GameDialogFrag;
import ponkberry.finalproject.gameobject.GameObject;

/**
 * Created by Ponk on 4/6/2017.
 */

public class DemoListView extends LinearLayout implements AdapterView.OnItemClickListener {

    private final View view;
    private View view2;
    private LayoutInflater inflater;
    private MainViewPager context;
    private ListViewAdapter adapter;
    private ArrayList<GameObject> gameList = new ArrayList<>();
    public int achievementCountDemo = 0;

    @BindView(R.id.main_listview)
    ListView listView;

    public DemoListView(Context context) {
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
                                        achievementCountDemo+=gameList.get(position-1).getAchievements();
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
                                    achievementCountDemo+=gameList.get(position-1).getAchievements();
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
//        demoEditor.putInt("achievements", count+achievementCountDemo);
        adapter.notifyDataSetChanged();
    }

    private void populateGameList(ArrayList<GameObject> list) {
        list.add(new GameObject("Angels that Kill", 410680, "Demo", 2, 1, "0.1 Hours"));
        list.add(new GameObject("Blob From Space", 437150, "Demo", 1, 1, "0.1 Hours"));
        list.add(new GameObject("Blue Rose", 365630, "Demo", 2, 2, "0.1 Hours"));
        list.add(new GameObject("Concursion", 318080, "Demo", 2, 2, "0.3 Hours" ));
        list.add(new GameObject("Cursed Sight", 360560, "Demo", 2, 3, "0.1 Hours"));
        list.add(new GameObject("Flat Kingdom", 407370, "Demo", 2, 1, "0.5 Hours"));
        list.add(new GameObject("Mu Complex", 434640, "Demo", 2, 5, "0.2 Hours"));
        list.add(new GameObject("She Remembered Caterpillars", 470800, "Demo", 0, 4, "0.3 Hours"));
        list.add(new GameObject("Stanley Parable", 247750, "Demo", 2, 1, "0.5 Hours"));
        list.add(new GameObject("Teslagrad", 254560, "Demo", 2, 9, "0.5 Hours"));
        list.add(new GameObject("Without Within 2", 399010, "Demo", 2, 2, "0.1 Hours"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
