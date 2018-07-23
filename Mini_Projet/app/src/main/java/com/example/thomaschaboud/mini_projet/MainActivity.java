package com.example.thomaschaboud.mini_projet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Meteo> tabSemaine;
    private MeteoAdapter madapt;
    private ListView maListView;
    private HashMap<String, Integer> pictos;
    private HashMap<String, String> villes;
    private boolean isConnected;
    private String filenameP;
    private String filenameF;
    private AutoCompleteTextView textVille;
    private Button b;
    private ArrayList<String> favoris;//liste des favoris
    private Spinner spinner;
    private ArrayAdapter<String> adapterSpinner;
    private MenuItem etoile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button);
        maListView = (ListView) findViewById(R.id.listView);
        tabSemaine = new ArrayList<Meteo>();
        favoris = new ArrayList<String>();
        favoris.add(" - ");

        pictos = new HashMap<String, Integer>();//on stocke dans une map les noms des pictos associés à l'id de leur image
        pictos.put("averse", R.mipmap.aversepluie);
        pictos.put("averseneige", R.mipmap.averseneige);
        pictos.put("brouillard", R.mipmap.brouillard);
        pictos.put("brouillardgivrant", R.mipmap.brouillardgivrant);
        pictos.put("couvert", R.mipmap.couvert);
        pictos.put("lune", R.mipmap.lune);
        pictos.put("luneaverse", R.mipmap.luneaverse);
        pictos.put("luneaverseneige", R.mipmap.luneaverseneige);
        pictos.put("lunenuageux", R.mipmap.lunenuageux);
        pictos.put("lunevoile", R.mipmap.lunevoile);
        pictos.put("neigefaible", R.mipmap.neigefaible);
        pictos.put("neigemoderer", R.mipmap.neigemoderer);
        pictos.put("neigeforte", R.mipmap.neigeforte);
        pictos.put("nuageux", R.mipmap.nuageux);
        pictos.put("oragefort", R.mipmap.oragefort);
        pictos.put("orageloc", R.mipmap.orageloc);
        pictos.put("pluiefaible", R.mipmap.pluiefaible);
        pictos.put("pluiemoderer", R.mipmap.pluiemoderer);
        pictos.put("pluieforte", R.mipmap.pluieforte);
        pictos.put("soleil", R.mipmap.soleil);
        pictos.put("verglas", R.mipmap.verglas);
        pictos.put("voile", R.mipmap.voile);
        pictos.put("undefined", R.mipmap.undefined);

        ArrayList<String> villesEditView = new ArrayList<String>();

        try{//récupération des villes avec leur id
            InputStream is = getAssets().open("villes.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            villes = new HashMap<String, String>();//on stocke dans une map les noms des villes associés à l'id de leur id
            String line = "";
            while ((line = br.readLine()) != null){
                String[] tmp = line.split("\"");
                villesEditView.add(tmp[3]);
                villes.put(tmp[3].toUpperCase(),tmp[1]);
            }
            br.close();
        }catch(Exception e){
            System.out.println("erreur lecture ville: " + e);
        }

        //récupération des prévisions stockées sur le téléphone
        filenameP = getApplicationContext().getFilesDir().getPath() + "previsions.txt";
        try{
            FileInputStream fis = new FileInputStream(filenameP);
            ObjectInputStream in = new ObjectInputStream(fis);
            tabSemaine = (ArrayList<Meteo>) in.readObject();
            in.close();
        }catch(Exception e){
            System.out.println("erreur lecture fichier previsions: " + e);
        }

        //récupération des favoris stockées sur le téléphone
        filenameF = getApplicationContext().getFilesDir().getPath() + "favoris.txt";
        try{
            FileInputStream fis = new FileInputStream(filenameF);
            ObjectInputStream in = new ObjectInputStream(fis);
            favoris = (ArrayList<String>) in.readObject();
            in.close();
        }catch(Exception e){
            System.out.println("erreur lecture fichier favoris: " + e);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, villesEditView);
        textVille = (AutoCompleteTextView) findViewById(R.id.nomVille);
        textVille.setAdapter(adapter);

        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, favoris);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapterSpinner);

        madapt = new MeteoAdapter(MainActivity.this, tabSemaine, pictos);
        maListView.setAdapter(madapt);

        if(tabSemaine.size() != 0){
            setTitle("Prévisions pour " + tabSemaine.get(0).getNomVille());
        }

        maListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//click pour affichage plus détaillé

                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                //on attribut un titre à notre boite de dialogue
                adb.setTitle(tabSemaine.get(position).getTitle());
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage(tabSemaine.get(position).getTitle() + " - " + tabSemaine.get(position).getDate() + "\n" +
                        "Prévision midi: " + tabSemaine.get(position).getSeeing_midi() + "\n\n" +
                        "Vent midi: " + tabSemaine.get(position).getVent_midi() + "\n\n" +
                        "Température midi: " + tabSemaine.get(position).getTempe_midi() + "\n\n" +
                        "Jour: " + tabSemaine.get(position).getJour() + "\n\n" +
                        "Précipition midi: " + tabSemaine.get(position).getPrecipitations_midi() + "\n\n" +
                        "Température après-midi: " + tabSemaine.get(position).getTempe_apmidi() + "\n\n" +
                        "Vent après-midi: " + tabSemaine.get(position).getVent_apmidi() + "\n\n" +
                        "Prévisions après-midi: " + tabSemaine.get(position).getSeeing_apmidi() + "\n\n" +
                        "Précipitations après-midi: " + tabSemaine.get(position).getPrecipitations_apmidi() + "\n\n" +
                        "Température soir: " + tabSemaine.get(position).getTempe_soir() + "\n\n" +
                        "Vent soir: " + tabSemaine.get(position).getVent_soir() + "\n\n" +
                        "Prévisions soir: " + tabSemaine.get(position).getSeeing_soir() + "\n\n" +
                        "Précipitations soir: " + tabSemaine.get(position).getPrecipitations_soir() + "\n\n" +
                        "Pubdate: " + tabSemaine.get(position).getPubdate());
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                //on affiche la boite de dialogue
                adb.show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if(!isConnected){
                    String text = "Connexion indisponible";
                    int duration = Toast.LENGTH_SHORT;
                    Toast tartine = Toast.makeText(context,text,duration);
                    tartine.show();
                }
                else{
                    String chaineVille = textVille.getText().toString();
                    if(villes.get(chaineVille.toUpperCase()) != null){
                        setTitle("Prévisions pour " + chaineVille);
                        new MyAsyncTask().execute(tabSemaine, madapt, villes.get(chaineVille.toUpperCase()), chaineVille);
                        VerifFavoris(chaineVille);
                    }
                    else{
                        String text = "Ville introuvable";
                        int duration = Toast.LENGTH_SHORT;
                        Toast tartine = Toast.makeText(context,text,duration);
                        tartine.show();
                    }
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinner.getSelectedItem().toString().equals(" - ")){
                    setTitle("Prévisions pour " + spinner.getSelectedItem().toString());
                    new MyAsyncTask().execute(tabSemaine, madapt, villes.get(spinner.getSelectedItem().toString().toUpperCase()), spinner.getSelectedItem().toString());
                    VerifFavoris(spinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_meteo à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_meteo, menu);
        etoile =  menu.findItem(R.id.action_favoris);
        if(!tabSemaine.isEmpty()){
            VerifFavoris(tabSemaine.get(0).getNomVille());
        }
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favoris:
                changeFavoris();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void VerifFavoris(String ville){
        if(favoris.contains(ville)){
            etoile.setIcon(R.mipmap.is_in_favoris);
        }else{
            etoile.setIcon(R.mipmap.is_not_in_favoris);
        }
    }

    public void changeFavoris(){
        String text;
        if(!tabSemaine.isEmpty()){//si une ville n'est affichée
            if(favoris.contains(tabSemaine.get(0).getNomVille())){
                favoris.remove(tabSemaine.get(0).getNomVille());
                text = tabSemaine.get(0).getNomVille() + " a été retirée de vos favoris";
                etoile.setIcon(R.mipmap.is_not_in_favoris);
            }
            else{
                favoris.add(tabSemaine.get(0).getNomVille());
                adapterSpinner.notifyDataSetChanged();
                text = tabSemaine.get(0).getNomVille() + " a été ajouté aux favoris";
                etoile.setIcon(R.mipmap.is_in_favoris);
            }
            int duration = Toast.LENGTH_SHORT;
            Toast tartine = Toast.makeText(getApplicationContext(),text,duration);
            tartine.show();
        }
        else{
            text = "Vous devez séléctionner une ville";
            int duration = Toast.LENGTH_SHORT;
            Toast tartine = Toast.makeText(getApplicationContext(),text,duration);
            tartine.show();
        }
    }

    @Override
    protected void onStop() {//on sauvegarde dans un fichier les données affichées
        super.onStop();
        try{
            FileOutputStream fos = new FileOutputStream(filenameP);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tabSemaine);
            oos.close();
        }catch(Exception e){
            System.out.println("erreur ecriture fichier previsions: " + e);
        }
        try{
            FileOutputStream fos = new FileOutputStream(filenameF);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(favoris);
            oos.close();
        }catch(Exception e){
            System.out.println("erreur ecriture fichier favoris: " + e);
        }
    }

}
