package com.wahyuade.kuisioner.pembeli.waktu_pembelian;

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
import com.wahyuade.kuisioner.model.HariModel;
import com.wahyuade.kuisioner.pembeli.kebutuhan.KebutuanAdapter;

import java.util.ArrayList;

/**
 * Created by wahyuade on 08/08/17.
 */

public class WaktuPembelianAdapter extends ArrayAdapter {

    private ArrayList<HariModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
        RelativeLayout list_kebutuhan;
    }

    public WaktuPembelianAdapter(ArrayList<HariModel> data, Context context) {
        super(context, R.layout.list_hari, data);
        this.dataSet = data;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public HariModel getItem(int position) {
        return dataSet.get(position);
    }


    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final WaktuPembelianAdapter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new WaktuPembelianAdapter.ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hari, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.hari);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.status);
            viewHolder.list_kebutuhan = (RelativeLayout)convertView.findViewById(R.id.list_hari);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (WaktuPembelianAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        final HariModel item = getItem(position);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isChecked()){
                    viewHolder.checkBox.setChecked(false);
                    item.setChecked(false);
                }else{
                    viewHolder.checkBox.setChecked(true);
                    item.setChecked(true);
                }
            }
        };

        viewHolder.txtName.setText(item.getHari());
        viewHolder.checkBox.setChecked(item.isChecked());

        viewHolder.list_kebutuhan.setOnClickListener(listener);
        viewHolder.checkBox.setOnClickListener(listener);
        viewHolder.txtName.setOnClickListener(listener);

        return result;
    }
}
