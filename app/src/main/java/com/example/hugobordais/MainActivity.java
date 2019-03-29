package com.example.hugobordais;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/* A faire :
    - Intérroger un serveur pour le JSON avec un Web Service
    - Parser le JSON
    - Afficher la liste avec un ListView*/


public class MainActivity extends AppCompatActivity {

    //    --DÉCLARATION--

    // EditText
    EditText address_input;
    EditText password_input;

    // Button
    Button clean_button;
    Button sign_in_button;

    // String
    String error_message = "The rules are not respected, please retry. Insert correct Email, and Password.";
    String address_string;
    String password_string;
    // Toast
    Toast badtoast;

    //    SharedPreferences
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    //    Other
    CheckBox checkBox_button_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attributions des variables aux ID
        address_input = findViewById(R.id.address_input);
        password_input = findViewById(R.id.password_input);
        checkBox_button_input = findViewById(R.id.checkBox_input);

        clean_button = findViewById(R.id.clean_button);
        sign_in_button = findViewById(R.id.sign_in_button);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        //Set à partir du moment où on clic
        clean_button.setOnClickListener(clear_listener);
        sign_in_button.setOnClickListener(login_listener);

        address_string = preferences.getString("address", "");
        password_string = preferences.getString("password", "");

        if (!address_string.equals("") && !password_string.equals("")) {
            address_input.setText(address_string);
            password_input.setText(password_string);
            checkBox_button_input.setChecked(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("DESTROY", "méthode destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("PAUSE", "méthode pause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Resume", "méthode resume");
    }


    private View.OnClickListener clear_listener = new View.OnClickListener() {
        @Override
//        Dès qu'on clic, change remplaces les chaines de caractère par ...
        public void onClick(View v) {
            address_input.setText("");
            password_input.setText("");
        }
    };

    private View.OnClickListener login_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Crée un toast selon le message
            badtoast = Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_LONG);

             /*Permet de récuperer address_input et le transformer en string afin de pouvoir compter le nombre
            de caratère dans la chaine*/
            address_string = address_input.getText().toString();
            password_string = password_input.getText().toString();


            if (address_string.equalsIgnoreCase("")) {
                address_input.setError("Enter Address");
                badtoast.show();

                if (password_string.length() < 8) {
                    password_input.setError("8 caractere mini");
                    badtoast.show();
                }

            } else {

                if (checkBox_button_input.isChecked()) {
                    editor.putString("address", address_string);
                    editor.putString("password", password_string);
                    editor.commit();

                } else {
                    editor.putString("address", "");
                    editor.putString("password", "");
                    editor.commit();
                }

                Intent intent = new Intent(MainActivity.this, ListProspectActivity.class);
                startActivity(intent);
            }
        }
    };
}