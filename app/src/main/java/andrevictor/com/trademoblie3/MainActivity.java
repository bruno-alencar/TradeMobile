package andrevictor.com.trademoblie3;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

//AppCompatActivity,

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    static ArrayList<String> lugares;
    static ArrayAdapter arrayAdapter;
    static ArrayList<LatLng> localizacoes;

    //Componentes
    GoogleMap mapa;
    FloatingActionButton botaoMenu,floatingActionButton1, floatingActionButton2, floatingActionButton3;
    ListView listaPrincipal;
    FloatingActionMenu materialDesignFAM;


    String[] permissoes = new String[]{
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPrincipal = (ListView) findViewById(R.id.ListPrincipal);

        PermissionUtils.validate(this,0,permissoes);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentPrincipal);
        mapFragment.getMapAsync(this);

        lugares = new ArrayList<>();
        lugares.add("Adicione uma nova rota");

        localizacoes = new ArrayList<>();
        localizacoes.add(new LatLng(0, 0));
        Log.i("Aqui", "passei aqui");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lugares);
        listaPrincipal.setAdapter(arrayAdapter);

        listaPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Aqui tb", "passei aqui");
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("infoLocalizacao", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        mapa.getUiSettings().setAllGesturesEnabled(false);

        LatLng ponto = new LatLng(-23.581485,-46.638507);
        mapa.addMarker(new MarkerOptions().position(ponto));

        //Dar zoom no mapa
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(ponto,18));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        for (int result : grantResults){
            if(result == PackageManager.PERMISSION_DENIED) {
                //Alguma permissao foi negada
                alertAndFinish();
                return;
            }
        }
        //Se chegou ate aqui esta OK
    }

    private void alertAndFinish(){
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, voce precisa aceitar as permissoes.");
            //Adiciona os botoes
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
