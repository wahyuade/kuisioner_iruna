package com.wahyuade.kuisioner.penjual;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wahyuade.kuisioner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroPenjualFragment extends Fragment {

    TextView lanjut;
    public IntroPenjualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View intro = inflater.inflate(R.layout.fragment_intro_penjual, container, false);
        lanjut = (TextView)intro.findViewById(R.id.next);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.penjual_fragment, new BiodataPenjualFragment(), "BIODATA").commit();
            }
        });

        return intro;
    }

}
