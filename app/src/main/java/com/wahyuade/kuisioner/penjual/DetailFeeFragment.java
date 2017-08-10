package com.wahyuade.kuisioner.penjual;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.DefaultModel;
import com.wahyuade.kuisioner.service.ApiService;
import com.wahyuade.kuisioner.service.DatabaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFeeFragment extends Fragment {
    RadioButton pilihan_1, pilihan_2;
    TextView selesai;
    ProgressDialog progressDialog;
    
    public DetailFeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detail_fee = inflater.inflate(R.layout.fragment_detail_fee, container, false);
        pilihan_1 = (RadioButton)detail_fee.findViewById(R.id.pilihan_1);
        pilihan_2 = (RadioButton)detail_fee.findViewById(R.id.pilihan_2);
        selesai = (TextView)detail_fee.findViewById(R.id.selesai);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        pilihan_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihan_2.setChecked(false);
            }
        });

        pilihan_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihan_1.setChecked(false);
            }
        });
        
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String id_pilihan;
                if(pilihan_1.isChecked()){
                    id_pilihan = "1";
                }else{
                    id_pilihan = "2";
                }
                DatabaseService db = new DatabaseService(getActivity());
                ApiService.service_post.postPilihanHarga(db.getEmail(), id_pilihan).enqueue(new Callback<DefaultModel>() {
                    @Override
                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                        progressDialog.dismiss();
                        getFragmentManager().beginTransaction().replace(R.id.penjual_fragment, new TerimaKasihPenjual(), "SELESAI").commit();
                    }

                    @Override
                    public void onFailure(Call<DefaultModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
                db.close();
            }
        });
        
        
        return detail_fee;
    }

}
