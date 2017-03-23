package lastrico.r.esempiowidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String TAG="MainActivity";
    EditText etNome, etCognome;
    Button btCliccami;
    TextView tvStampa;
    Spinner spnResidenza;

    String _residenza="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome=(EditText) findViewById(R.id.etNome);
        etCognome=(EditText)findViewById(R.id.etCognome);

        tvStampa=(TextView) findViewById(R.id.tvStampa);
        btCliccami=(Button)findViewById(R.id.btCliccami);
        spnResidenza=(Spinner)findViewById(R.id.spnResidenza);

        spnResidenza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posizione, long l) {

                _residenza=spnResidenza.getItemAtPosition(posizione).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btCliccami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //la funzione onclick è richiamabile solo dal programma
                Log.i(TAG, "bottone premuto");
                String _nome=etNome.getText().toString();
                String _cognome=etCognome.getText().toString();

                tvStampa.setText("Nome: "+_nome +"\nCognome: "+_cognome+"\nResidenza: "+_residenza);
            }
        }); //mi serve un oggetto listener

    }

}
