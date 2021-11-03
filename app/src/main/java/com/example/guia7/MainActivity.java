package com.example.guia7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.guia7.model.Usuario;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*Intent intent = getIntent();
        Bundle bundle = new Bundle();
        this.usuarioSerializable = (Usuario) intent.getSerializableExtra("usuario");*/

    }
    public void Jugar(View view){
        try {
            if (!validate()) {
                Toast.makeText(MainActivity.this, "Por favor inserte un usuario", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Juegue", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Jugar.class));
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error: ", Toast.LENGTH_SHORT).show();
        }
        ///Toast.makeText(this, usuarioSerializable.getNombre(), Toast.LENGTH_LONG).show();
        /*if(usuarioSerializable == null){
            Toast.makeText(MainActivity.this, "Ingrese un usuario", Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(this, Jugar.class));*/
            ///this.usuario.setNombre(this.usuarioSerializable.getNombre());
            ///Activity Usuario
            ///Toast.makeText(MainActivity.this, "Jugar", Toast.LENGTH_SHORT).show();
            /*Intent intent2 = new Intent(MainActivity.this, Jugar.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("nick2", this.usuarioSerializable.getNombre());
            intent2.putExtras(bundle2);
            intent2.putExtra("usuario2", this.usuarioSerializable);
            startActivity(intent2);

            ///startActivity(new Intent(MainActivity.this, Jugar.class));
        }*/
    }
    public void verRespuesta(View view){
        startActivity(new Intent(MainActivity.this, Respuesta.class));
    }
    public void verPuntaje(View view){
        startActivity(new Intent(MainActivity.this, Puntaje.class));
    }
    public void verDatos(View view){
        startActivity(new Intent(MainActivity.this, Datos.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_add_user:
                startActivity(new Intent(this, AgregarUsuario.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private boolean validate(){
        boolean  b= false;
        File f = new File("/data/data/com.example.guia7/shared_prefs/config.xml");
        if(f.exists()){
            b = true;
        }
        return b;
    }

}