package com.example.hugobordais;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListProspectActivity extends ListActivity {

    Button return_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prospect);

        return_home = findViewById(R.id.return_home);
        return_home.setOnClickListener(return_listener);

        /*      --Utilisations de la lib Volley--   https://developer.android.com/training/volley

         * Ajouter la lib dans le build.gradle (Module: app) -> implementation 'com.android.volley:volley:1.1.1'
         * AJouter la permission Intenet à l'utilisateur dans le manifest -> <uses-permission android:name="android.permission.INTERNET" />
         *
         * Création d'un objet de type JsonAarrayRequest, l'objet prend en paramètre l'url de ce qu'il doit aller chercher
         * Instanciations de la classe Response.Listener qui est la réponse à un event à partir du moment où il n'y pas eu d'erreur
         * il prend en paramètre un tableau de JSON object.
         * Dans la classe Responde.Listener on fait une méthode onResponse qui prend en paramètre le tableau response
         * On fait une boucle qui parcours la tableau response
         * On fait un try/catch, dans le try :
         *                                     - On créer un objet JSONObject qui est égal à l'index d'un objet du tableau réponse
         *                                     - On vérifie avec un Log si on trouve bien
         * Dans le catch, si il y à une erreur tu fais une JSONException et tu l'écris dans le Logcat
         *
         * En 2ème partie, on instancie une classe Response.ErrorListener qui intervient à partir du moment où il y à eu une erreur lors
         * de la "connexion" avec l'url.
         * Il y à une méthode dedans qui écrit dans le Logcat l'erreur
         */


        // a faire, trouver comme on récupere le resultat et le renvoyer avec la lib
        JsonArrayRequest request = new JsonArrayRequest("http://dev.audreybron.fr/flux/flux_prospects.json",

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<ProspectTemplate> Maliste = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                ProspectTemplate prospectTemplate;
                                JSONObject prospect = response.getJSONObject(i);
                                prospectTemplate = new ProspectTemplate(prospect);
                                Maliste.add(prospectTemplate);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }

    private View.OnClickListener return_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
