package com.wahyuade.kuisioner;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.wahyuade.kuisioner.penjual.DetailFeeFragment;
import com.wahyuade.kuisioner.penjual.IntroPenjualFragment;


public class PenjualActivity extends Activity {
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjual);
        ft = getFragmentManager().beginTransaction();

        IntroPenjualFragment intro = new IntroPenjualFragment();
        ft.replace(R.id.penjual_fragment, intro, "INTRO");
        ft.commit();
    }
    @Override
    public void onBackPressed() {

    }
}
