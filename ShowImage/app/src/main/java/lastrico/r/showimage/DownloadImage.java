package lastrico.r.showimage;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadImage extends AsyncTask<String, Void, Bitmap> { /* l'AsyncTask richiede tre tipi
uno è il tipo che si aspetta in input background (URL dell'immagine), l'ultimo è quello che restituisce*/

    private final String tag="DownloadImage";

    private ProgressDialog myPd=null;
    private String myUrl=null;
    private ImageView myIw=null;

    private DownloadEnded myDe;

    DownloadImage(ProgressDialog _pd, String _url, ImageView _iw, DownloadEnded _de)
    {
        myPd=_pd;
        myUrl=_url;
        myIw=_iw;
        myDe=_de;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        myPd.setIndeterminate(true); //set del progressbar
        myPd.setTitle("Please Wait");
        myPd.setMessage("Download in progress");
        myPd.show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {   //non può avere a che fare con l'interfaccia grafica (doinbackgroung)
        Bitmap bm=null;
        try {
            bm = DownloadImage(myUrl);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {   //prende in ingresso il terzo oggetto delle parentesi angolari
        super.onPostExecute(bitmap);

        myPd.dismiss();
        //myIw.setImageBitmap(bitmap);

        myDe.onDownloadFinished(bitmap);
    }

    private Bitmap DownloadImage(String _url) throws IOException
    {
        Bitmap bm = null;
        URL url=new URL(_url);

        //open url connection
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        urlConnection.connect();

        //open input stream channel
        InputStream is= urlConnection.getInputStream();
        BufferedInputStream bis= new BufferedInputStream(is);

        bm= BitmapFactory.decodeStream(is);


        bis.close();
        is.close();

        return bm;
    }
}