package lastrico.r.aMyBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btButton=null;
    private EditText tvText=null;

    private BroadcastReceiver myBR=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btButton=(Button) findViewById(R.id.btButton);
        tvText=(EditText) findViewById(R.id.etText);
        myBR=new MyBroadcastReceiver();
        registerReceiver(myBR, new IntentFilter("lastrico.broadcastreceiver.MainActivity"));

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();



                String _s=tvText.getText().toString();
                i.putExtra("messaggio", _s);
                i.setAction("lastrico.broadcastreceiver.MainActivity");

                sendBroadcast(i);


            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBR);
    }
}
