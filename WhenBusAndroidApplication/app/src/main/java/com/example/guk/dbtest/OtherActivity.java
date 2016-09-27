package com.example.guk.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hyo99 on 2015-12-17.
 */
public class OtherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    public void onImageBtnClicked_other_star(View view){
        Toast.makeText(getApplicationContext(), "친절도 평가 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OtherStarActivity.class);
        startActivity(intent);
    }
    public void onImageBtnClicked_other_url(View view){
        Toast.makeText(getApplicationContext(), "모든 정거장 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OtherUrlActivity.class);
        startActivity(intent);
    }
}
