package com.cs1998.colorpic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.graphics.Color.RGBToHSV;

public class Main2Activity extends AppCompatActivity {
    View colorSquare;
    SeekBar hueSeekBar;
    SeekBar satSeekBar;
    SeekBar valueSeekBar;
    TextView hexCode;
    TextView hueValue;
    TextView satValue;
    TextView valValue;
    Button rgbButton;
    Button locButton;
    Button searchButton;
    String colorString;
    int redColor;
    int greenColor;
    int blueColor;
    int RGB;
    int HSV;
    float[] currentHSV = new float[3];
    boolean isUserActive;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        colorSquare = findViewById(R.id.colorSquare);
        hexCode = findViewById(R.id.hexCode2);
        hueSeekBar = findViewById(R.id.seekBar1);
        satSeekBar = findViewById(R.id.seekBar2);
        valueSeekBar = findViewById(R.id.seekBar3);
        hueValue = findViewById(R.id.hueValue);
        satValue = findViewById(R.id.satValue);
        valValue = findViewById(R.id.valValue);
        rgbButton = findViewById(R.id.button1);
        locButton = findViewById(R.id.button2);
        searchButton = findViewById(R.id.button3);

        redColor = 0;
        greenColor = 0;
        blueColor = 0;

        RGB = Color.rgb(redColor, greenColor, blueColor);
        RGBToHSV(redColor, greenColor, blueColor, currentHSV);
        HSV = Color.HSVToColor(currentHSV);

        Bundle b = getIntent().getExtras();
        if(b != null){
            redColor = b.getInt("color_r");
            greenColor = b.getInt("color_g");
            blueColor = b.getInt("color_b");

            RGB = Color.rgb(redColor, greenColor, blueColor);
            RGBToHSV(redColor,  greenColor, blueColor, currentHSV);
            HSV = Color.HSVToColor(currentHSV);
        }

        colorSquare.setBackgroundColor(HSV);
        hexCode.setText(String.format("#%02X%02X%02X",redColor, greenColor, blueColor));

        hueSeekBar.setProgress((int)currentHSV[0]);
        satSeekBar.setProgress((int)(currentHSV[1] * 100));
        valueSeekBar.setProgress((int)(currentHSV[2]* 100));

        hueValue.setText("Hue: "+ (int)currentHSV[0]);
        satValue.setText("Sat: "+ (int)(currentHSV[1] * 100));
        valValue.setText("Value: "+ (int)(currentHSV[2]* 100));
        isUserActive = true;


        //HSV SLIDER HELP!
        hueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(isUserActive == true){
                    currentHSV[0] = progress;
                    currentHSV[1] = satSeekBar.getProgress()/100f;
                    currentHSV[2] = valueSeekBar.getProgress()/100f;
                    colorSquare.setBackgroundColor(Color.HSVToColor(currentHSV));
//                hexCode.setText(String.format("#%02X%02X%02X", progress, satSeekBar.getProgress(), valueSeekBar.getProgress()));
                    hexCode.setText(String.format("#%02X%02X%02X", Color.red(Color.HSVToColor(currentHSV)), Color.green(Color.HSVToColor(currentHSV)), Color.blue(Color.HSVToColor(currentHSV))));
                    hueValue.setText("Hue: "+ progress);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        satSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(isUserActive == true){
                    currentHSV[0] = hueSeekBar.getProgress();
                    currentHSV[1] = progress/100f;
                    currentHSV[2] = valueSeekBar.getProgress()/100f;
                    colorSquare.setBackgroundColor(Color.HSVToColor(currentHSV));
//                    hexCode.setText(String.format("#%02X%02X%02X", hueSeekBar.getProgress(),progress,valueSeekBar.getProgress()));
                    hexCode.setText(String.format("#%02X%02X%02X", Color.red(Color.HSVToColor(currentHSV)), Color.green(Color.HSVToColor(currentHSV)), Color.blue(Color.HSVToColor(currentHSV))));
                    satValue.setText("Sat: "+ progress);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        valueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(isUserActive == true){
                    currentHSV[0] = hueSeekBar.getProgress();
                    currentHSV[1] = satSeekBar.getProgress()/100f;
                    currentHSV[2] = progress/100f;
                    colorSquare.setBackgroundColor(Color.HSVToColor(currentHSV));
//                    hexCode.setText(String.format("#%02X%02X%02X", hueSeekBar.getProgress(),satSeekBar.getProgress(),progress));
                    hexCode.setText(String.format("#%02X%02X%02X", Color.red(Color.HSVToColor(currentHSV)), Color.green(Color.HSVToColor(currentHSV)), Color.blue(Color.HSVToColor(currentHSV))));
                    valValue.setText("Value: "+ progress);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        rgbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                HSV = Color.HSVToColor(currentHSV);
                redColor = Color.red(HSV);
                greenColor = Color.green(HSV);
                blueColor = Color.blue(HSV);
                intent.putExtra("color_r", redColor);
                intent.putExtra("color_g", greenColor);
                intent.putExtra("color_b", blueColor);
                startActivity(intent);
            }
        });


        locButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);}

                mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Main2Activity.this);

//                Intent intent = new Intent();
                Task<Location> task = mFusedLocationProviderClient.getLastLocation();
                task.addOnSuccessListener((new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            colorString = "#" + String.format("%06d", Math.round((location.getLatitude() % 1) * 100000));
                            Log.d("testing-2", colorString);
                            RGB = Color.parseColor(colorString);

                            redColor = Color.red(RGB);
                            greenColor = Color.green(RGB);
                            blueColor = Color.blue(RGB);
                            hexCode.setText(String.format("#%02X%02X%02X", redColor, greenColor, blueColor));

                            RGBToHSV(redColor,  greenColor, blueColor, currentHSV);
                            HSV = Color.HSVToColor(currentHSV);
                            colorSquare.setBackgroundColor(HSV);

                            isUserActive = false;
                            hueSeekBar.setProgress((int) currentHSV[0]);
                            satSeekBar.setProgress((int) (currentHSV[1] * 100));
                            valueSeekBar.setProgress((int) (currentHSV[2] * 100));

                            hueValue.setText("Hue: "+ (int)currentHSV[0]);
                            satValue.setText("Sat: "+ (int)(currentHSV[1] * 100));
                            valValue.setText("Value: "+ (int)(currentHSV[2]* 100));
                            isUserActive = true;

                        } else {
                            Toast.makeText(Main2Activity.this, "This failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }));
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                HSV = Color.HSVToColor(currentHSV);
                redColor = Color.red(HSV);
                greenColor = Color.green(HSV);
                blueColor = Color.blue(HSV);
                intent.putExtra("color_r", redColor);
                intent.putExtra("color_g", greenColor);
                intent.putExtra("color_b", blueColor);
                intent.putExtra("hex", hexCode.getText());
                startActivity(intent);
            }
        });


    }
}

