package lastrico.r.showimage;

import android.graphics.Bitmap;
        import android.os.AsyncTask;

/**
 * Created by feder on 05/04/2017.
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> { /* l'AsyncTask richiede tre tipi
uno è il tipo che si aspeta in input Backgroung (URL dell'immagine), l'ultimo è quello che restituisce*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {   //prende in ingresso il III oggetto delle parentesi angolari
        super.onPostExecute(bitmap);
    }
}