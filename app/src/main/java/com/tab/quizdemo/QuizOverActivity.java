package com.tab.quizdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizOverActivity extends AppCompatActivity {
    Button btn;
    String att,totalqns,totalTime;
    TextView resTv;

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent i=new Intent(QuizOverActivity.this,SubjectActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        QuizOverActivity.this.finish();
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_over);
        btn=findViewById(R.id.btn);
        resTv=findViewById(R.id.resTv);
        att=getIntent().getStringExtra("att_cnt");
        totalqns=getIntent().getStringExtra("total_cnt");
        totalTime=getIntent().getStringExtra("total_time");
       int t=Integer.parseInt(totalTime);
       double t1=(double) t/1000;
        resTv.setText("You attempted "+att+" questions out of "+totalqns+" in "+t1+" secs.");
       // Toast.makeText(getApplicationContext(),totalTime,Toast.LENGTH_LONG).show();
     //   Toast.makeText(getApplicationContext(),totalqns,Toast.LENGTH_LONG).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(QuizOverActivity.this,SubjectActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                QuizOverActivity.this.finish();
                startActivity(i);
            }
        });
    }
}
