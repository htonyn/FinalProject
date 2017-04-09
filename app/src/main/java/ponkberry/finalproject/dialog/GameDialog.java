package ponkberry.finalproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.finalproject.R;

/**
 * Created by Ponk on 4/8/2017.
 */

public class GameDialog extends Dialog {

    private IGameDialogEventListener listener;
    private int checkedID;
    @BindView(R.id.radio_completion)
    RadioGroup radioGroup;

    @OnClick(R.id.rb_comp_set)
    public void onSelectComp() {
        switch(checkedID) {
            case R.id.rb_untouch:
                listener.setUntouched();
                break;
            case R.id.rb_prog:
                listener.setProgress();
                break;
            case R.id.rb_comp:
                listener.setComplete();
                break;
            default:
        }
        dismiss();
    }

    public GameDialog(@NonNull Context context, IGameDialogEventListener listener) {
        super(context, R.style.dialog);
        this.listener = listener;
    }

    public interface IGameDialogEventListener {
        public void setUntouched();
        public void setProgress();
        public void setComplete();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_dialog);
        ButterKnife.bind(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //toastShort("You checked the radio button"+checkedId);
                checkedID = checkedId;
            }
        });
    }


}
