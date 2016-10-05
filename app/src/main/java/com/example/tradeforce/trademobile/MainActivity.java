package com.example.tradeforce.trademobile;

import android.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] permissoes = new String[]{
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtils.validate(this,0,permissoes);

        final TextView textoMapa = (TextView) findViewById(R.id.txtviewMapa);
        Button btnRotas = (Button) findViewById(R.id.btnRotas);

        textoMapa.setText("Teste");
        textoMapa.setTextSize(40);

        btnRotas.setText("Clique aqui");

        btnRotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoMapa.setText("Botao apertado");
            }
        });
    }
}








