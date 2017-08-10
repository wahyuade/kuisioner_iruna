package com.wahyuade.kuisioner.penjual.waktu_penjualan;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wahyuade.kuisioner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFeeFragment extends Fragment {


    public DetailFeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detail_fee = inflater.inflate(R.layout.fragment_detail_fee, container, false);

        return detail_fee;
    }

}
