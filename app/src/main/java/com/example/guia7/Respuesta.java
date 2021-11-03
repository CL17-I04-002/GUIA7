package com.example.guia7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Respuesta extends AppCompatActivity {
    private TextView tx_valor_respuesta;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tx_valor_respuesta = (TextView) findViewById(R.id.tx_valor_respuesta);
        getRespuesta();
    }
    private void getRespuesta(){
        shared = getSharedPreferences(AgregarUsuario.NAME_FILE, MODE_PRIVATE);
        tx_valor_respuesta.setText(shared.getString("numCorrecto", "0"));
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