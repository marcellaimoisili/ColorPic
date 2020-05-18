package com.cs1998.colorpic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import net.openid.appauth.AuthorizationServiceConfiguration;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.graphics.Color.RGBToHSV;

public class Main3Activity extends AppCompatActivity implements CustomAdapter.AdapterOnClickHandler{
//    implements CustomAdapter.AdapterOnClickHandler
    int redColor;
    int greenColor;
    int blueColor;
    int RGB;
    String hexCode;
    Button shareButton;
    Button pickButton;
    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    AuthorizationServiceConfiguration serviceConfig;
    String id;
    String url;

    private ArrayList<PicModel> picList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        rv = findViewById(R.id.mainRV);
        shareButton = findViewById(R.id.shareButton);
        pickButton = findViewById(R.id.pickButton);
        redColor = 0;
        greenColor = 0;
        blueColor = 0;

//        serviceConfig = new AuthorizationServiceConfiguration(Uri.parse("https://idp.example.com/auth"), Uri.parse("https://idp.example.com/token"));
        id = "814907ba029197df75c5542a26e2aefe982da09459e3b2862207f41aa5cbbfc0";
        OkHttpClient client = new OkHttpClient();
//        HttpUrl.Builder urlBuilder = HttpUrl.parse("api.dribbble.com/v2/");
//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://dribbble.com/shots/popular/illustration?color=").newBuilder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.thecolorapi.com/scheme?hex=").newBuilder();

        Bundle b = getIntent().getExtras();
        if(b != null){
            redColor = b.getInt("color_r");
            greenColor = b.getInt("color_g");
            blueColor = b.getInt("color_b");
            hexCode = b.getString("hex");
            RGB = Color.rgb(redColor, greenColor, blueColor);
            url = urlBuilder.build().toString() + hexCode.substring(1,hexCode.length()) + "&mode=analogic&count=5";

//            Toast toast = Toast.makeText(this, Integer.toString(redColor) + Integer.toString(greenColor) + Integer.toString(blueColor), Toast.LENGTH_SHORT);
//            toast.show();

        }

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected Code:" + response);
                }else{
//                    Log.d("test", response.body().string());
                    Moshi moshi = new Moshi.Builder().build();

//                    JsonAdapter<Name> jsonAdapterName = moshi.adapter(Name.class);
//                    JsonAdapter<Image> jsonAdapterImage = moshi.adapter(Image.class);
                    JsonAdapter<Colors> jsonAdapter = moshi.adapter(Colors.class);

//                    Name name = jsonAdapterName.fromJson(response.body().string());
//                    Log.d("test", name.toString());

//                    Image image = jsonAdapterImage.fromJson(response.body().string());
//                    Log.d("test", image.toString());

                    Colors colors = jsonAdapter.fromJson(response.body().string());
                    Log.d("test", colors.toString());

//
                    final ArrayList<PicModel> body = (ArrayList) colors.colors;
                    Log.d("test", body.toString());

                }
            }
        });


        rv.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new CustomAdapter(picList, this);
        rv.setAdapter(mAdapter);

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                intent.putExtra("color_r", redColor);
                intent.putExtra("color_g", greenColor);
                intent.putExtra("color_b", blueColor);
                startActivity(intent);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Check out this color: " +  hexCode);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(int position) {
        Toast toast = Toast.makeText(this,"picList.get(position).named", Toast.LENGTH_SHORT);
        toast.show();
    }
}