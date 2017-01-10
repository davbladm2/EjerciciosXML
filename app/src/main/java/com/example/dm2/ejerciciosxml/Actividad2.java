package com.example.dm2.ejerciciosxml;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class Actividad2 extends AppCompatActivity {

    List<Tiempo> listaTiempo;
    AdaptadorTiempo adaptador;
    private ListView listViewTiempo;
    private TextView labelFecha,labelCiudad,labelTempMax,labelTempMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        CargarXmlTask tarea=new CargarXmlTask();
        tarea.execute("http://www.aemet.es/xml/municipios/localidad_01059.xml");
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RssParserDom rs=new RssParserDom(params[0]);
            listaTiempo=rs.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {
            adaptador=new AdaptadorTiempo(getBaseContext(),listaTiempo);
            listViewTiempo=(ListView) findViewById(R.id.ListViewTiempo);
            listViewTiempo.setAdapter(adaptador);
        }
    }

    class AdaptadorTiempo extends ArrayAdapter<Tiempo> {
        public AdaptadorTiempo(Context context, List<Tiempo> listaTiempo){
            super(context,R.layout.activity_actividad2,listaTiempo);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View item=inflater.inflate(R.layout.activity_actividad2,null);

            labelFecha=(TextView) item.findViewById(R.id.labelFecha);
            labelFecha.setText(listaTiempo.get(position).getFechaT());

            labelTempMax=(TextView) item.findViewById(R.id.labelTempMax);
            labelTempMax.setText("Temp. Máxima: "+listaTiempo.get(position).getTemperaturaMax());

            labelTempMin=(TextView) item.findViewById(R.id.labelTempMin);
            labelTempMin.setText("Temp. Mínima: "+listaTiempo.get(position).getTempraturaMin());
            return(item);
        }
    }
}
