package com.wahyuade.kuisioner.pembeli.pembayaran;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.wahyuade.kuisioner.model.MetodePembayaranModel;
import com.wahyuade.kuisioner.pembeli.FeeAgreementFragment;
import com.wahyuade.kuisioner.service.ApiService;
import com.wahyuade.kuisioner.service.DatabaseService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MetodePembayaranFragment extends Fragment {
    RecyclerView list_metode_pembayaran;
    ArrayList<MetodePembayaranModel> data_metode;
    FloatingActionButton add_metode;
    AlertDialog tambah_metode;
    TextView lanjut;
    ProgressDialog progressDialog;
    int indeks = 0;

    public MetodePembayaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View metode_pembayaran = inflater.inflate(R.layout.fragment_metode_pembayaran, container, false);
        list_metode_pembayaran = (RecyclerView)metode_pembayaran.findViewById(R.id.list_metode_pembayaran);
        add_metode = (FloatingActionButton)metode_pembayaran.findViewById(R.id.add_metode);
        lanjut = (TextView)metode_pembayaran.findViewById(R.id.next);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        data_metode = new ArrayList<>();
        data_metode.add(new MetodePembayaranModel("Tunai"));
        data_metode.add(new MetodePembayaranModel("Kartu Kredit"));
        data_metode.add(new MetodePembayaranModel("Kartu Debit"));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity().getApplicationContext());
        list_metode_pembayaran.setLayoutManager(layout);

        final MetodePembayaranAdapter adapter = new MetodePembayaranAdapter(getActivity(), data_metode);
        list_metode_pembayaran.setAdapter(adapter);

        add_metode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                tambah_metode = new AlertDialog.Builder(getActivity()).create();
                tambah_metode.setView(inflater.inflate(R.layout.add_metode, null));
                tambah_metode.show();
                Button tambah = (Button)tambah_metode.findViewById(R.id.tambah_metode);
                Button batal = (Button)tambah_metode.findViewById(R.id.batal);
                final EditText metode = (EditText)tambah_metode.findViewById(R.id.metode);

                tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!metode.getText().toString().isEmpty()){
                            data_metode.add(new MetodePembayaranModel(metode.getText().toString()));
                            adapter.notifyDataSetChanged();
                            tambah_metode.dismiss();
                        }
                    }
                });

                batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tambah_metode.dismiss();
                    }
                });
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                for(int i = 0; i< data_metode.size();i++){
                    if(data_metode.get(i).isStatus()){
                        indeks = i;
                        count++;
                    }
                }
                if(count==1){
                    AlertDialog.Builder perhatian = new AlertDialog.Builder(getActivity());
                    perhatian.setTitle("Maaf");
                    perhatian.setMessage("Apakah metode pembayaran yang Anda pilih sudah benar?");
                    perhatian.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialogInterface, int i) {
                            progressDialog.show();
                            DatabaseService db = new DatabaseService(getActivity());
                            String data_email = db.getEmail();
                            db.close();
                            if(indeks < 3){
                                ApiService.service_post.postPembayaranTertutupPembeli(data_email, Integer.toString(indeks)).enqueue(new Callback<DefaultModel>() {
                                    @Override
                                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                        progressDialog.dismiss();
                                        dialogInterface.dismiss();
                                        getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new FeeAgreementFragment(), "FEE_AGREEMENT").commit();
                                    }

                                    @Override
                                    public void onFailure(Call<DefaultModel> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });
                            }else{
                                ApiService.service_post.postPembayaranTerbukaPembeli(data_email, data_metode.get(indeks).getNama_metode()).enqueue(new Callback<DefaultModel>() {
                                    @Override
                                    public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                        progressDialog.dismiss();
                                        dialogInterface.dismiss();
                                        getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new FeeAgreementFragment(), "FEE_AGREEMENT").commit();
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
                    perhatian.setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    perhatian.show();
                }else{
                    Toast.makeText(getActivity(), "Setidaknya Anda harus memiliki 1 metode pembayaran", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return metode_pembayaran;
    }

}
