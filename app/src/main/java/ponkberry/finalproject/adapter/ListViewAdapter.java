package ponkberry.finalproject.adapter;

import android.widget.BaseAdapter;

/**
 * Created by Ponk on 4/6/2017.
 */

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ponkberry.finalproject.R;
import ponkberry.finalproject.gameobject.GameObject;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mInflater;
    private final ArrayList<GameObject> listResult;

    public ListViewAdapter(Context context, ArrayList<GameObject> listResult) {
        mContext = context;
        this.listResult = listResult;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listResult.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.text_app_id);
            holder.textView2 = (TextView) convertView.findViewById(R.id.text_app_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.object = listResult.get(position);

        holder.textView1.setText(""+holder.object.getAppid());
        holder.textView2.setText(holder.object.getName());

        return convertView;
    }
}

class ViewHolder {
    TextView textView1;
    TextView textView2;
    GameObject object;
}

