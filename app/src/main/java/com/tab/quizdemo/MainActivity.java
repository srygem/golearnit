package com.tab.quizdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    ImageView  clover,clover2,namaste;
    LinearLayout textsplash, texthome;//llGs; //menus;
    Animation frombottom,fromend;
    ConstraintLayout c1;
    Placeholder ph,ph2,ph3,ph4;
    Button btnGs;
    TextView to;
    tutDia tutDia;

    @Override
    public void onBackPressed() {

        if(tutDia.isShowing()){}
            else{super.onBackPressed();}

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        c1=findViewById(R.id.c1);
      //  bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        clover2 = (ImageView) findViewById(R.id.clover2);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        btnGs=findViewById(R.id.gsBtn);
       // llGs=findViewById(R.id.gsView);
        ph=findViewById(R.id.ph);
        ph2=findViewById(R.id.ph2);
        ph3=findViewById(R.id.ph3);
        ph4=findViewById(R.id.ph4);
        namaste=findViewById(R.id.nam);
        to=findViewById(R.id.to);
        btnGs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutDia=new tutDia(MainActivity.this);
                tutDia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                tutDia.show();


               /* Intent i=new Intent(MainActivity.this,tutorialActivity.class);
                MainActivity.this.finish();
                startActivity(i);*/
            }
        });
        // menus = (LinearLayout) findViewById(R.id.menus);
        texthome.setVisibility(View.GONE);
        btnGs.setVisibility(View.GONE);
        to.setVisibility(View.GONE);

        //   menus.setVisibility(View.GONE);

     //   bgapp.animate().translationY(-1900).setDuration(2000).setStartDelay(1000);
      //  clover.animate().translationX(150).setDuration(2000).setStartDelay(1300);
      //  llGs.animate().translationY(300).setDuration(2000).setStartDelay(1000);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                to.setVisibility(View.VISIBLE);
              //  textsplash.animate().translationY(140).alpha(0).setDuration(2000);
                AutoTransition autoTransition = new AutoTransition();
                autoTransition.setDuration(2000);
                TransitionManager.beginDelayedTransition(c1,autoTransition);
                ph.setContentId(R.id.clover);
                ph2.setContentId(R.id.clover2);
                ph3.setContentId(R.id.nam);
                ph4.setContentId(R.id.textsplash);
                texthome.setVisibility(View.VISIBLE);
              //  menus.setVisibility(View.VISIBLE);
                texthome.startAnimation(frombottom);
                btnGs.setVisibility(View.VISIBLE);
               // menus.startAnimation(frombottom);
            }
        }, 2000);



    }
}
