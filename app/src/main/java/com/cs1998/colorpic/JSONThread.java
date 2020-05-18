//package com.cs1998.colorpic;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import okhttp3.Request;
//
//public class JSONThread extends AsyncTask<String, Void, JSONObject> {
//    private DownloadCallback<String> callback;
//    public JSONThread(DownloadCallback<String> callback){
//        this.callback = callback;
//    }
//    @Override
//    protected JSONObject doInBackground(String... params) {
//        String inputString = params[0];
//        JSONObject retrievedData = new JSONObject();
//        try {
//            Request request = new Request.Builder().url(url).build();
//
////            InputStream in = new java.net.URL().openStream(inputString);
////            BufferedReader webData = new BufferedReader(new InputStreamReader(in));
////            String dataString = webData.readLine();
////            retrievedData = new JSONObject(dataString);
//            Moshi moshi = new Moshi.Builder().build();
//
////                    JsonAdapter<Name> jsonAdapterName = moshi.adapter(Name.class);
////                    JsonAdapter<Image> jsonAdapterImage = moshi.adapter(Image.class);
//            JsonAdapter<Colors> jsonAdapter = moshi.adapter(Colors.class);
//
////                    Name name = jsonAdapterName.fromJson(response.body().string());
////                    Log.d("test", name.toString());
//
////                    Image image = jsonAdapterImage.fromJson(response.body().string());
////                    Log.d("test", image.toString());
//
//            Colors colors = jsonAdapter.fromJson(response.body().string());
//            Log.d("test", colors.toString());
//
////
//            final ArrayList<PicModel> body = (ArrayList) colors.colors;
//            Log.d("test", body.toString());
//            picList = (ArrayList<PicModel>) body.clone();
//
//        } catch (Exception e) {
//            Log.d("JSONThread", "ERROR: Networking Failed");
//        }
//        return retrievedData;
//    }
//    @Override
//    protected void onPostExecute(JSONObject result) {
//        Log.d("JSONThread", result.toString());
//    }
//}