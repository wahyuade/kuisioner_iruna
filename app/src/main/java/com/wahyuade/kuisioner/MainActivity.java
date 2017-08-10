package com.wahyuade.kuisioner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.wahyuade.kuisioner.service.DatabaseService;

public class MainActivity extends Activity {
    LinearLayout pembeli, penjual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseService db = new DatabaseService(MainActivity.this);
        db.unSetUser();
        db.close();

        pembeli = (LinearLayout)findViewById(R.id.pembeli);
        penjual = (LinearLayout)findViewById(R.id.penjual);

        pembeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pem = new Intent(MainActivity.this, PembeliActivity.class);
                startActivity(pem);
            }
        });

        penjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pen = new Intent(MainActivity.this, PenjualActivity.class);
                startActivity(pen);
            }
        });
    }
}
