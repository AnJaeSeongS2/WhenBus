package com.example.guk.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hyo99 on 2015-12-17.
 */
public class SearchActivity extends Activity {

    RadioGroup ratioGroup_bus;

    RadioButton ratioBtn_bus1;
    RadioButton ratioBtn_bus2;
    RadioButton ratioBtn_bus3;
    RadioButton ratioBtn_bus4;
    RadioButton ratioBtn_bus5;
    RadioButton ratioBtn_time1;
    RadioButton ratioBtn_time2;
    RadioButton ratioBtn_time3;
    RadioButton ratioBtn_time4;
    RadioButton ratioBtn_time5;
    RadioButton ratioBtn_time6;
    RadioButton ratioBtn_day1;
    RadioButton ratioBtn_day2;

    String BusName;
    String StartTime;
    String IsWeekend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ratioGroup_bus = (RadioGroup) findViewById(R.id.RadioGroup_BusNumber);

        ratioBtn_bus1 = (RadioButton) findViewById(R.id.RadioButton_BusNumber1);
        ratioBtn_bus2 = (RadioButton) findViewById(R.id.RadioButton_BusNumber2);
        ratioBtn_bus3 = (RadioButton) findViewById(R.id.RadioButton_BusNumber3);
        ratioBtn_bus4 = (RadioButton) findViewById(R.id.RadioButton_BusNumber4);
        ratioBtn_bus5 = (RadioButton) findViewById(R.id.RadioButton_BusNumber5);
        ratioBtn_time1 = (RadioButton) findViewById(R.id.RadioButton_Time1);
        ratioBtn_time2 = (RadioButton) findViewById(R.id.RadioButton_Time2);
        ratioBtn_time3 = (RadioButton) findViewById(R.id.RadioButton_Time3);
        ratioBtn_time4 = (RadioButton) findViewById(R.id.RadioButton_Time4);
        ratioBtn_time5 = (RadioButton) findViewById(R.id.RadioButton_Time5);
        ratioBtn_time6 = (RadioButton) findViewById(R.id.RadioButton_Time6);
        ratioBtn_day1 = (RadioButton) findViewById(R.id.RadioButton_Day1);
        ratioBtn_day2 = (RadioButton) findViewById(R.id.RadioButton_Day2);

        ratioBtn_bus1.setChecked(true);
        ratioBtn_time1.setChecked(true);
        ratioBtn_day1.setChecked(true);
    /*
        ratioBtn_bus1.setOnClickListener(optionOnClickListener);
        ratioBtn_bus2.setOnClickListener(optionOnClickListener);
        ratioBtn_bus3.setOnClickListener(optionOnClickListener);
        ratioBtn_bus4.setOnClickListener(optionOnClickListener);
        ratioBtn_bus5.setOnClickListener(optionOnClickListener);
        ratioBtn_bus1.setChecked(true);
    */
        /*
        ratioBtn_bus1.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            int id = ratioGroup_bus.getCheckedRadioButtonId();
            //getCheckedRadioButtonId() 의 리턴값은 선택된 RadioButton 의 id 값.
            RadioButton rb = (RadioButton) findViewById(id);
            //tv.setText("결과: " + rb.getText().toString());
        } // end onClick()

    });  // end Listener
        */

    }

    public void onBtnClicked_Search_Enter(View view){
        Toast.makeText(getApplicationContext(), "검색 중...", Toast.LENGTH_SHORT).show();

        //String BusName = "5100";
        //String StartTime = "04:00";
        //String IsWeekend = "T";

        if(ratioBtn_bus1.isChecked()) {
            BusName = "5500-1";
        }
        else if(ratioBtn_bus2.isChecked()){
            BusName = "1112";
        }
        else if(ratioBtn_bus3.isChecked()){
            BusName = "5100";
        }
        else if(ratioBtn_bus4.isChecked()){
            BusName = "M5107";
        }
        else if(ratioBtn_bus5.isChecked()){
            BusName = "9";
        }

        if(ratioBtn_time1.isChecked()){
            StartTime = "04:00";
        }
        else if(ratioBtn_time2.isChecked()){
            StartTime = "08:00";
        }
        else if(ratioBtn_time3.isChecked()){
            StartTime = "12:00";
        }
        else if(ratioBtn_time4.isChecked()){
            StartTime = "16:00";
        }
        else if(ratioBtn_time5.isChecked()){
            StartTime = "20:00";
        }
        else if(ratioBtn_time6.isChecked()){
            StartTime = "24:00";
        }

        if(ratioBtn_day1.isChecked()){
            IsWeekend = "F";
        }
        else if(ratioBtn_day2.isChecked()){
            IsWeekend = "T";
        }


        Intent intent = new Intent(this, SearchResultActivity.class);

        intent.putExtra("tube1", BusName);
        intent.putExtra("tube2", StartTime);
        intent.putExtra("tube3", IsWeekend);
        startActivity(intent);
    }

    RadioButton.OnClickListener optionOnClickListener
            = new RadioButton.OnClickListener() {

        public void onClick(View v) {
            Toast.makeText(
                    SearchActivity.this,
                    "Option 1 : " + ratioBtn_bus1.isChecked() + "\n"
                            + "Option 2 : " + ratioBtn_bus2.isChecked() + "\n"
                            + "Option 2 : " + ratioBtn_bus3.isChecked() + "\n"
                            + "Option 2 : " + ratioBtn_bus4.isChecked() + "\n"
                            + "Option 3 : " + ratioBtn_bus5.isChecked(),
                    Toast.LENGTH_LONG).show();

        }
    };

}

