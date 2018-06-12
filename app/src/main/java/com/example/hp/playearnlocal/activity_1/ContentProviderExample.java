package com.example.hp.playearnlocal.activity_1;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.playearnlocal.R;

public class ContentProviderExample extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    Button loadContacts;
    TextView showContacts;
    private static final int PERMISSION_REQUEST_CONTACT = 444;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_example);

        loadContacts = (Button) findViewById(R.id.loadContacs);
        showContacts = (TextView) findViewById(R.id.showContacts);

        askForContactPermission();

        loadContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLoaderManager().initLoader(1,null,ContentProviderExample.this);

            }
        });



    }
    public void askForContactPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSION_REQUEST_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSION_REQUEST_CONTACT);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }else{
                //getContact();
            }
        }
        else{
            //getContact();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //getContact();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    //ToastMaster.showMessage(this,"No permission for contacts");
                    Toast.makeText(getApplicationContext(),"No Permisssionfor contatcs..",Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
private void getContact(){
    ContentResolver cr = getContentResolver();
    Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

    if(cursor!=null && cursor.getCount()>0){
        StringBuilder sb = new StringBuilder("");
        while(cursor.moveToNext()){
            sb.append(cursor.getString(0)+" , "+cursor.getString(1)+" , "+cursor.getString(2)+"\n");
        }
        showContacts.setText(sb.toString());
    }
    else{
        showContacts.setText("No Contacts in Device");
    }
}

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if(i ==1)
            return new CursorLoader(ContentProviderExample.this, ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        getContact();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
