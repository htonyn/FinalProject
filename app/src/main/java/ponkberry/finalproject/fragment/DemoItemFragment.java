package ponkberry.finalproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ponkberry.finalproject.R;
import ponkberry.finalproject.adapter.fragment.DemoRecyclerViewAdapter;
import ponkberry.finalproject.gameobject.GameObject;

public class DemoItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DemoItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DemoItemFragment newInstance(int columnCount) {
        DemoItemFragment fragment = new DemoItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            ArrayList<GameObject> gameList = new ArrayList<>();
            gameList.add(new GameObject("Angels that Kill", 410680, "Demo", false));
            gameList.add(new GameObject("Blob From Space", 437150, "Demo", false));
            gameList.add(new GameObject("Blue Rose", 365630, "Demo", false));
            gameList.add(new GameObject("Concursion", 318080, "Demo", false));
            gameList.add(new GameObject("Cursed Sight", 360560, "Demo", false));
            gameList.add(new GameObject("Flat Kingdom", 407370, "Demo", false));
            gameList.add(new GameObject("Mu Complex", 434640, "Demo", false));
            gameList.add(new GameObject("She Remembered Caterpillars", 470800, "Demo", false));
            gameList.add(new GameObject("Stanley Parable", 247750, "Demo", false));
            gameList.add(new GameObject("Teslagrad", 254560, "Demo", false));
            gameList.add(new GameObject("Without Within 2", 399010, "Demo", false));

            recyclerView.setAdapter(new DemoRecyclerViewAdapter(gameList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        public void DemoFragmentInteraction(GameObject game);
    }
}
