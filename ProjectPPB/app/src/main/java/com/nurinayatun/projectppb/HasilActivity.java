package com.nurinayatun.projectppb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.nurinayatun.projectppb.LoginActivity.TAG_ID;
import static com.nurinayatun.projectppb.LoginActivity.TAG_USERNAME;

public class HasilActivity extends AppCompatActivity {
    TextView pinjam, data, nama, tanggal, waktu, ruang;
    Button btnBack, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        String nm = getIntent().getStringExtra("nama");
        String rk = getIntent().getStringExtra("ruang");
        String tgl = getIntent().getStringExtra("tanggal");
        String wkt = getIntent().getStringExtra("waktu");

        data = (TextView) findViewById(R.id.tvSimpan);
        nama = (TextView) findViewById(R.id.tvNama);
        ruang = (TextView) findViewById(R.id.tvRuang);
        tanggal = (TextView) findViewById(R.id.tvTanggal);
        waktu = (TextView) findViewById(R.id.tvWaktu);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnBack = (Button) findViewById(R.id.btnBack);

        data.setText("Data Peminjaman");
        nama.setText("Nama : " +nm);
        ruang.setText("Ruangan : " +rk);
        tanggal.setText("Tanggal : " +tgl);
        waktu.setText("Waktu : " +wkt);

        final SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_ID,null);
                editor.putString(TAG_USERNAME,null);
                editor.commit();

                Intent intent = new Intent(HasilActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
