package com.example.dm2.ejerciciosxml;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssParserXmlPull {

    private URL rssUrl;

    public RssParserXmlPull(String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<Noticia> parse(){
        List<Noticia> noticias=null;
        XmlPullParser parser= Xml.newPullParser();

        try {
            parser.setInput(this.getInputStream(),null);
            int evento=parser.getEventType();
            Noticia noticiaActual=null;

            while (evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_DOCUMENT:
                        noticias=new ArrayList<Noticia>();
                        break;
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("item")){
                            noticiaActual=new Noticia();
                        }else if(noticiaActual!=null){
                            if(etiqueta.equals("link")){
                                noticiaActual.setLink(parser.nextText());
                            }else if(etiqueta.equals("descripcion")){
                                noticiaActual.setDescripcion(parser.nextText());
                            }else if(etiqueta.equals("pubdate")){
                                noticiaActual.setDescripcion(parser.nextText());
                            }else if(etiqueta.equals("title")){
                                noticiaActual.setTitulo(parser.nextText());
                            }else if(etiqueta.equals("guid")){
                                noticiaActual.setGuid(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("item") && noticiaActual!=null){
                            noticias.add(noticiaActual);
                        }
                        break;
                }
                evento=parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noticias;
    }

    private InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
