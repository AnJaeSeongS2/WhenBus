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
public class NewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        TextView TxtDate = (TextView) findViewById(R.id.Text_news_date);
        TextView TxtContent = (TextView) findViewById(R.id.Text_news_content);

        URLConnector notice = new URLConnector("http://163.180.117.131:7000/TestProject/notice.jsp");

        notice.start();

        //String[] NoticeName;
        //String[] Contents;
        //String[] UpdateDate;

        try {
            notice.join();

            String ngr = notice.getResult();

            JSONObject root = new JSONObject(ngr);
            JSONArray ja = root.getJSONArray("result");

            String[] Contents = new String[ja.length()];
            String[] UpdatedDate = new String[ja.length()];

            for(int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);

                 Contents[i] = jo.getString("Contents");
                UpdatedDate[i] = jo.getString("UpdatedDate");

                //System.out.println(NoticeName[i] + " " + Contents[i] + " " + UpdatedDate[i]);
            }


            TxtDate.setText(UpdatedDate[0]);
            TxtContent.setText(Contents[0]);

        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }

        //TxtDate.setText("???");
        //TxtContent.setText("내용없다");
    }

    public void onImageBtnClicked_news_return(View view){
        finish();
    }
}
