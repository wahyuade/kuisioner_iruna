package com.wahyuade.kuisioner.penjual;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wahyuade.kuisioner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerimaKasihPenjual extends Fragment {


    public TerimaKasihPenjual() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View terima_kasih = inflater.inflate(R.layout.fragment_terima_kasih_penjual, container, false);
        Thread thread = new Thread(){
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    getActivity().finish();
                }
            }
        };
        return terima_kasih;
    }

}
