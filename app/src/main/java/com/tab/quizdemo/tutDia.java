package com.tab.quizdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;


public class tutDia extends Dialog {
  ViewFlipper vf;
  int curTut;
  ImageView dot_o,dot_t,dot_th,dot_f;
  TextView tut_tv;

  public tutDia(@NonNull Context context) {
    super(context);
  }

  @Override
  public void onBackPressed() {
    if(this.isShowing()){}
    else{}
    super.onBackPressed();
  }

  @Override
  public void onStart() {
    super.onStart();

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.tut_dia);
    setCanceledOnTouchOutside(false);
    setCancelable(false);
    vf=findViewById(R.id.vF);
      dot_o=findViewById(R.id.dot_o);
      dot_t=findViewById(R.id.dot_t);
      dot_th=findViewById(R.id.dot_th);
      dot_f=findViewById(R.id.dot_f);
      tut_tv=findViewById(R.id.tut_text);
      curTut=0;
      updateDots(curTut);
      vf.setFlipInterval(3000);
      vf.setInAnimation(getContext(),R.anim.slide_in_right);
      vf.setOutAnimation(getContext(),R.anim.slide_out_left);
      vf.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
          public void onAnimationStart(Animation animation) {
              curTut=vf.getDisplayedChild();
              updateDots(curTut);
              Log.d("missionFlipper", vf.getDisplayedChild()+"");
          }
          public void onAnimationRepeat(Animation animation) {}
          public void onAnimationEnd(Animation animation) {}
      });
      vf.startFlipping();
  }
  private void updateDots(int curTut){
    switch (curTut){
      case 0:
        dot_o.setImageResource(R.drawable.dot_bigger);
        dot_t.setImageResource(R.drawable.dot);
        dot_th.setImageResource(R.drawable.dot);
        dot_f.setImageResource(R.drawable.dot);
        tut_tv.setText("1.Read content");
        break;
      case 1:
        dot_o.setImageResource(R.drawable.dot);
        dot_t.setImageResource(R.drawable.dot_bigger);
        dot_th.setImageResource(R.drawable.dot);
        dot_f.setImageResource(R.drawable.dot);
        tut_tv.setText("2.Participate in quiz");
        break;
      case 2:
        dot_o.setImageResource(R.drawable.dot);
        dot_t.setImageResource(R.drawable.dot);
        dot_th.setImageResource(R.drawable.dot_bigger);
        dot_f.setImageResource(R.drawable.dot);
        tut_tv.setText("3.Win points");
        break;
      case 3:
        dot_o.setImageResource(R.drawable.dot);
        dot_t.setImageResource(R.drawable.dot);
        dot_th.setImageResource(R.drawable.dot);
        dot_f.setImageResource(R.drawable.dot_bigger);
        tut_tv.setText("4.Redeem points as cash");
        break;
        default:{

        }

    }
  }




}
