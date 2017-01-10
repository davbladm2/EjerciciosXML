package com.example.dm2.ejerciciosxml;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssParserDom {

    private URL rssUrl;

    public RssParserDom (String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse(){
        //Instanciamos la fabrica para DOM
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        List<Tiempo> tiempos=new ArrayList<Tiempo>();

        try {
            //Creamos un nuevo parser DOM
            DocumentBuilder builder=factory.newDocumentBuilder();
            //Realizamos la lectura completa del XML
            Document dom=builder.parse(this.getInputStream());
            //Nos posicionamos en el nodo principal del árbol
            Element root=dom.getDocumentElement();
            //Obtener la ciudad
            NodeList it=root.getElementsByTagName("nombre");
            //Localizamos todos los elementos <item>
            NodeList items=root.getElementsByTagName("dia");
            //Recorremos la lista de tiempo
            for(int i=0;i<items.getLength();i++){
                Tiempo tiempo=new Tiempo();
                //Obtenemos el dia actual
                Node item=items.item(i);
                NamedNodeMap nod=item.getAttributes();
                tiempo.setFechaT(nod.item(0).getNodeValue());
                //Obtenemos la lista de datos de la dia actual
                NodeList datosTiempos=item.getChildNodes();
                //Procesamos cada dato de la tiempo
                for(int j=0;j<datosTiempos.getLength();j++){
                    Node dato=datosTiempos.item(j);
                    String etiqueta=dato.getNodeName();
                     if(etiqueta.equals("temperatura")){
                         NodeList temps=dato.getChildNodes();
                         for(int x=0;x<temps.getLength();x++){
                             Node tempera=temps.item(x);
                             String etiquetaTem=tempera.getNodeName();
                             //Obtener la temperatura maxima
                             if(etiquetaTem.equals("maxima")){
                                 String texto=tempera.getLastChild().getNodeValue();
                                 tiempo.setTemperaturaMax(texto);
                             }
                             //Obtener la temperatura minima
                             if(etiquetaTem.equals("minima")){
                                 String texto=tempera.getLastChild().getNodeValue();
                                 tiempo.setTempraturaMin(texto);
                             }
                         }
                    }
                }
                tiempos.add(tiempo);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return tiempos;
    }

    public String obtenerTexto (Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();
        for(int k=0;k<fragmentos.getLength();k++){
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    private InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
