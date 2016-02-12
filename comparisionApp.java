package com.example.sbhal.comparisionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ComparisionApp extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int countx =0, county =0;

    EditText resultField;
    SharedPreferences prefs;

    private void updateResultField(){
        String compSymbol;
        if ( countx > county)
            compSymbol = ">";
        else if ( countx < county)
            compSymbol = "<";
        else
            compSymbol = "=";

        resultField.setText("X " + compSymbol + " Y");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        countx = prefs.getInt("X", 0);
        county = prefs.getInt("Y", 0);

        //Layout
        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.parseColor("#3E50B4"));

        //nameField input
        final EditText nameField = new EditText(this);
        nameField.setId(View.generateViewId());
        RelativeLayout.LayoutParams nameFieldDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        nameFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        nameFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        nameFieldDetails.setMargins(0, 0, 0, 0);
        nameField.setBackgroundColor(Color.parseColor("#FF3F80"));
        nameField.setGravity(Gravity.CENTER);
        nameField.setText("Siddharth Bhal");
        nameField.setTextColor(Color.WHITE);


        //xField input
        final EditText xField = new EditText(this);
        xField.setId(View.generateViewId());
        RelativeLayout.LayoutParams xFieldDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        xFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        xFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        xFieldDetails.addRule(RelativeLayout.BELOW, nameField.getId());
        xFieldDetails.setMargins(0, 20, 0, 50);
        xField.setBackgroundColor(Color.parseColor("#FF3F80"));
        xField.setGravity(Gravity.CENTER);
        xField.setTextColor(Color.WHITE);
        xField.setText("X = " + String.valueOf(countx));

        //yField input
        final EditText yField = new EditText(this);
        yField.setId(View.generateViewId());
        RelativeLayout.LayoutParams yFieldDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        yFieldDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        yFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        yFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        yField.setBackgroundColor(Color.parseColor("#FF3F80"));
        yField.setGravity(Gravity.CENTER);
        yField.setTextColor(Color.WHITE);
        yField.setText("Y = " + String.valueOf(county));
        yFieldDetails.setMargins(0, 100, 0, 50);

        //Button
        Button xUpButton = new Button(this);
        xUpButton.setText("+");
        xUpButton.setId(View.generateViewId());
        RelativeLayout.LayoutParams xUpButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        xUpButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        xUpButtonDetails.addRule(RelativeLayout.BELOW, xField.getId());
        xUpButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        countx = countx + 1;
                        xField.setText("X = " + String.valueOf(countx));
                        updateResultField();
                    }
                }
        );


        Button xDownButton = new Button(this);
        xDownButton.setText("-");
        xDownButton.setId(View.generateViewId());
        RelativeLayout.LayoutParams xDownButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        xDownButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        xDownButtonDetails.addRule(RelativeLayout.BELOW, xField.getId());
        xDownButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        countx = countx - 1;
                        xField.setText(String.valueOf("X = " + String.valueOf(countx)));
                        updateResultField();
                    }
                }
        );


        Button yUpButton = new Button(this);
        yUpButton.setText("+");
        yUpButton.setId(View.generateViewId());
        RelativeLayout.LayoutParams yUpButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        yUpButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        yUpButtonDetails.addRule(RelativeLayout.BELOW, yField.getId());
        yUpButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        county = county + 1;
                        yField.setText("Y = " + String.valueOf(county));
                        updateResultField();
                    }
                }
        );


        Button yDownButton = new Button(this);
        yDownButton.setText("-");
        yDownButton.setId(View.generateViewId());
        RelativeLayout.LayoutParams yDownButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        yDownButtonDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        yDownButtonDetails.addRule(RelativeLayout.BELOW, yField.getId());
        yDownButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        county = county - 1;
                        yField.setText("Y = " + String.valueOf(county));
                        updateResultField();
                    }
                }
        );



        resultField = new EditText(this);
        resultField.setId(View.generateViewId());
        resultField.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams resultFieldDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        resultFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        resultFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        resultFieldDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        resultFieldDetails.setMargins(0, 0, 0, 50);
        resultField.setBackgroundColor(Color.parseColor("#FF3F80"));
        resultField.setTextColor(Color.WHITE);
        updateResultField();

        myLayout.addView(xUpButton, xUpButtonDetails);
        myLayout.addView(xDownButton, xDownButtonDetails);
        myLayout.addView(yUpButton, yUpButtonDetails);
        myLayout.addView(yDownButton, yDownButtonDetails);
        myLayout.addView(nameField, nameFieldDetails);
        myLayout.addView(xField, xFieldDetails);
        myLayout.addView(yField, yFieldDetails);
        myLayout.addView(resultField, resultFieldDetails);


        setContentView(myLayout);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comparision_app, menu);
        return true;
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ComparisionApp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sbhal.comparisionapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("X", countx);
        editor.putInt("Y", county);
        editor.commit();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ComparisionApp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sbhal.comparisionapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
