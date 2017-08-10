package com.wahyuade.kuisioner.penjual.kebutuhan;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.DefaultModel;
import com.wahyuade.kuisioner.model.KebutuhanModel;
import com.wahyuade.kuisioner.penjual.waktu_penjualan.WaktuPenjualanFragment;
import com.wahyuade.kuisioner.service.ApiService;
import com.wahyuade.kuisioner.service.DatabaseService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KebutuhanPenjualFragment extends Fragment {
    ArrayList<KebutuhanModel> data_kebutuhan;
    FloatingActionButton add_kebutuhan;
    ListView list_kebutuhan;
    AlertDialog new_kebutuhan;
    TextView lanjut;
    ProgressDialog progressDialog;
    public KebutuhanPenjualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View kebutuhan = inflater.inflate(R.layout.fragment_kebutuhan_penjual, container, false);
        list_kebutuhan = (ListView)kebutuhan.findViewById(R.id.list_kebutuhan);
        add_kebutuhan = (FloatingActionButton)kebutuhan.findViewById(R.id.add_kebutuhan);
        lanjut = (TextView)kebutuhan.findViewById(R.id.next);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        data_kebutuhan = new ArrayList<>();
        data_kebutuhan.add(new KebutuhanModel("Sembako"));
        data_kebutuhan.add(new KebutuhanModel("Makanan / Minuman"));
        data_kebutuhan.add(new KebutuhanModel("Alat Rumah Tangga"));
        data_kebutuhan.add(new KebutuhanModel("Elektronik & Aksesoris"));
        data_kebutuhan.add(new KebutuhanModel("Otomotif"));
        data_kebutuhan.add(new KebutuhanModel("Bahan Kimia (Pembersih dll)"));

        final KebutuhanPenjualAdapter adapter = new KebutuhanPenjualAdapter(data_kebutuhan, getActivity());
        list_kebutuhan.setAdapter(adapter);

        add_kebutuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                new_kebutuhan = new AlertDialog.Builder(getActivity()).create();
                new_kebutuhan.setView(inflater.inflate(R.layout.add_kebutuhan, null));
                new_kebutuhan.show();
                final EditText kebutuhan = (EditText)new_kebutuhan.findViewById(R.id.kebutuhan);
                Button batal = (Button)new_kebutuhan.findViewById(R.id.batal);
                Button tambah = (Button)new_kebutuhan.findViewById(R.id.tambah_kebutuhan);
                tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!kebutuhan.getText().toString().isEmpty()){
                            progressDialog.show();
                            DatabaseService db = new DatabaseService(getActivity());
                            String data_email = db.getEmail();
                            db.close();
                            ApiService.service_post.postKebutuhanTerbukaPenjual(data_email, kebutuhan.getText().toString()).enqueue(new Callback<DefaultModel>() {
                                @Override
                                public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                    data_kebutuhan.add(new KebutuhanModel(kebutuhan.getText().toString(), true));
                                    adapter.notifyDataSetChanged();
                                    new_kebutuhan.dismiss();
                                    progressDialog.dismiss();
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
                batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new_kebutuhan.dismiss();
                    }
                });
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                int count = 0;
                for(int i=0;i<data_kebutuhan.size();i++){
                    if(data_kebutuhan.get(i).isChecked()){
                        count++;
                    }
                }
                if(count>0){
                    DatabaseService db = new DatabaseService(getActivity());
                    String data_email = db.getEmail();
                    db.close();
                    ApiService.service_post.postKebutuhanTertutupPenjual(data_email,Boolean.toString(data_kebutuhan.get(0).isChecked()), Boolean.toString(data_kebutuhan.get(1).isChecked()), Boolean.toString(data_kebutuhan.get(2).isChecked()), Boolean.toString(data_kebutuhan.get(3).isChecked()), Boolean.toString(data_kebutuhan.get(4).isChecked()), Boolean.toString(data_kebutuhan.get(5).isChecked())).enqueue(new Callback<DefaultModel>() {
                        @Override
                        public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                            progressDialog.dismiss();
                            getFragmentManager().beginTransaction().replace(R.id.penjual_fragment, new WaktuPenjualanFragment(), "WAKTU_PENJUALAN").commit();
                        }

                        @Override
                        public void onFailure(Call<DefaultModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "Anda harus memilih kategori minimal 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return kebutuhan;
    }
}
