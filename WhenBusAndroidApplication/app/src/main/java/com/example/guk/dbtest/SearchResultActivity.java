package com.example.guk.dbtest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hyo99 on 2015-12-17.
 */
public class SearchResultActivity extends Activity {

    TableLayout tl;

    String BusName;
    String StartTime;
    String IsWeekend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        BusName = intent.getExtras().getString("tube1");
        StartTime = intent.getExtras().getString("tube2");
        IsWeekend = intent.getExtras().getString("tube3");

        tl = (TableLayout) findViewById(R.id.Table_search);


        GetSearch();

    }

    public void GetSearch() {

        URLConnector noticeTest =
                new URLConnector("http://163.180.117.131:7000/TestProject/search.jsp?BusName="+BusName+"&StartTime="+StartTime+"&IsWeekend="+IsWeekend);
        noticeTest.start();

        try {
            noticeTest.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        String json = noticeTest.getResult();
        try{

            JSONObject root = new JSONObject(json);
            JSONArray ja = root.getJSONArray("result");


            String[] StartTime = new String[ja.length()];
            String[] BusName  = new String[ja.length()];
            String[] CarPlateName  = new String[ja.length()];
            String[] IsCrossRoad = new String[ja.length()];
            String[] Name = new String[ja.length()];
            String[] DriverID = new String[ja.length()];
            String[] VoteValue = new String[ja.length()];


            for( int i = 0 ; i < ja.length() ; i++){
                JSONObject jo = ja.getJSONObject(i);

                StartTime[i] = jo.getString("StartTime");
                BusName[i] = jo.getString("BusName");
                CarPlateName[i] = jo.getString("CarPlateName");
                IsCrossRoad[i] = jo.getString("IsCrossRoad");
                Name[i] = jo.getString("Name");
                DriverID[i] = jo.getString("DriverID");
                VoteValue[i] = jo.getString("VoteValue");
            }

            for( int i = 0 ; i < ja.length() ; i++) {
                TableRow tr = new TableRow(this);
                tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                tr.setBackgroundColor(0xFFFFFFFF);
                //tr.setOrientation(RelativeLayout.CENTER_HORIZONTAL);

                TextView[] a;
                a = new TextView[6];
                for (int j=0; j<6; j++) {
                    a[j] = new TextView(this);
                    a[j].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                    a[j].setTextSize(10);
                    a[j].setGravity(Gravity.CENTER);
                    a[j].setBackgroundColor(0xFFFFFFFF);
                    //a[j].setText("hello");
                }

                a[0].setText(StartTime[i]);
                a[1].setText(BusName[i]);
                a[2].setText(CarPlateName[i]);
                a[3].setText(Name[i]);
                a[4].setText(DriverID[i]);
                a[5].setText(VoteValue[i]);

                tr.addView(a[0]);
                tr.addView(a[1]);
                tr.addView(a[2]);
                tr.addView(a[3]);
                tr.addView(a[4]);
                tr.addView(a[5]);

                tl.addView(tr);
            }

        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void onImageBtnClicked_search_return(View view){
        finish();
    }
}
