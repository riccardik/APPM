package lastrico.r.aMyBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by rikyg on 05/04/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String _s=intent.getStringExtra("messaggio");

        Toast.makeText(context, "Messaggio: "+_s,Toast.LENGTH_LONG).show();




    }
}
