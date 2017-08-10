package com.wahyuade.kuisioner.penjual.kebutuhan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.KebutuhanModel;

import java.util.ArrayList;

/**
 * Created by wahyuade on 10/08/17.
 */

public class KebutuhanPenjualAdapter extends ArrayAdapter {

    private ArrayList<KebutuhanModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
        RelativeLayout list_kebutuhan;
    }

    public KebutuhanPenjualAdapter(ArrayList<KebutuhanModel> data, Context context) {
        super(context, R.layout.list_kebutuhan, data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public KebutuhanModel getItem(int position) {
        return dataSet.get(position);
    }


    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kebutuhan, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.kebutuhan);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.status);
            viewHolder.list_kebutuhan = (RelativeLayout)convertView.findViewById(R.id.list_kebutuhan);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        final KebutuhanModel item = getItem(position);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position < 6){
                    if(item.isChecked()){
                        viewHolder.checkBox.setChecked(false);
                        item.setChecked(false);
                    }else{
                        viewHolder.checkBox.setChecked(true);
                        item.setChecked(true);
                    }
                }else{
                    viewHolder.checkBox.setChecked(true);
                    item.setChecked(true);
                }
            }
        };
        viewHolder.txtName.setText(item.getName());
        viewHolder.checkBox.setChecked(item.isChecked());

        viewHolder.list_kebutuhan.setOnClickListener(listener);
        viewHolder.checkBox.setOnClickListener(listener);
        viewHolder.txtName.setOnClickListener(listener);

        return result;
    }
}