package com.example.hugobordais;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hugobordais.models.Prospect;
import com.example.hugobordais.request.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class ListProspectActivity extends ListActivity {


    private TextView text;
    protected List<Prospect> listProspects;
    private ProgressDialog pDialog;
    private ProspectsAdapter adapter;
    // URL to get prospects JSON
    private static String url = "http://dev.audreybron.fr/flux/flux_prospects.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prospect);

        // initiate the listadapter
        listProspects = new ArrayList<Prospect>();
        adapter = new ProspectsAdapter(this, listProspects);
        GetProspects task = new GetProspects();
        task.execute();
        // assign the list adapter
        setListAdapter(adapter);
    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);


        Prospect info = (Prospect) getListView().getItemAtPosition(position);
        Intent intent = new Intent(this, InfoProspectActivity.class);
        intent.putExtra("infoprospect", info);
        startActivity(intent);
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetProspects extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            //Log.e("YNOV", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonObj = new JSONArray(jsonStr);

                    // looping through All Prospects
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);

                        String name = c.getString("Nom");
                        String firstname = c.getString("Prénom");
                        String address1 = c.getString("Adresse 1");
                        String address2 = c.getString("Adresse 2");
                        String postCode = c.getString("Code Postal");
                        String mobile = c.getString("Téléphone");
                        String city = c.getString("Ville");
                        String mail = c.getString("Mail");
                        String companyName = c.getString("Nom Entreprise");

                        Prospect prospect = new Prospect();

                        prospect.setName(name);
                        prospect.setFirstname(firstname);
                        prospect.setAddresse1(address1);
                        prospect.setAddresse2(address2);
                        prospect.setPostCode(postCode);
                        prospect.setMobile(mobile);
                        prospect.setCity(city);
                        prospect.setMail(mail);
                        prospect.setCompanyName(companyName);

                        // adding contact to contact list
                        listProspects.add(prospect);
                    }
                } catch (final JSONException e) {
                    Log.e("YNOV", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("YNOV", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            // Mise à jour de la liste
            adapter.addAll(listProspects);
            adapter.notifyDataSetChanged();

        }

    }

    public class ProspectsAdapter extends ArrayAdapter<Prospect> {

        public ProspectsAdapter(Context context, List<Prospect> prospects) {
            super(context, 0, prospects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Prospect prospect = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
            }
            // Lookup view for data population
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView mail = (TextView) convertView.findViewById(R.id.email);
            // Populate the data into the template view using the data object
            name.setText(prospect.getName());
            mail.setText(prospect.getMail());
            // Return the completed view to render on screen

            return convertView;
        }
    }


    public void destroy(View v) {
        finish();
    }
}