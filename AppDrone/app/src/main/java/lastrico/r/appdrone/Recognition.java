package lastrico.r.appdrone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Recognition  extends Activity implements NavigationView.OnNavigationItemSelectedListener {



    ImageView myImageView = null;
    //TextView myTextView = null;
    Button bttClickMe = null;
    Button bttClickMe2 = null;

    Bitmap myBitmap = null;
    int REQUEST_IMAGE_CAPTURE = 1;
    ProgressDialog progress;

    ManipulateImage _myCV;
    //int manipulationSelected = 1;

    //Function to launch camera application
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recognition_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_recognition);
        navigationView.setNavigationItemSelectedListener(this);


        myImageView = (ImageView) findViewById(R.id.etImage);
        bttClickMe2 = (Button) findViewById(R.id.bttClickMe2);
        progress = new ProgressDialog(this);

        //initialize ImageView content
        myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        myImageView.setImageBitmap(myBitmap); //set ImageView content

        //------------ BUTTONS & SEEKBAR LISTENERS ------------

        //gray scale button
        bttClickMe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _myCV = new ManipulateImage(progress, myImageView, myBitmap, 1);
                _myCV.execute("");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            myBitmap = imageBitmap;
            myImageView.setImageBitmap(imageBitmap);
        }
    }
    //Function for saving state of activity (for example when going landscape)
    @Override
    public void onSaveInstanceState(Bundle toSave) {
        super.onSaveInstanceState(toSave);
        toSave.putParcelable("bitmap", myBitmap);
    }

    //Function for reloading state of activity
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myBitmap = savedInstanceState.getParcelable("bitmap");
        myImageView.setImageBitmap(myBitmap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.training) {
            Intent manda = new Intent( getApplicationContext(),Training.class);

            startActivity(manda);




        } else if (id == R.id.recognition) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}