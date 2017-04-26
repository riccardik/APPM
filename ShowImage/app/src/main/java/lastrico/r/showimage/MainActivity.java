package lastrico.r.showimage;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements DownloadEnded{

    Button button=null;
    EditText text=null;
    ImageView iw =null;

    DownloadImage myImage=null;

    private ProgressDialog progressdialog=null;
    private DownloadEnded de=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button) findViewById(R.id.bttButton);
        text=(EditText)findViewById(R.id.etText);
        iw =(ImageView)findViewById(R.id.ivImage);

        new Thread(new Runnable() { //lanciare un nuovo thread con una funzione pesante
            @Override               // non funziona bene come l'asinc task, è più semplice
            public void run() {     //come sintassi
                VeryHighLoad();
            }
        });

        de=this; //l'oggetto si inizializza con la classe stessa


        progressdialog=new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _s = text.getText().toString();

                myImage = new DownloadImage(progressdialog, _s, iw, de);

                myImage.execute(new String[] {""});

            }
        });


    }

    @Override
    public void onDownloadFinished (Bitmap bm)
    {
        iw.setImageBitmap(bm);


    }

    private void VeryHighLoad()
    {

    }
}
