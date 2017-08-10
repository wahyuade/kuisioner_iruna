package com.wahyuade.kuisioner.pembeli.waktu_pembelian;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
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
public class BukaFragment extends Fragment {
    TextView lanjut;
    TimePicker buka_time;
    ProgressDialog progressDialog;

    public BukaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View buka =  inflater.inflate(R.layout.fragment_buka, container, false);
        buka_time = (TimePicker)buka.findViewById(R.id.waktu_buka);
        lanjut = (TextView)buka.findViewById(R.id.next);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mohon tunggu");

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder perhatian = new AlertDialog.Builder(getActivity());
                perhatian.setTitle("Maaf");
                perhatian.setMessage("Apakah kebiasaan waktu mulai pembelian / pembayaran Anda sudah benar?");
                perhatian.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        progressDialog.show();
                        DatabaseService db = new DatabaseService(getActivity());
                        String data_email = db.getEmail();
                        db.close();
                        ApiService.service_post.postWaktuBukaPembeli(data_email, Integer.toString(buka_time.getCurrentHour()), Integer.toString(buka_time.getCurrentMinute())).enqueue(new Callback<DefaultModel>() {
                            @Override
                            public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                dialogInterface.dismiss();
                                progressDialog.dismiss();
                                getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new TutupFragment(), "TUTUP").commit();
                            }

                            @Override
                            public void onFailure(Call<DefaultModel> call, Throwable t) {
                                Toast.makeText(getActivity(), "Mohon maaf terjadi gangguan jaringan", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                });
                perhatian.setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                perhatian.show();
            }
        });
        return buka;
    }

}
