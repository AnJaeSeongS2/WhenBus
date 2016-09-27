package com.example.guk.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hyo99 on 2015-12-17.
 */


public class OtherUrlActivity extends Activity {

    String BusName;
    String MapURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_url);
    }

    public void onBtnClicked_other_url_5500(View view) {
        BusName = "5500-1";
        GetUrl();

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.map.naver.com/bus/lane.nhn?busID=10071"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MapURL));
        startActivity(intent);
    }

    public void onBtnClicked_other_url_1112(View view) {
        BusName = "1112";
        GetUrl();

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.map.naver.com/bus/lane.nhn?busID=10052"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MapURL));
        startActivity(intent);
    }

    public void onBtnClicked_other_url_5100(View view) {
        BusName = "5100";
        GetUrl();

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.map.naver.com/bus/lane.nhn?busID=9564"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MapURL));
        startActivity(intent);
    }

    public void onBtnClicked_other_url_M5107(View view) {
        BusName = "M5107";
        GetUrl();

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.map.naver.com/bus/lane.nhn?busID=10501"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MapURL));
        startActivity(intent);
    }

    public void onBtnClicked_other_url_9(View view) {
        BusName = "9";
        GetUrl();

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.map.naver.com/bus/lane.nhn?busID=9598"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MapURL));
        startActivity(intent);
    }

    public void GetUrl() {

        System.out.println("startGetURL");
        URLConnector station = new URLConnector("http://163.180.117.131:7000/TestProject/station.jsp?BusName=" + BusName + "");

        station.start();

        try {
            station.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String json = station.getResult();
        try{
            JSONObject root = new JSONObject(json);
            JSONArray ja = root.getJSONArray("result");

            for( int i = 0 ; i < ja.length() ; i++){
                JSONObject jo = ja.getJSONObject(i);
                MapURL= jo.getString("NaverMapURL");

            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }


    }
}
