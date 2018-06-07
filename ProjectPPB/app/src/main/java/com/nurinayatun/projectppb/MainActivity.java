package com.nurinayatun.projectppb;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.nurinayatun.projectppb.LoginActivity.TAG_ID;
import static com.nurinayatun.projectppb.LoginActivity.TAG_USERNAME;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    EditText etNama, etTanggal, etWaktu;
    Button btnTanggal, btnWaktu, btnSimpan, btnLogout;
    SimpleDateFormat dateFormat;
    String[] ruangan;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        etNama = (EditText) findViewById(R.id.etNama);
        etTanggal = (EditText) findViewById(R.id.etTanggal);
        etWaktu = (EditText) findViewById(R.id.etWaktu);
        btnTanggal = (Button) findViewById(R.id.btnTanggal);
        btnWaktu = (Button) findViewById(R.id.btnWaktu);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        Spinner s1 = (Spinner) findViewById(R.id.spinner);

        final SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_ID,null);
                editor.putString(TAG_USERNAME,null);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });


        ruangan = new String[]{"Aula Direktorat", "RK Teori 1", "RK Teori 2", "RK Teori 3",
                "RK Teori 4", "RK Teori 5", "RK Teori 6", "RK Teori 7", "RK Teori 8",
                "RK Teori 9", "RK Teori 10", "RK Teori 11", "RK Teori 12"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ruangan);

        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }

        });
        btnWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = etNama.getText().toString();
                String strTanggal = etTanggal.getText().toString();
                String strWaktu = etWaktu.getText().toString();
                Intent pesan = new Intent(MainActivity.this,HasilActivity.class);
                if (strNama.matches("")||strTanggal.matches("")||strWaktu.matches("")){
                    Toast.makeText(MainActivity.this,"Harap Diisi Semua",Toast.LENGTH_SHORT).show();
                }else{
                    pesan.putExtra("nama",strNama);
                    pesan.putExtra("ruang", ruangan[index]);
                    pesan.putExtra("tanggal",strTanggal);
                    pesan.putExtra("waktu",strWaktu);
                    startActivity(pesan);
                }
            }
        });
    }

    public void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                etTanggal.setText(dateFormat.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public void showTimeDialog(){
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etWaktu.setText(hourOfDay+":"+minute);
            }
        },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }
}
