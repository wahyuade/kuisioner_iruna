package com.wahyuade.kuisioner.penjual;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.DefaultModel;
import com.wahyuade.kuisioner.penjual.kebutuhan.KebutuhanPenjualFragment;
import com.wahyuade.kuisioner.service.ApiService;
import com.wahyuade.kuisioner.service.DatabaseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BiodataPenjualFragment extends Fragment {
    EditText nama, alamat, email, no_telp;
    TextView next;
    ProgressDialog progressDialog;
    public BiodataPenjualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View penjual = inflater.inflate(R.layout.fragment_biodata_penjual, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        nama = (EditText)penjual.findViewById(R.id.nama);
        alamat = (EditText)penjual.findViewById(R.id.alamat);
        email = (EditText)penjual.findViewById(R.id.email);
        no_telp = (EditText)penjual.findViewById(R.id.no_telp);

        next = (TextView)penjual.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(nama.getText()).equals("") || String.valueOf(alamat.getText()).equals("") || String.valueOf(email.getText()).equals("") || String.valueOf(no_telp.getText()).equals("")){
                    Toast.makeText(getActivity(), "Mohon lengkapi terlebih dahulu isian formulir kuisioner Anda", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                    ApiService.service_post.postBiodataPenjual(String.valueOf(nama.getText()),String.valueOf(alamat.getText()),String.valueOf(no_telp.getText()),String.valueOf(email.getText())).enqueue(new Callback<DefaultModel>() {
                        @Override
                        public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                            progressDialog.dismiss();
                            DatabaseService db = new DatabaseService(getActivity());
                            db.setUsersData(String.valueOf(email.getText()));
                            db.close();
                            getFragmentManager().beginTransaction().replace(R.id.penjual_fragment, new KebutuhanPenjualFragment(), "KEBUTUHAN").commit();
                        }

                        @Override
                        public void onFailure(Call<DefaultModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });


        return penjual;
    }

}
