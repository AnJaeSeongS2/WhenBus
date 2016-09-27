package com.example.guk.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.w3c.dom.Text;

/**
 * Created by hyo99 on 2015-12-17.
 */
public class OtherStarActivity extends Activity {

    Spinner spinner1;

    TextView[] TxtName;
    TextView[] TxtNumber;
    TextView[] TxtGrade;
    TextView TxtAvg;

    EditText ETxtName;
    EditText ETxtNumber;
    Spinner SpinnerValue;

    String DriverID;
    String VoteValue; //= "1.0점";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_star);
        TxtName = new TextView[5];
        TxtNumber = new TextView[5];
        TxtGrade = new TextView[5];

        TxtName[0] = (TextView) findViewById(R.id.text_other_1_name);
        TxtNumber[0] = (TextView) findViewById(R.id.text_other_1_number);
        TxtGrade[0] = (TextView) findViewById(R.id.text_other_1_grade);
        TxtName[1] = (TextView) findViewById(R.id.text_other_2_name);
        TxtNumber[1] = (TextView) findViewById(R.id.text_other_2_number);
        TxtGrade[1] = (TextView) findViewById(R.id.text_other_2_grade);
        TxtName[2] = (TextView) findViewById(R.id.text_other_3_name);
        TxtNumber[2] = (TextView) findViewById(R.id.text_other_3_number);
        TxtGrade[2] = (TextView) findViewById(R.id.text_other_3_grade);
        TxtName[3] = (TextView) findViewById(R.id.text_other_4_name);
        TxtNumber[3] = (TextView) findViewById(R.id.text_other_4_number);
        TxtGrade[3] = (TextView) findViewById(R.id.text_other_4_grade);
        TxtName[4] = (TextView) findViewById(R.id.text_other_5_name);
        TxtNumber[4] = (TextView) findViewById(R.id.text_other_5_number);
        TxtGrade[4] = (TextView) findViewById(R.id.text_other_5_grade);
        TxtAvg = (TextView) findViewById(R.id.text_other_avg);

        ETxtName = (EditText) findViewById(R.id.EditTxt_Name);
        ETxtNumber = (EditText) findViewById(R.id.EditTxt_Number);
        SpinnerValue = (Spinner) findViewById(R.id.Spinner_star);

        /*
        //ETxtName.getText.toString();
        if ( editText.getText.toString().length() == 0 ) {
        //공백일 때 처리할 내용
        } else {
        //공백이 아닐 때 처리할 내용
        }
        */

        //text1.setText("남아중");
        //TextView text1 = (TextView) findViewById(R.id.text111);

        GetRank();

        spinner1 = (Spinner) findViewById(R.id.Spinner_star);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this, R.array.grade, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        //spinner1.getSelectedItem().toString();

    }

    public void onBtnClicked_Other_Star_Enter(View view){
        Toast.makeText(getApplicationContext(), "완료되었습니다.", Toast.LENGTH_SHORT).show();
        SendData();
    }

    public void GetRank() {
        URLConnector rank = new URLConnector("http://163.180.117.131:7000/TestProject/rank.jsp");
        URLConnector grade = new URLConnector("http://163.180.117.131:7000/TestProject/grade.jsp");

        rank.start();

        try {
            rank.join();

            String sa = rank.getResult();

            JSONObject root = new JSONObject(sa);
            JSONArray ja = root.getJSONArray("result");

            String[] DriverIDArray = new String[ja.length()];
            String[] NameArray = new String[ja.length()];
            String[] VoteValueArray = new String[ja.length()];

           // for(int i = 0; i < ja.length(); i++) {
            for(int i = 0; i < 5; i++) {
                JSONObject jo = ja.getJSONObject(i);

                DriverIDArray[i] = jo.getString("DriverID");
                NameArray[i] = jo.getString("Name");
                VoteValueArray[i] = jo.getString("VoteValue");
            }

            for(int i=0; i<5; i++) {
                TxtName[i].setText(NameArray[i]);
                TxtNumber[i].setText(DriverIDArray[i]);
                TxtGrade[i].setText(VoteValueArray[i]);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        grade.start();
        try {
            grade.join();

            String sa = grade.getResult();

            JSONObject root = new JSONObject(sa);
            JSONArray ja = root.getJSONArray("result");

            String[] AvgVoteValue = new String[ja.length()];

            for(int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);

                AvgVoteValue[i] = jo.getString("AvgVoteValue");
            }

            TxtAvg.setText(AvgVoteValue[0]);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void SendData() {

        if(spinner1.getSelectedItem().toString().equals("1.0점")) {
            VoteValue = "1";
        }
        else if(spinner1.getSelectedItem().toString().equals("2.0점")) {
            VoteValue = "2";
        }
        else if(spinner1.getSelectedItem().toString().equals("3.0점")) {
            VoteValue = "3";
        }
        else if(spinner1.getSelectedItem().toString().equals("4.0점")) {
            VoteValue = "4";
        }
        else if(spinner1.getSelectedItem().toString().equals("5.0점")) {
            VoteValue = "5";
        }

        DriverID = ETxtNumber.getText().toString();
        //VoteValue = spinner1.getSelectedItem().toString();

        URLConnector vote = new URLConnector("http://163.180.117.131:7000/TestProject/vote.jsp?DriverID=" + DriverID + "&VoteValue=" + VoteValue);

        vote.start();

        try {
            vote.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
