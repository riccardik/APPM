package lastrico.r.showimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button=null;
    EditText text=null;
    ImageView image=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button) findViewById(R.id.bttButton);
        text=(EditText)findViewById(R.id.etText);
        image=(ImageView)findViewById(R.id.ivImage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _s = text.getText().toString();


            }
        });


    }
}
