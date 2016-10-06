package andrevictor.com.trademoblie3;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//AppCompatActivity,

public class MainActivity extends FragmentActivity {

    //Componentes
    Button botaoPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoPrincipal = (Button) findViewById(R.id.btnPrincipal);
    }

    public void AcaoRotas(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
