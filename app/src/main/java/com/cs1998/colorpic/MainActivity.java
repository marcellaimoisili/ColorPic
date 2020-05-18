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
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    View colorSquare;
    SeekBar redSeekBar;
    SeekBar greenSeekBar;
    SeekBar blueSeekBar;
    TextView hexCode;
    TextView redValue;
    TextView greenValue;
    TextView blueValue;
    Button hsvButton;
    Button locButton;
    Button searchButton;
    String colorString;
    int redColor;
    int greenColor;
    int blueColor;
    int RGB;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorSquare = findViewById(R.id.colorSquare);
        hexCode = findViewById(R.id.hexCode);
        redSeekBar = findViewById(R.id.seekBar1);
        greenSeekBar = findViewById(R.id.seekBar2);
        blueSeekBar = findViewById(R.id.seekBar3);
        hexCode = findViewById(R.id.hexCode);
        redValue = findViewById(R.id.redValue);
        greenValue = findViewById(R.id.greenValue);
        blueValue = findViewById(R.id.blueValue);
        hsvButton = findViewById(R.id.button1);
        locButton = findViewById(R.id.button2);
        searchButton = findViewById(R.id.button3);

        redColor = 0;
        greenColor = 0;
        blueColor = 0;

        Bundle b = getIntent().getExtras();
        if(b != null){
            colorSquare.setBackgroundColor(Color.rgb(b.getInt("color_r"), b.getInt("color_g"), b.getInt("color_b")));
            hexCode.setText(String.format("#%02X%02X%02X", b.getInt("color_r"), b.getInt("color_g"), b.getInt("color_b")));
            redColor = b.getInt("color_r");
            greenColor = b.getInt("color_g");
            blueColor = b.getInt("color_b");
        }

        redSeekBar.setProgress(redColor);
        greenSeekBar.setProgress(greenColor);
        blueSeekBar.setProgress(blueColor);

        colorSquare.setBackgroundColor(Color.rgb(redColor,greenColor,blueColor));
        hexCode.setText(String.format("#%02X%02X%02X",redColor,greenColor,blueColor));

        redValue.setText("Red: "+ redColor);
        greenValue.setText("Green: "+ greenColor);
        blueValue.setText("Blue: "+ blueColor);

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorSquare.setBackgroundColor(Color.rgb(progress,greenSeekBar.getProgress(),blueSeekBar.getProgress()));
                hexCode.setText(String.format("#%02X%02X%02X", progress,greenSeekBar.getProgress(),blueSeekBar.getProgress()));
                redValue.setText("Red: "+ progress);
                redColor = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorSquare.setBackgroundColor(Color.rgb(redSeekBar.getProgress(),progress,blueSeekBar.getProgress()));
                hexCode.setText(String.format("#%02X%02X%02X", redSeekBar.getProgress(),progress,blueSeekBar.getProgress()));
                greenValue.setText("Green: "+ progress);
                greenColor = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                colorSquare.setBackgroundColor(Color.rgb(redSeekBar.getProgress(),greenSeekBar.getProgress(),progress));
                hexCode.setText(String.format("#%02X%02X%02X", redSeekBar.getProgress(),greenSeekBar.getProgress(),progress));
                blueValue.setText("Blue: "+ progress);
                blueColor = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        hsvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("color_r", redColor);
                intent.putExtra("color_g", greenColor);
                intent.putExtra("color_b", blueColor);
                startActivity(intent);
            }
        });

        locButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);}

                mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

                Task<Location> task = mFusedLocationProviderClient.getLastLocation();
                task.addOnSuccessListener((new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            colorString = "#" + String.format("%06d", Math.round((location.getLatitude() % 1) * 100000));
                            RGB = Color.parseColor(colorString);
                            colorSquare.setBackgroundColor(RGB);
                            redColor = Color.red(RGB);
                            greenColor = Color.green(RGB);
                            blueColor = Color.blue(RGB);
                            colorSquare.setBackgroundColor(RGB);
                            redSeekBar.setProgress(redColor);
                            greenSeekBar.setProgress(greenColor);
                            blueSeekBar.setProgress(blueColor);
                        } else {
                            Toast.makeText(MainActivity.this, "This failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }));
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("color_r", redColor);
                intent.putExtra("color_g", greenColor);
                intent.putExtra("color_b", blueColor);
                intent.putExtra("hex", hexCode.getText());
                startActivity(intent);
            }
        });

    }
}
