package com.wahyuade.kuisioner.pembeli;


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
public class IntroFragment extends Fragment {
    TextView next;

    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View intro = inflater.inflate(R.layout.fragment_intro, container, false);
        next = (TextView)intro.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.pembeli_fragment, new BiodataFragment(), "BIODATA").commit();
            }
        });

        return intro;
    }

}
