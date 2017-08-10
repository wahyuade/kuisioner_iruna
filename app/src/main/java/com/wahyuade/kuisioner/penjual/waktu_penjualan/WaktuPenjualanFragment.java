package com.wahyuade.kuisioner.penjual.waktu_penjualan;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.DefaultModel;
import com.wahyuade.kuisioner.model.HariModel;
import com.wahyuade.kuisioner.service.ApiService;
import com.wahyuade.kuisioner.service.DatabaseService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaktuPenjualanFragment extends Fragment {
    ArrayList<HariModel> data_hari;
    ListView list_hari;
    TextView lanjut;
    ProgressDialog progressDialog;
    public WaktuPenjualanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View waktu =  inflater.inflate(R.layout.fragment_waktu_penjualan, container, false);
        list_hari = (ListView)waktu.findViewById(R.id.list_hari);
        lanjut = (TextView)waktu.findViewById(R.id.next);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        data_hari = new ArrayList<>();
        data_hari.add(new HariModel("Senin"));
        data_hari.add(new HariModel("Selasa"));
        data_hari.add(new HariModel("Rabu"));
        data_hari.add(new HariModel("Kamis"));
        data_hari.add(new HariModel("Jumat"));
        data_hari.add(new HariModel("Sabtu"));
        data_hari.add(new HariModel("Minggu"));

        WaktuPenjualanAdapter adapter = new WaktuPenjualanAdapter(data_hari, getActivity());

        list_hari.setAdapter(adapter);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                int count=0;
                for(int i = 0; i<data_hari.size();i++){
                    if(data_hari.get(i).isChecked()){
                        count++;
                    }
                }
                if(count>0){
                    DatabaseService db = new DatabaseService(getActivity());
                    String data_email = db.getEmail();
                    db.close();
                    ApiService.service_post.postHariTransaksiPenjual(data_email, Boolean.toString(data_hari.get(0).isChecked()), Boolean.toString(data_hari.get(1).isChecked()), Boolean.toString(data_hari.get(2).isChecked()), Boolean.toString(data_hari.get(3).isChecked()), Boolean.toString(data_hari.get(4).isChecked()), Boolean.toString(data_hari.get(5).isChecked()), Boolean.toString(data_hari.get(6).isChecked())).enqueue(new Callback<DefaultModel>() {
                        @Override
                        public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                            progressDialog.dismiss();
                            getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new BukaPenjualFragment(), "BUKA_PENJUAL").commit();
                        }

                        @Override
                        public void onFailure(Call<DefaultModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "Silahkan masukkan minimal 1 hari untuk pembelian atau pembayaran Anda", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return waktu;
    }

}
