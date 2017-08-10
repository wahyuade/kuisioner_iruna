package com.wahyuade.kuisioner.pembeli;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wahyuade.kuisioner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerimaKasihFragment extends Fragment {


    public TerimaKasihFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        thread.start();
        return inflater.inflate(R.layout.fragment_terima_kasih, container, false);
    }

}
