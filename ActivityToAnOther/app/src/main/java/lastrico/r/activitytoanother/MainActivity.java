package lastrico.r.activitytoanother;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btOrdina;
    RadioButton rb1, rb2, rb3;
    TextView tv1;
    String _cibosel;
    RadioGroup rg1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btOrdina = (Button) findViewById(R.id.btOrdina);
        rb1=(RadioButton)findViewById(R.id.rdb1);
        rb2=(RadioButton)findViewById(R.id.rdb2);
        rb3=(RadioButton)findViewById(R.id.rdb3);
        tv1=(TextView) findViewById(R.id.tv1);
        rg1=(RadioGroup) findViewById(R.id.rg1);

        /*rb1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                _cibosel=rb1.getText().toString();


            }
            });
        rb2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                _cibosel=rb2.getText().toString();


            }
        });
        rb3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                _cibosel=rb3.getText().toString();


            }
        });*/




        btOrdina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int _idrb=rg1.getCheckedRadioButtonId();
                RadioButton rsel=(RadioButton)findViewById(_idrb);
                _cibosel=rsel.getText().toString();

                tv1.setText(_cibosel);




                Intent manda=new Intent(getApplicationContext(),DIsplayActivity.class );
                manda.putExtra("cibosel", _cibosel);
                startActivity(manda);


            }
            });









    }
}
