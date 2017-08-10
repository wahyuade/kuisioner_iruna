package com.wahyuade.kuisioner.penjual;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terima_kasih_penjual, container, false);
    }

}
