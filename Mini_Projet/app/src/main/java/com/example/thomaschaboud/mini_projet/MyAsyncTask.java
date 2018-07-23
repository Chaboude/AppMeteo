package com.example.thomaschaboud.mini_projet;

/**
 * Created by thomaschaboud on 21/03/2017.
 */

import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Object,Void,String>{
    private ArrayList<Meteo> tab;
    private boolean itemPasse;
    private MeteoAdapter ma;
    private String idVille;
    private String nomVille;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ma.notifyDataSetChanged();
    }

    @Override
    protected String doInBackground(Object... params) {
        String rep = "Pas de météo :(";
        tab = (ArrayList<Meteo>) params[0];
        ma = (MeteoAdapter) params[1];
        idVille = (String) params[2];
        nomVille = (String) params[3];
        Meteo m = new Meteo();

        try{
            URL url = new URL("http://api.meteorologic.net/rssid?id=" + idVille);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(urlConnection.getInputStream(), "iso-8859-1");
                tab.clear();
                while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                    if ((xpp.getEventType() == XmlPullParser.START_TAG) && xpp.getName().equals("item")) {
                        itemPasse = true;
                        xpp.nextTag();
                    }
                    if((xpp.getEventType() == XmlPullParser.END_TAG) && xpp.getName().equals("item")){
                        itemPasse = false;
                    }
                    if((xpp.getEventType() == XmlPullParser.START_TAG) && itemPasse){
                            switch (xpp.getName()) {
                                case "title":
                                    m.setTitle(xpp.nextText());
                                    break;
                                case "date":
                                    m.setDate(xpp.nextText());
                                    break;
                                case "jour":
                                    m.setJour(xpp.nextText());
                                    break;
                                case "tempe_matin":
                                    m.setTempe_matin(xpp.nextText() + "°C");
                                    break;
                                case "vent_matin":
                                    m.setVent_matin(xpp.nextText());
                                    break;
                                case "seeing_matin":
                                    m.setSeeing_matin(xpp.nextText());
                                    break;
                                case "precipitations_matin":
                                    m.setPrecipitations_matin(xpp.nextText());
                                    break;
                                case "tempe_midi":
                                    m.setTempe_midi(xpp.nextText() + "°C");
                                    break;
                                case "vent_midi":
                                    m.setVent_midi(xpp.nextText());
                                    break;
                                case "seeing_midi":
                                    m.setSeeing_midi(xpp.nextText());
                                    break;
                                case "precipitations_midi":
                                    m.setPrecipitations_midi(xpp.nextText());
                                    break;
                                case "tempe_apmidi":
                                    m.setTempe_apmidi(xpp.nextText() + "°C");
                                    break;
                                case "vent_apmidi":
                                    m.setVent_apmidi(xpp.nextText());
                                    break;
                                case "seeing_apmidi":
                                    m.setSeeing_apmidi(xpp.nextText());
                                    break;
                                case "precipitations_apmidi":
                                    m.setPrecipitations_apmidi(xpp.nextText());
                                    break;
                                case "tempe_soir":
                                    m.setTempe_soir(xpp.nextText() + "°C");
                                    break;
                                case "vent_soir":
                                    m.setVent_soir(xpp.nextText());
                                    break;
                                case "seeing_soir":
                                    m.setSeeing_soir(xpp.nextText());
                                    break;
                                case "precipitations_soir":
                                    m.setPrecipitations_soir(xpp.nextText());
                                    break;
                                case "pubDate":
                                    m.setPubdate(xpp.nextText());
                                    break;
                                case "pictos_matin":
                                    m.setPicto_matin(xpp.nextText());
                                    break;
                                case "pictos_midi":
                                    m.setPicto_midi(xpp.nextText());
                                    break;
                                case "pictos_apmidi":
                                    m.setPicto_apmidi(xpp.nextText());
                                    break;
                                case "pictos_soir":
                                    m.setPicto_soir(xpp.nextText());
                                    break;
                            }
                    }
                    if((xpp.getEventType() == XmlPullParser.END_TAG)){
                        if(xpp.getName().equals("item")){
                            m.setNomVille(nomVille);
                            tab.add(m);
                            m = new Meteo();
                        }
                    }
                    xpp.next();
                }
                urlConnection.disconnect();
            }
        }catch(Exception e){
            System.out.println("Erreur récupération des données RSS: " + e);
        }
        return rep;
    }
}
