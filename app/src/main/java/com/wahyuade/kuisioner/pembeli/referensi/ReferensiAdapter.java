package com.wahyuade.kuisioner.pembeli.referensi;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.ReferensiModel;

import java.util.ArrayList;

/**
 * Created by wahyuade on 08/08/17.
 */

public class ReferensiAdapter extends RecyclerView.Adapter<ReferensiAdapter.ListReferensi>{
    ArrayList<ReferensiModel> data_referensi;
    Activity activity;

    public ReferensiAdapter(ArrayList<ReferensiModel> data_referensi, Activity activity) {
        this.data_referensi = data_referensi;
        this.activity = activity;
    }

    @Override
    public ListReferensi onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_referensi, parent, false);
        return new ListReferensi(itemView);
    }

    @Override
    public void onBindViewHolder(ListReferensi holder, int position) {
        holder.nama_ukm.setText(data_referensi.get(position).getNama_ukm());
        holder.produk.setText(data_referensi.get(position).getProduk());
        holder.no_telp.setText(data_referensi.get(position).getNo_telp());
        holder.alamat.setText(data_referensi.get(position).getAlamat());
        holder.pemilik.setText(data_referensi.get(position).getNama_pemilik());
    }

    @Override
    public int getItemCount() {
        return data_referensi.size();
    }

    public class ListReferensi extends RecyclerView.ViewHolder {
        TextView nama_ukm, pemilik, no_telp, alamat, produk;
        public ListReferensi(View itemView) {
            super(itemView);
            nama_ukm = itemView.findViewById(R.id.nama_ukm);
            alamat = itemView.findViewById(R.id.alamat);
            pemilik = itemView.findViewById(R.id.pemilik);
            no_telp = itemView.findViewById(R.id.no_telp);
            produk = itemView.findViewById(R.id.produk);
        }
    }

}
