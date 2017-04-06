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

import ponkberry.finalproject.R;
import ponkberry.finalproject.adapter.fragment.F2PRecyclerViewAdapter;
import ponkberry.finalproject.gameobject.GameObject;

import java.util.ArrayList;

public class F2PItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public F2PItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static F2PItemFragment newInstance(int columnCount) {
        F2PItemFragment fragment = new F2PItemFragment();
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
            gameList.add(new GameObject("404Sight", 361360, "F2P", false));
            gameList.add(new GameObject("Altitude", 41300, "F2P", false));
            gameList.add(new GameObject("Antenna", 443580, "F2P", false));
            gameList.add(new GameObject("Blind Trust", 468560, "F2P", false));
            gameList.add(new GameObject("Carpe Diem", 423880, "F2P", false));
            gameList.add(new GameObject("Cloney", 400030, "F2P", false));
            gameList.add(new GameObject("Dev Guy", 351800, "F2P", false));
            gameList.add(new GameObject("Emily is Away", 417860, "F2P", false));
            gameList.add(new GameObject("Free to Play", 245550, "F2P", false));
            gameList.add(new GameObject("Iron Snout", 424280, "F2P", false));
            gameList.add(new GameObject("Mandagon", 461560, "F2P", false));

            recyclerView.setAdapter(new F2PRecyclerViewAdapter(gameList, mListener));

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
        public void F2PFragmentInteraction(GameObject game);
    }
}
