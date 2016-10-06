package andrevictor.com.trademoblie3;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        //mMap.getUiSettings().setAllGesturesEnabled(false);

        // Ponto A
        LatLng pontoA = new LatLng(-23.581485, -46.638807);
        mMap.addMarker(new MarkerOptions().position(pontoA).title("Ponto A"));
        // Ponto B
        LatLng pontoB = new LatLng(-23.581485,-46.638707);
        mMap.addMarker(new MarkerOptions().position(pontoB).title("Ponto B"));
        // Ponto C
        LatLng pontoC = new LatLng(-23.581485, -46.638607);
        mMap.addMarker(new MarkerOptions().position(pontoC).title("Ponto C"));
        // Ponto D
        LatLng pontoD = new LatLng(-23.581485,-46.638507);
        mMap.addMarker(new MarkerOptions().position(pontoD).title("Ponto D"));

        //Dar zoom no mapa
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pontoC,17));
    }
}
