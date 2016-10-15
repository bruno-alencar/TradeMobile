package andrevictor.com.trademoblie3;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    static ArrayList<String> lugares;
    static ArrayAdapter arrayAdapter;
    static ArrayList<LatLng> localizacoes;

  //  private Polyline polyline;
  //  private List<LatLng> list;

    private ProgressDialog load;

    private GoogleMap mMap;
    ListView listaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentRotas);
        mapFragment.getMapAsync(this);

        GetJson download = new GetJson();

        listaPrincipal = (ListView) findViewById(R.id.ListRotas);

        lugares = new ArrayList<>();
        lugares.add("Vire a direita");
        lugares.add("Pegue o onibus");
        lugares.add("Pegue o trem");
        lugares.add("Vire a direita");
        lugares.add("Pegue o onibus");
        lugares.add("Pegue o trem");
        lugares.add("Vire a direita");
        lugares.add("Pegue o onibus");
        lugares.add("Pegue o trem");

        localizacoes = new ArrayList<>();
        localizacoes.add(new LatLng(0, 0));


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lugares);
        listaPrincipal.setAdapter(arrayAdapter);

        //Chama Async Task
        download.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Ponto A
        LatLng pontoA = new LatLng(-23.581485, -46.638807);
        //mMap.addMarker(new MarkerOptions().position(pontoA).title("Ponto A"));

        //drawRoute();

        //Dar zoom no mapa
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pontoA,17));
    }
/*
    public void drawRoute(){
        PolylineOptions po;

        if(polyline == null){
            po = new PolylineOptions();

            for(int i = 0, tam = list.size(); i < tam; i++){
                po.add(list.get(i));
            }

            po.color(Color.BLACK).width(4);
            polyline = mMap.addPolyline(po);
        }
        else{
            polyline.setPoints(list);
        }
    }
    */


    private class GetJson extends AsyncTask<Void, Void, Direcoes> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MapsActivity.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected Direcoes doInBackground(Void... params) {
            Utils util = new Utils();
            return util.getInformacao("http://maps.googleapis.com/maps/api/directions/json?origin=-23.581485,-46.638807&destination=-23.581485,-46.638507&sensor=false");
        }

        @Override
        protected void onPostExecute(Direcoes direcoes){

            lugares.add(direcoes.getNomeLocal());
            listaPrincipal.setAdapter(arrayAdapter);

            load.dismiss();
        }
    }

}
