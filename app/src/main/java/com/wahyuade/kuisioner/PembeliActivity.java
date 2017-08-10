package com.wahyuade.kuisioner;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.wahyuade.kuisioner.pembeli.IntroFragment;
import com.wahyuade.kuisioner.pembeli.kebutuhan.KebutuhanFragment;
import com.wahyuade.kuisioner.pembeli.pembayaran.MetodePembayaranFragment;
import com.wahyuade.kuisioner.pembeli.referensi.ReferensiFragment;
import com.wahyuade.kuisioner.pembeli.waktu_pembelian.WaktuPembelianFragment;

public class PembeliActivity extends Activity {
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli);
        ft = getFragmentManager().beginTransaction();

        IntroFragment intro = new IntroFragment();
        ft.replace(R.id.pembeli_fragment, intro, "INTRO");
        ft.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
