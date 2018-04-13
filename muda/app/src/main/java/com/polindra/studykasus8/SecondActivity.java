package com.polindra.studykasus8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tanggal,waktu, ruangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String ruangan = getIntent().getStringExtra("ruangan");
        String tgl = getIntent().getStringExtra("tanggal");
        String wkt = getIntent().getStringExtra("waktu");

        this.ruangan = (TextView) findViewById(R.id.tvRuangan);
        tanggal = (TextView) findViewById(R.id.tvTanggal);
        waktu = (TextView) findViewById(R.id.tvWaktu);

        this.ruangan.setText("Ruangan : "+ruangan);
        tanggal.setText("Tanggal : "+tgl);
        waktu.setText("Waktu : "+wkt);
    }
}
