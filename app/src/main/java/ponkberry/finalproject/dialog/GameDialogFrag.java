package ponkberry.finalproject.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.finalproject.R;

/**
 * Created by htony on 4/8/2017.
 */

public class GameDialogFrag extends DialogFragment {
    private View v;
    private IGameDialogEventListener listener;
    int mNum;
    private int checkedID;
    @BindView(R.id.radio_completion)
    RadioGroup radioGroup;
    @BindView(R.id.game_name)
    TextView gameTitle;
    @BindView(R.id.game_icon)
    ImageView gameIcon;
    @BindView(R.id.gd_ach)
    TextView gameAchievements;
    @BindView(R.id.gd_comp_time)
    TextView gameCompTime;
    @BindView(R.id.gd_genre)
    TextView gameGenre;
    @BindView(R.id.gd_status)
    TextView gameStatus;

    @OnClick(R.id.rb_comp_set)
    public void onSelectComp() {
        switch(checkedID) {
            case R.id.rb_untouch:
                listener = getArguments().getParcelable("listener");
                listener.setUntouched();
                break;
            case R.id.rb_prog:
                listener = getArguments().getParcelable("listener");
                listener.setProgress();
                break;
            case R.id.rb_comp:
                listener = getArguments().getParcelable("listener");
                listener.setComplete();
                break;
            default:
        }
        dismiss();
    }

    public static GameDialogFrag newInstance(String name, String genre, int status, int achievements, String time, GameDialogFrag.IGameDialogEventListener listener) {
        GameDialogFrag gdf = new GameDialogFrag();
        Bundle data = new Bundle();
        data.putInt("achievements", achievements);
        data.putInt("status", status);
        data.putString("name", name);
        data.putString("genre", genre);
        data.putString("time", time);
        data.putParcelable("listener", listener);
        gdf.setArguments(data);
        return gdf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listener = listener;
        mNum = getArguments().getInt("num");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.game_dialog, container, false);
        ButterKnife.bind(this, v);
        updateDialog();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //toastShort("You checked the radio button"+checkedId);
                checkedID = checkedId;
            }
        });
        return v;
    }

    public void updateDialog() {
        gameTitle.setText(getArguments().getString("name", ""));
        gameGenre.setText(getArguments().getString("genre", ""));
        gameAchievements.setText(""+getArguments().getInt("achievements", 0));
        gameCompTime.setText(getArguments().getString("time", ""));
        switch(getArguments().getInt("status", 0)) {
            case 0:
                gameStatus.setText("Untouched");
                break;
            case 1:
                gameStatus.setText("In Progress");
                break;
            case 2:
                gameStatus.setText("Completed");
                break;
            default:
                break;
        }
        gameIcon.setImageResource(R.mipmap.tacocat_trans);
    }

    public interface IGameDialogEventListener extends Parcelable {
        public void setUntouched();
        public void setProgress();
        public void setComplete();
        public void writeToParcel(Parcel dest, int flags);
        public int describeContents();
    }
}
