package com.example.guk.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        URLConnector a = new URLConnector("http://163.180.117.131:7000/TestProject/join.jsp");

        a.start();

        try {
            a.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getResult());
        */
    }

    public void onImageBtnClicked_search(View view){
        Toast.makeText(getApplicationContext(), "버스 검색 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void onImageBtnClicked_arrive(View view){
        Toast.makeText(getApplicationContext(), "도착 예정 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ArriveAcitivity.class);
        startActivity(intent);
    }
    public void onImageBtnClicked_other(View view){
        Toast.makeText(getApplicationContext(), "그외 추가기능 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }
    public void onImageBtnClicked_news(View view){
        Toast.makeText(getApplicationContext(), "공지사항 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
