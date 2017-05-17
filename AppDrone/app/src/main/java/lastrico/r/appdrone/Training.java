package lastrico.r.appdrone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import lastrico.r.appdrone.ImageSaver;

import static android.R.attr.bitmap;


public class Training  extends Activity  implements NavigationView.OnNavigationItemSelectedListener {



    ImageView myImageView = null;
    //TextView myTextView = null;
    Button bttClickMe = null;
    Button bttClickMe2 = null;
    Button bttClickMe4 = null;
    Bitmap myBitmap = null;
    int REQUEST_IMAGE_CAPTURE = 1;
    ProgressDialog progress;
    SeekBar mySeekBar;
    ManipulateImage _myCV;
    Bitmap actualPhoto=null;
    Uri imageUri=null;
    ContentValues values;
    //int manipulationSelected = 1;

    //Function to launch camera application
   /* private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }*/

    private void dispatchTakePictureIntent() {
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {

                myBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                myImageView.setImageBitmap(myBitmap);

                //convertImage = new ConvertImage(progress, img, bitmap);
                //convertImage.execute("");
                //img.setImageBitmap(newBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //imgByte = getBytesFromBitmap(newBitmap);
            //new PhotoSaver(getApplicationContext(), bitmap).record();



        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();


            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText imageName=(EditText)findViewById(R.id.imageName);
                String nome=imageName.getText().toString();
                myImageView.buildDrawingCache();
                Bitmap bmap = myImageView.getDrawingCache();
                ImageSaver.saveToInternalStorage(bmap, nome);
                myImageView.destroyDrawingCache();
                Toast.makeText(Training.this, "Immagine salvata!",
                        Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText imageName=(EditText)findViewById(R.id.imageName);
                String nome=imageName.getText().toString();
                myBitmap=ImageSaver.LoadImageFromStorage(nome);
                myImageView.setImageBitmap(myBitmap);

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_training);
        navigationView.setNavigationItemSelectedListener(this);


        myImageView = (ImageView) findViewById(R.id.etImage);
        bttClickMe2 = (Button) findViewById(R.id.bttClickMe2);
        bttClickMe4 = (Button) findViewById(R.id.bttClickMe4);
        progress = new ProgressDialog(this);
        mySeekBar = (SeekBar) findViewById(R.id.seekBar);

        //initialize SeekBar correctly
        mySeekBar.setProgress(128); //128 ad default value
        mySeekBar.setMax(255);

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
        //reset button
        bttClickMe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myImageView.setImageBitmap(myBitmap);
            }
        });

        //Binary with Seek bar threshold manipulation
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //visualize number selected with toast
                Toast.makeText(Training.this,"Soglia: "+ progressChanged, Toast.LENGTH_SHORT).show();

                //execute manipulation
                _myCV = new ManipulateImage(progress, myImageView, myBitmap, 2, progressChanged);
                _myCV.execute("");

            }
        });
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            myBitmap = imageBitmap;
            myImageView.setImageBitmap(imageBitmap);
        }
    }*/
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
        } else if (id == R.id.recognition) {
            Intent manda = new Intent( getApplicationContext(),Recognition.class);

            startActivity(manda);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}