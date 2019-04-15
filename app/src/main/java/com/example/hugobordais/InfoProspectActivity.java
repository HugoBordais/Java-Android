package com.example.hugobordais;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hugobordais.models.Prospect;

public class InfoProspectActivity extends AppCompatActivity {

    TextView info_name;
    TextView info_firstName;
    TextView info_address1;
    TextView info_address2;
    TextView info_postCode;
    TextView info_mobile;
    TextView info_city;
    TextView info_mail;
    TextView info_companyName;

    Button buttonCall;

    Prospect prospect;

    /*
    On veut quoi ?
    - Récupérer les informations du prospect correspondant
    - Les afficher dans des TextView

    Qu'est-ce qu'on a ?
    - On récupére les données dans le ListProspectActivity
    - On traite les données
    - On affiche uniquement le name et le mail

    Que doit-on faire ?
    - Récupérer les données qui sont déjà traitées dans le ListProspectActivity
    - On affiche les données que l'on souhaite selon l'ID du prospect
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_prospect);

        Intent intent = getIntent();
        prospect = (Prospect) intent.getExtras().getSerializable("infoprospect");

        info_firstName = (TextView) findViewById(R.id.info_name);
        info_firstName.setText(prospect.getFirstname());

        info_name = (TextView) findViewById(R.id.info_firstname);
        info_name.setText(prospect.getName());

        info_address1 = (TextView) findViewById(R.id.info_address1);
        info_address1.setText(prospect.getAddresse1());

        info_address2 = (TextView) findViewById(R.id.info_address2);
        info_address2.setText(prospect.getAddresse2());

        info_postCode = (TextView) findViewById(R.id.info_postCode);
        info_postCode.setText(prospect.getPostCode());

        info_mobile = (TextView) findViewById(R.id.info_mobile);
        info_mobile.setText(prospect.getMobile());

        info_city = (TextView) findViewById(R.id.info_city);
        info_city.setText(prospect.getCity());

        info_mail = (TextView) findViewById(R.id.info_mail);
        info_mail.setText(prospect.getMail());

        info_companyName = (TextView) findViewById(R.id.info_companyName);
        info_companyName.setText(prospect.getCompanyName());

        buttonCall = (Button) findViewById(R.id.buttonCall);
        buttonCall.setOnClickListener(call);
    }

    private View.OnClickListener call = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent Icall = new Intent(Intent.ACTION_DIAL);
            Icall.setData(Uri.parse("tel:" + prospect.getMobile()));
            startActivity(Icall);
        }
    };

    public void destroy(View v) {
        finish();
    }

}