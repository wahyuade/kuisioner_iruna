package com.wahyuade.kuisioner.penjual.pembayaran;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.MetodePembayaranModel;

import java.util.ArrayList;

/**
 * Created by wahyuade on 10/08/17.
 */

public class MetodePembayaranPenjualAdapter extends RecyclerView.Adapter<MetodePembayaranPenjualAdapter.ListMetode> {
    Activity activity;
    ArrayList<MetodePembayaranModel> data_metode;

    public MetodePembayaranPenjualAdapter(Activity activity, ArrayList<MetodePembayaranModel> data_metode) {
        this.activity = activity;
        this.data_metode = data_metode;
    }

    @Override
    public ListMetode onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_metode, parent, false);
        return new ListMetode(itemView);
    }

    @Override
    public void onBindViewHolder(final ListMetode holder, final int position) {
        holder.metode.setText(data_metode.get(position).getNama_metode());
        holder.metode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i<data_metode.size();i++){
                    if(Integer.toString(i).equals(Integer.toString(position))){
                        Log.d("position", Integer.toString(i));
                        data_metode.get(position).setStatus(true);
                    }else{
                        data_metode.get(i).setStatus(false);
                        notifyItemChanged(i);
                    }
                }
            }
        });
        holder.metode.setChecked(data_metode.get(position).isStatus());

    }

    @Override
    public int getItemCount() {
        return data_metode.size();
    }

    public class ListMetode extends RecyclerView.ViewHolder {
        RadioButton metode;
        public ListMetode(View itemView) {
            super(itemView);
            metode = (RadioButton)itemView.findViewById(R.id.metode);
        }
    }
}
