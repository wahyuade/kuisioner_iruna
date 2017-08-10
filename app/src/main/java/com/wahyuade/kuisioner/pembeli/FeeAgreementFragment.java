package com.wahyuade.kuisioner.pembeli;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wahyuade.kuisioner.MainActivity;
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
public class FeeAgreementFragment extends Fragment {
    Button bersedia, tidak_bersedia;
    ProgressDialog progressDialog;
    public FeeAgreementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fee_agreement =  inflater.inflate(R.layout.fragment_fee_agreement, container, false);
        bersedia = (Button)fee_agreement.findViewById(R.id.bersedia);
        tidak_bersedia = (Button)fee_agreement.findViewById(R.id.tidak_bersedia);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");
        DatabaseService db=new DatabaseService(getActivity());
        final String data_email = db.getEmail();
        db.close();
        bersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                ApiService.service_post.postFeeAgreementPembeli(data_email,"1").enqueue(new Callback<DefaultModel>() {
                    @Override
                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                        progressDialog.dismiss();
                        DatabaseService db = new DatabaseService(getActivity());
                        db.unSetUser();
                        db.close();
                        getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new TerimaKasihFragment(), "TERIMA KASIH").commit();
                    }

                    @Override
                    public void onFailure(Call<DefaultModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });

        tidak_bersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                ApiService.service_post.postFeeAgreementPembeli(data_email,"0").enqueue(new Callback<DefaultModel>() {
                    @Override
                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                        progressDialog.dismiss();
                        DatabaseService db = new DatabaseService(getActivity());
                        db.unSetUser();
                        db.close();
                        getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new TerimaKasihFragment(), "TERIMA_KASIH").commit();
                    }

                    @Override
                    public void onFailure(Call<DefaultModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });
        return fee_agreement;
    }

}
