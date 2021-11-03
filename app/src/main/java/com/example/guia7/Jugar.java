package com.example.guia7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guia7.model.Usuario;

public class Jugar extends AppCompatActivity {

    private TextView tx_nick;
    private EditText ed_numero;
    private int numeroRandom;
    private int contadorPuntaje =10;
    private int intentos = 0;
    private TextView tx_puntaje;
    private TextView tx_intentos;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ed_numero = (EditText) findViewById(R.id.edt_numero);
        tx_puntaje = (TextView) findViewById(R.id.txv_puntaje);
        tx_intentos = (TextView) findViewById(R.id.txv_intentos);
        tx_nick = (TextView) findViewById(R.id.txv_nick_name);
        addUser();


    }
    public void adivinar(View view) {
        int ed_numero_int = Integer.parseInt(ed_numero.getText().toString());
        try {
                if (ed_numero_int > 0 && ed_numero_int < 11) {
                    this.sharedPreferences = getSharedPreferences(AgregarUsuario.NAME_FILE, MODE_PRIVATE);
                    if (ed_numero.getText().toString().equals(sharedPreferences.getString("numCorrecto", "0"))) {
                        ////SCORE
                        int puntaje_int = Integer.parseInt(sharedPreferences.getString("puntaje", "") + contadorPuntaje);



                        SharedPreferences.Editor editorConfig = sharedPreferences.edit();
                        editorConfig.putString("puntaje", String.valueOf(puntaje_int));
                        editorConfig.commit();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Jugar.this);
                        builder.setTitle("Confirmacion");
                        builder.setMessage("Felicidades, has ganado");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        ed_numero.setText("");
                    } else {
                        int numero_actual = Integer.parseInt(ed_numero.getText().toString());
                        int numero_correcto = Integer.parseInt(sharedPreferences.getString("numCorrecto", ""));
                        contadorPuntaje--;
                        intentos++;
                        tx_intentos.setText("Intentos realizados: " + (intentos));
                        ed_numero.setText("");

                        if (numero_actual > numero_correcto) {
                            Toast.makeText(Jugar.this, "El numero oculto es menor", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Jugar.this, "El numero oculto es mayor", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Jugar.this, "Número fuera de rango", Toast.LENGTH_SHORT).show();
                }
        }catch (Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void addUser(){
        this.sharedPreferences = getSharedPreferences(AgregarUsuario.NAME_FILE, MODE_PRIVATE);
        if(this.sharedPreferences !=null){
            tx_nick.setText(this.sharedPreferences.getString("nick", "0"));
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