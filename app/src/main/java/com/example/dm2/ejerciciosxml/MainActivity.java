package com.example.dm2.ejerciciosxml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pulsado(View v) {
        Intent intent;

        if(v.getId() == R.id.buttonActividad1){
            intent=new Intent(MainActivity.this,Actividad1.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.buttonActividad2){
            intent=new Intent(MainActivity.this,Actividad2.class);
            startActivity(intent);
        }
    }
}
