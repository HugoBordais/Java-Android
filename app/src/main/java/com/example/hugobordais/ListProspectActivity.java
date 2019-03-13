package com.example.hugobordais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListProspectActivity extends AppCompatActivity {

    Button return_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prospect);

        return_home = findViewById(R.id.return_home);

        return_home.setOnClickListener(return_listener);
    }

    private View.OnClickListener return_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(ListProspectActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}
