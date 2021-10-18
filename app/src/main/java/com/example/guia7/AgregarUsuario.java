package com.example.guia7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    private int contadorUsuario = 0;

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
                    this.usuario.setNombre(ed_nick.getText().toString());
                    ///Preguntar antes de guardar
                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarUsuario.this);
                    builder.setTitle("Confirmacion");
                    builder.setMessage("¿Estas seguro?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Usuario usuario = new Usuario();
                            ///Guardar datos en tablas hash
                            /*OJO: Verificar si es accesible el objeto Usuario dentro de
                            otro Activity, ya que esta implementación está hecha dentro de una
                            interfaz la cual encapsula el código
                             */
                            Intent intent = new Intent(AgregarUsuario.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("nick", usuario.getNombre());
                            intent.putExtras(bundle);
                            intent.putExtra("usuario", usuario);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Cancelar",null );
                    AlertDialog dialog = builder.create();
                    dialog.show();

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