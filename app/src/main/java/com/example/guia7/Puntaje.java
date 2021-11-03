package com.example.guia7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Puntaje extends AppCompatActivity {

    private TextView txt_nick;
    private TextView txt_puntaje;
    private SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txt_nick = (TextView) findViewById(R.id.txv_nick);
        txt_puntaje = (TextView) findViewById(R.id.txv_puntaje);
        getNick_Puntaje();
    }
    private void getNick_Puntaje(){
        this.shared = getSharedPreferences(AgregarUsuario.NAME_FILE, MODE_PRIVATE);
        if(this.shared != null) {
            txt_nick.setText(this.shared.getString("nick", ""));
            txt_puntaje.setText(this.shared.getString("puntaje", "0"));
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return true;
    }

}