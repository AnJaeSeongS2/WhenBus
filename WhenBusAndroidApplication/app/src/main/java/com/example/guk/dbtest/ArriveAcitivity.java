package com.example.guk.dbtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hyo99 on 2015-12-17.
 */
public class ArriveAcitivity extends Activity {

    TextView[] TxtTime;
    TextView[] Txtnum;
    TextView[] TxtCross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive);
        TxtTime = new TextView[10];
        Txtnum = new TextView[10];
        TxtCross = new TextView[10];

        TxtTime[0] = (TextView) findViewById(R.id.Text_arrive_time1);
        Txtnum[0] = (TextView) findViewById(R.id.Text_arrive_number1);
        TxtCross[0] = (TextView) findViewById(R.id.Text_arrive_cross1);
        TxtTime[1] = (TextView) findViewById(R.id.Text_arrive_time2);
        Txtnum[1] = (TextView) findViewById(R.id.Text_arrive_number2);
        TxtCross[1] = (TextView) findViewById(R.id.Text_arrive_cross2);
        TxtTime[2] = (TextView) findViewById(R.id.Text_arrive_time3);
        Txtnum[2] = (TextView) findViewById(R.id.Text_arrive_number3);
        TxtCross[2] = (TextView) findViewById(R.id.Text_arrive_cross3);
        TxtTime[3] = (TextView) findViewById(R.id.Text_arrive_time4);
        Txtnum[3] = (TextView) findViewById(R.id.Text_arrive_number4);
        TxtCross[3] = (TextView) findViewById(R.id.Text_arrive_cross4);
        TxtTime[4] = (TextView) findViewById(R.id.Text_arrive_time5);
        Txtnum[4] = (TextView) findViewById(R.id.Text_arrive_number5);
        TxtCross[4] = (TextView) findViewById(R.id.Text_arrive_cross5);
        TxtTime[5] = (TextView) findViewById(R.id.Text_arrive_time6);
        Txtnum[5] = (TextView) findViewById(R.id.Text_arrive_number6);
        TxtCross[5] = (TextView) findViewById(R.id.Text_arrive_cross6);
        TxtTime[6] = (TextView) findViewById(R.id.Text_arrive_time7);
        Txtnum[6] = (TextView) findViewById(R.id.Text_arrive_number7);
        TxtCross[6] = (TextView) findViewById(R.id.Text_arrive_cross7);
        TxtTime[7] = (TextView) findViewById(R.id.Text_arrive_time8);
        Txtnum[7] = (TextView) findViewById(R.id.Text_arrive_number8);
        TxtCross[7] = (TextView) findViewById(R.id.Text_arrive_cross8);
        TxtTime[8] = (TextView) findViewById(R.id.Text_arrive_time9);
        Txtnum[8] = (TextView) findViewById(R.id.Text_arrive_number9);
        TxtCross[8] = (TextView) findViewById(R.id.Text_arrive_cross9);
        TxtTime[9] = (TextView) findViewById(R.id.Text_arrive_time10);
        Txtnum[9] = (TextView) findViewById(R.id.Text_arrive_number10);
        TxtCross[9] = (TextView) findViewById(R.id.Text_arrive_cross10);

        URLConnector arrive =
                new URLConnector("http://163.180.117.131:7000/TestProject/arrive.jsp");
        arrive.start();
        try {
            arrive.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        String json = arrive.getResult();
        try{

            JSONObject root = new JSONObject(json);

            JSONArray ja = root.getJSONArray("result");


            String[] DueTime = new String[ja.length()];
            String[] StartTime = new String[ja.length()];
            String[] IsWeekend = new String[ja.length()];
            String[] BusName = new String[ja.length()];
            String[] IsCrossRoad = new String[ja.length()];


            for( int i = 0 ; i < ja.length() && i<10; i++){
                JSONObject jo = ja.getJSONObject(i);

                DueTime[i] = jo.getString("DueTime");
                StartTime[i] = jo.getString("StartTime");
                IsWeekend[i] = jo.getString("IsWeekend");
                BusName[i] = jo.getString("BusName");
                IsCrossRoad[i] = jo.getString("IsCrossRoad");
            }
            System.out.println("arrive activated.!");

            for( int i=0; i< 10; i++) {
                TxtTime[i].setText(DueTime[i]);
                Txtnum[i].setText(BusName[i]);
                TxtCross[i].setText(IsCrossRoad[i]);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void onImageBtnClicked_arrive_return(View view){
        finish();
    }
}
