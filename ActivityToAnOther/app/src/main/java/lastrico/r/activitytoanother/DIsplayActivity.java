package lastrico.r.activitytoanother;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rikyg on 22/03/2017.
 */

public class DIsplayActivity extends Activity {


    TextView tvdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        TextView tvdisplay;
        Intent ricevi= getIntent();

        String cibosel=ricevi.getStringExtra("cibosel");
        tvdisplay=(TextView)findViewById(R.id.tvdisplay);

        tvdisplay.setText(cibosel);








    }
}
