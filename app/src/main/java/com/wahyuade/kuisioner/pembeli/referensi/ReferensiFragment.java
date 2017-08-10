package com.wahyuade.kuisioner.pembeli.referensi;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wahyuade.kuisioner.R;
import com.wahyuade.kuisioner.model.DefaultModel;
import com.wahyuade.kuisioner.model.ReferensiModel;
import com.wahyuade.kuisioner.pembeli.waktu_pembelian.WaktuPembelianFragment;
import com.wahyuade.kuisioner.service.ApiService;
import com.wahyuade.kuisioner.service.DatabaseService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReferensiFragment extends Fragment {
    FloatingActionButton add_referensi;
    AlertDialog new_referensi;
    RecyclerView list_referensi;
    ArrayList<ReferensiModel> data_referensi;
    TextView lanjut;
    ProgressDialog progressDialog;
    public ReferensiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View referensi = inflater.inflate(R.layout.fragment_referensi, container, false);
        add_referensi = (FloatingActionButton)referensi.findViewById(R.id.add_referensi);
        list_referensi = (RecyclerView)referensi.findViewById(R.id.list_referensi);
        lanjut = (TextView)referensi.findViewById(R.id.next);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity().getApplicationContext());
        list_referensi.setLayoutManager(layout);

        data_referensi = new ArrayList<>();

        //data_referensi.add(new ReferensiModel("Wahyu Ade Sasongko", "Jln Kalisari Damen no 72 A Surabaya", "Internet Provider", "085736600250", "PT Bima Sakti Indah"));

        final ReferensiAdapter adapter = new ReferensiAdapter(data_referensi, getActivity());
        list_referensi.setAdapter(adapter);

        add_referensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                new_referensi = new AlertDialog.Builder(getActivity()).create();
                new_referensi.setView(inflater.inflate(R.layout.add_referensi, null));
                new_referensi.show();
                final EditText nama_ukm = (EditText)new_referensi.findViewById(R.id.nama_ukm);
                final EditText nama_pemilik = (EditText)new_referensi.findViewById(R.id.nama_pemilik);
                final EditText no_telp = (EditText)new_referensi.findViewById(R.id.no_telp);
                final EditText alamat = (EditText)new_referensi.findViewById(R.id.alamat);
                final EditText produk = (EditText)new_referensi.findViewById(R.id.produk);
                Button tambah_referensi = (Button)new_referensi.findViewById(R.id.tambah_referensi);
                Button batal = (Button)new_referensi.findViewById(R.id.batal);
                batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new_referensi.dismiss();
                    }
                });

                tambah_referensi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(nama_pemilik.getText().toString().isEmpty() || no_telp.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() || produk.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Silahkan lengkapi data formulir toko / UKM referensi Anda", Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.show();
                            DatabaseService db = new DatabaseService(getActivity());
                            String data_email = db.getEmail();
                            db.close();

                            if(nama_ukm.getText().toString().isEmpty()){
                                ApiService.service_post.postReferensiUKM(data_email,"TANPA NAMA", nama_pemilik.getText().toString(), no_telp.getText().toString(),  alamat.getText().toString(), produk.getText().toString()).enqueue(new Callback<DefaultModel>() {
                                    @Override
                                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                        data_referensi.add(new ReferensiModel(nama_pemilik.getText().toString(), alamat.getText().toString(), produk.getText().toString(), no_telp.getText().toString()));
                                        progressDialog.dismiss();
                                        adapter.notifyDataSetChanged();
                                        new_referensi.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<DefaultModel> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });
                            }else{
                                ApiService.service_post.postReferensiUKM(data_email,nama_ukm.getText().toString(), nama_pemilik.getText().toString(), no_telp.getText().toString(),  alamat.getText().toString(), produk.getText().toString()).enqueue(new Callback<DefaultModel>() {
                                    @Override
                                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                        data_referensi.add(new ReferensiModel(nama_pemilik.getText().toString(), alamat.getText().toString(), produk.getText().toString(), no_telp.getText().toString(), nama_ukm.getText().toString()));
                                        progressDialog.dismiss();
                                        adapter.notifyDataSetChanged();
                                        new_referensi.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<DefaultModel> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data_referensi.size() > 0){
                    getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new WaktuPembelianFragment(), "WAKTU").commit();
                }else{
                    AlertDialog.Builder logout = new AlertDialog.Builder(getActivity());
                    logout.setTitle("Maaf");
                    logout.setMessage("Apakah Anda ingin melanjutkan tanpa Referensi Toko / UKM ?");
                    logout.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new WaktuPembelianFragment(), "WAKTU").commit();
                            dialogInterface.dismiss();
                        }
                    });
                    logout.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    logout.show();
                }
            }
        });
        return referensi;
    }

}
