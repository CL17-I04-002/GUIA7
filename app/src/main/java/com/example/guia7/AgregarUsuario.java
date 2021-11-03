package com.example.guia7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guia7.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AgregarUsuario extends AppCompatActivity {

    private EditText ed_nick;
    private Usuario usuario;
    private String nick;
    public int contadorUsuario = 0;
    public static SharedPreferences shared;
    public static String NAME_FILE = "config";
    public static int numCorrecto=0;
    private int puntaje=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        this.usuario = new Usuario();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ed_nick = (EditText) findViewById(R.id.edt_nick);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_save_nick: {
                if(ed_nick.length() >0){
                    ///Preguntar antes de guardar
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(AgregarUsuario.this);
                    builder.setTitle("Confirmacion");
                    builder.setMessage("¿Estas seguro?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {*/
                            ///Usuario usuario = new Usuario();
                            ///Guardar datos en tablas hash
                            /*OJO: Verificar si es accesible el objeto Usuario dentro de
                            otro Activity, ya que esta implementación está hecha dentro de una
                            interfaz la cual encapsula el código
                             */

                            /*Intent intent = new Intent(AgregarUsuario.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("nick", this.usuario.getNombre());
                            intent.putExtras(bundle);
                            intent.putExtra("usuario", this.usuario);
                            startActivity(intent);*/
                        /*}
                    });*/
                    numCorrecto=(int) (Math.random() * 10) + 1;
                    shared = getSharedPreferences(NAME_FILE, MODE_PRIVATE);
                    SharedPreferences.Editor editConfig= shared.edit();
                    this.usuario.setNombre(ed_nick.getText().toString());
                    editConfig.putString("nick", this.usuario.getNombre());
                    editConfig.putString("numCorrecto", String.valueOf(numCorrecto));
                    editConfig.putString("puntaje", String.valueOf(puntaje));
                    Toast.makeText(AgregarUsuario.this, "Se agregó Usuario", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AgregarUsuario.this, MainActivity.class);
                    startActivity(intent);
                    editConfig.commit();
                    /*Bundle bundle = new Bundle();
                    bundle.putString("nick", this.usuario.getNombre());
                    intent.putExtras(bundle);
                    intent.putExtra("usuario", this.usuario);*/


                    /*builder.setNegativeButton("Cancelar",null );
                    AlertDialog dialog = builder.create();
                    dialog.show();*/

                }else{
                    Toast.makeText(this, "Campo vacío", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_nick, menu);
        return super.onCreateOptionsMenu(menu);
    }

}