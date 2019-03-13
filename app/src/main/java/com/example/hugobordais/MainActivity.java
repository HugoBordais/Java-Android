package com.example.hugobordais;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //    Déclaration
    EditText address_input;
    EditText password_input;
    Button clean_button;
    Button sign_in_button;
    String error_message = "The rules are not respected, please retry. Insert correct Email, and Password.";
    String valid_message = "Is ok !";
    String addressPref;
    String passwordPref;
    Toast goodtoast;
    Toast badtoast;
    CheckBox checkBox_button_input;
    
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        attributions des variables aux ID
        address_input = findViewById(R.id.address_input);
        password_input = findViewById(R.id.password_input);
        clean_button = findViewById(R.id.clean_button);
        sign_in_button = findViewById(R.id.sign_in_button);
        checkBox_button_input = findViewById(R.id.checkBox_input);


//        Set à partir du moment où on clic
        clean_button.setOnClickListener(clear_listener);
        sign_in_button.setOnClickListener(login_listener);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

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

//            Crée un toast selon le message
            goodtoast = Toast.makeText(getApplicationContext(), valid_message, Toast.LENGTH_SHORT);
            badtoast = Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_LONG);

             /*Permet de récuperer address_input et le transformer en string afin de pouvoir compter le nombre
            de caratère dans la chaine*/
            String address_string = address_input.getText().toString();
            String password_string = password_input.getText().toString();


            if (address_string.equalsIgnoreCase("")) {
                address_input.setError("Enter Address");
                badtoast.show();

                if (password_string.length() < 8) {
                    password_input.setError("8 caractere mini");
                    badtoast.show();
                }

            } else {

                if (checkBox_button_input.isChecked()) {

                    addressPref

                    goodtoast.show();
                    Intent intent = new Intent(MainActivity.this, ListProspectActivity.class);
                    startActivity(intent);
                }
            }
        }
    };
}