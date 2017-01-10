package com.example.dm2.ejerciciosxml;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Actividad1 extends AppCompatActivity {

    List<Noticia> listaNoticias;
    AdaptadorNoticias adaptador;
    private ListView listViewNoticias;
    private TextView labelTitulo,labelLink;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        CargarXmlTask tarea=new CargarXmlTask();
        tarea.execute("http://www.20minutos.es/rss/deportes/");

    }


    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserXmlPull rs=new RssParserXmlPull(params[0]);
            listaNoticias=rs.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            adaptador=new AdaptadorNoticias(getBaseContext(),listaNoticias);
            listViewNoticias=(ListView) findViewById(R.id.ListViewNoticias);
            listViewNoticias.setAdapter(adaptador);

            /*listViewNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // La posición donde se hace clic en el elemento de lista se obtiene de la
                    // la posición de parámetro de la vista de lista de Android
                    int posicion = position;
                    //obtengo el valor del string del elemento donde se hizo clic
                    String itemValue = (String) listViewNoticias.getItemAtPosition(position);

                    //Con el fin de empezar a mostrar una nueva actividad lo que necesitamos es una intención
                    //Intent intent = new Intent(getApplicationContext(),LenguajeActivity.class);

                    // Poner el Id de la imagen como extra en la intención
                    //intent.putExtra("logo",LogoId[position]);
                    // Aquí pasaremos el parámetro de la intención creada previamente
                    //startActivity(intent);
                }
            });*/

        }
    }

    class AdaptadorNoticias extends ArrayAdapter<Noticia> {
        public AdaptadorNoticias(Context context, List<Noticia> listaNoticias){
            super(context,R.layout.activity_actividad2,listaNoticias);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View item=inflater.inflate(R.layout.activity_actividad1,null);

            labelTitulo=(TextView) item.findViewById(R.id.labelTitulo);
            labelTitulo.setText(listaNoticias.get(position).getTitulo());

            labelLink=(TextView) item.findViewById(R.id.labelLink);
            labelLink.setText(listaNoticias.get(position).getLink());

            return(item);
        }
    }
}
