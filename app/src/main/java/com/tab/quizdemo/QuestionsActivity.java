package com.tab.quizdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsActivity extends AppCompatActivity {
    Chronometer ch;
    ViewFlipper qnFlipper;
    List<QuestionModel> qns;
    RadioGroup resRg;
    Button sub,back;
    String url,qid;
    TextView ctr;
    RadioButton op1,op2,op3,op4;
    FragmentManager fm;
    ProgressDia rd;
    RadioGroup.OnCheckedChangeListener chklis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ch=findViewById(R.id.chrono);
        ctr=findViewById(R.id.ctr);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        op4=findViewById(R.id.op4);
        url=getIntent().getStringExtra("link");
        qid=getIntent().getStringExtra("qid");
        qnFlipper=findViewById(R.id.qF);
        updateCtr();
        resRg=findViewById(R.id.radioGroup);
        sub=findViewById(R.id.sub);
        back=findViewById(R.id.back);
        ch.setFormat("Time: %s");
        qns=new ArrayList<>();
        chklis=new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                try{
                    List<RadioButton>  rbs=new ArrayList<>();
                    rbs.add(op1);
                    rbs.add(op2);
                    rbs.add(op3);
                    rbs.add(op4);

                    for(int k=0;k<rbs.size();k++){
                        if(rbs.get(k).getId()==i){
                            RadioButton rb=radioGroup.findViewById(i);
                            rb.setBackgroundColor(Color.CYAN);
                        }
                        else{
                            rbs.get(k).setBackgroundColor(Color.WHITE);
                          //  qns.get(qnFlipper.getDisplayedChild()).setqRes(String.valueOf(k));
                           // Log.d("qres>>>>", k+""+"data");
                          //  Log.d("qres>>>>", "data");

                        }
                    }

                }
                catch (Exception e){

                }
            }
        };
   /*  QuestionModel q1=new QuestionModel();
        q1.setQ_text(" Question 1");
        q1.setQ_o1("Option");
        q1.setQ_o2("Option");
        q1.setQ_o3("Option");
        q1.setQ_o4("Option");
        q1.setTimer("10");
        QuestionModel q2=new QuestionModel();
        q2.setQ_text(" Question 2");
        q2.setQ_o1("Option");
        q2.setQ_o2("Option");
        q2.setQ_o3("Option");
        q2.setQ_o4("Option");
        q2.setTimer("10");
        QuestionModel q3=new QuestionModel();
        q3.setQ_text(" Question 3");
        q3.setQ_o1("Option");
        q3.setQ_o2("Option");
        q3.setQ_o3("Option");
        q3.setQ_o4("Option");
        q3.setTimer("10");
        qns.add(q1);
        qns.add(q2);
        qns.add(q3);
        addTvsToFlipper();
        setOptions(qns.get(qnFlipper.getDisplayedChild()).getQ_o1(),qns.get(qnFlipper.getDisplayedChild()).getQ_o2(),qns.get(qnFlipper.getDisplayedChild()).getQ_o3(),qns.get(qnFlipper.getDisplayedChild()).getQ_o4());
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSub();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack();


            }
        });
       // ch.setBase(SystemClock.elapsedRealtime()+1000);
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                int time= (Integer.parseInt("10"))*1000;
                if((SystemClock.elapsedRealtime()-ch.getBase())>=time){
                    onSub();
                   // ch.setBase(SystemClock.elapsedRealtime());
                    // Toast.makeText(getApplicationContext(),"Bing !",Toast.LENGTH_LONG).show();

                   // ch.stop();
                }
            }
        });
//        rd.dismiss();
        ch.setBase(SystemClock.elapsedRealtime());


// screen and CPU will stay awake during this section
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ch.start();*/



        getQuestions(url,qid);


    }
    private void onSub(){
        int radioButtonID = resRg.getCheckedRadioButtonId();
        View radioButton = resRg.findViewById(radioButtonID);
        String idx = String.valueOf(resRg.indexOfChild(radioButton));
       qns.get(qnFlipper.getDisplayedChild()).setqRes(idx);
       if(Integer.parseInt(idx)!=-1){
           qns.get(qnFlipper.getDisplayedChild()).setAttempted(true);
       }
       else{
           qns.get(qnFlipper.getDisplayedChild()).setAttempted(false);
       }
        Log.d("QRESPS>>>>", idx+""+"data");
        qns.get(qnFlipper.getDisplayedChild()).setTotalTime(SystemClock.elapsedRealtime()-ch.getBase()+"");
        String tm=qns.get(qnFlipper.getDisplayedChild()).getTotalTime();
//        String tmNew=tm.substring(tm.length()-2,tm.length());

        //int secs=Integer.parseInt(tmNew);
     //   Toast.makeText(getApplicationContext(),tm,Toast.LENGTH_LONG).show();
        changeQuesToNext();
    }
    private void onBack(){
        int radioButtonID = resRg.getCheckedRadioButtonId();
        View radioButton = resRg.findViewById(radioButtonID);
        String idx = String.valueOf(resRg.indexOfChild(radioButton));
        qns.get(qnFlipper.getDisplayedChild()).setqRes(idx);
        changeQuesToPrev();
    }
    private void changeQuesToNext(){
     //   back.setVisibility(View.VISIBLE);
        if(qnFlipper.getDisplayedChild()==qns.size()-1){
            endQuiz();

        }
        else{
            resRg.setOnCheckedChangeListener(null);
            qnFlipper.showNext();
            setOptions(qns.get(qnFlipper.getDisplayedChild()).getQ_o1(),qns.get(qnFlipper.getDisplayedChild()).getQ_o2(),qns.get(qnFlipper.getDisplayedChild()).getQ_o3(),qns.get(qnFlipper.getDisplayedChild()).getQ_o4());
            ch.setBase(SystemClock.elapsedRealtime());
            ch.start();
            updateCtr();
          //  back.setVisibility(View.VISIBLE);
            try{
                ((RadioButton)resRg.getChildAt(Integer.parseInt(qns.get(qnFlipper.getDisplayedChild()).getqRes()))).setChecked(true);
              }
            catch (Exception e){
                resRg.clearCheck();
            }
            if(qnFlipper.getDisplayedChild()==qns.size()-1){
                sub.setText("Submit");
            }
        }


    }
    private void changeQuesToPrev(){
        sub.setText("Next");
        if(qnFlipper.getDisplayedChild()==0){
            // back.setVisibility(View.GONE);
        }
        else{
            sub.setEnabled(true);
            resRg.setOnCheckedChangeListener(null);
            qnFlipper.showPrevious();
            setOptions(qns.get(qnFlipper.getDisplayedChild()).getQ_o1(),qns.get(qnFlipper.getDisplayedChild()).getQ_o2(),qns.get(qnFlipper.getDisplayedChild()).getQ_o3(),qns.get(qnFlipper.getDisplayedChild()).getQ_o4());
            updateCtr();
            try {
                ((RadioButton) resRg.getChildAt(Integer.parseInt(qns.get(qnFlipper.getDisplayedChild()).getqRes()))).setChecked(true);
            }
            catch (Exception e){
                resRg.clearCheck();
            }
            if(qnFlipper.getDisplayedChild()==0){
                back.setVisibility(View.GONE);
            }
        }

    }
    public List<TextView> getTvs(){
        List<TextView> tvsEng=new ArrayList<>();
        for(int i=0;i<qns.size();i++){
            TextView tvEng=new TextView(QuestionsActivity.this);
            tvEng.setTextColor(Color.BLACK);
            tvEng.setTextSize(18);
            tvEng.setGravity(Gravity.START);
            tvEng.setText(qns.get(i).getQ_text());
            tvsEng.add(tvEng);
        }
        return tvsEng;

    }

    public void addTvsToFlipper(){
        List<TextView> engTvs=getTvs();
        for (int i=0;i<engTvs.size();i++){
            qnFlipper.addView(engTvs.get(i));
        }
    }
    public void updateCtr(){
        ctr.setText("Question No. "+(qnFlipper.getDisplayedChild()+1));
    }
    private void getQuestions(String url,String quizId){
       openProgressdia();
        String s="http://"+url+".ngrok.io/golearnit-service/quiz/getQuiz?quizId="+quizId;
        Log.d("RES>>>err", s);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, s, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("RES>>>", response+"");

                try{
                JSONArray jsonArray=response.getJSONArray("questionsList");
                final String timer=response.getString("stopWatch");
                for (int i=0;i<jsonArray.length();i++){
                QuestionModel questionModel=new QuestionModel();
                questionModel.setQ_text(jsonArray.getJSONObject(i).getString("question"));
                questionModel.setQid(jsonArray.getJSONObject(i).getString("questionId"));
                JSONArray oarr=new JSONArray();
                oarr=jsonArray.getJSONObject(i).getJSONArray("optionSet");
                questionModel.setQ_o1(oarr.getString(0));
                    questionModel.setQ_o2(oarr.getString(1));
                    questionModel.setQ_o3(oarr.getString(2));
                    questionModel.setQ_o4(oarr.getString(3));
                qns.add(questionModel);



                }
                    addTvsToFlipper();
                    setOptions(qns.get(qnFlipper.getDisplayedChild()).getQ_o1(),qns.get(qnFlipper.getDisplayedChild()).getQ_o2(),qns.get(qnFlipper.getDisplayedChild()).getQ_o3(),qns.get(qnFlipper.getDisplayedChild()).getQ_o4());
                    sub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onSub();
                        }
                    });
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBack();


                        }
                    });
                    ch.setBase(SystemClock.elapsedRealtime());
                    ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        @Override
                        public void onChronometerTick(Chronometer chronometer) {
                            int time= Integer.parseInt(timer)*1000;
                            if((SystemClock.elapsedRealtime()-ch.getBase())>=time){
                                //ch.setBase(SystemClock.elapsedRealtime());
                               // Toast.makeText(getApplicationContext(),"Bing !",Toast.LENGTH_LONG).show();
                               // ch.stop();
                                onSub();
                            }
                        }
                    });
                    rd.dismiss();
                    ch.setBase(SystemClock.elapsedRealtime());


// screen and CPU will stay awake during this section
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                    ch.start();

                }
                catch (JSONException e){

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent i=new Intent(QuestionsActivity.this,SubjectActivity.class);
                if(error instanceof NoConnectionError){
                    showErrorMessage("No Internet Available !");
                }
                else{
                    showErrorMessage("Can't reach server !");

                }
                QuestionsActivity.this.finish();
                startActivity(i);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
        }};
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonObjectRequest);
    }
    private void setOptions(String o1,String o2,String o3,String o4){
        op1.setBackgroundColor(Color.WHITE);
        op2.setBackgroundColor(Color.WHITE);
        op3.setBackgroundColor(Color.WHITE);
        op4.setBackgroundColor(Color.WHITE);
        resRg.setOnCheckedChangeListener(chklis);
        op1.setText(o1);
        op2.setText(o2);
        op3.setText(o3);
        op4.setText(o4);

    }

  public void openProgressdia() {
      fm =getSupportFragmentManager();
      rd = new ProgressDia ();
      rd.show(fm, "prog");

  }
  public void endQuiz(){
      //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
     // Toast.makeText(getApplicationContext(),attemptedqs(qns),Toast.LENGTH_LONG).show();
      sub.setEnabled(false);
      ch.setBase(SystemClock.elapsedRealtime());
      ch.stop();
      ch.setEnabled(false);
      Intent i=new Intent(QuestionsActivity.this,QuizOverActivity.class);
      i.putExtra("att_cnt",attemptedqs(qns));
      i.putExtra("total_cnt",String.valueOf(qns.size()));
      i.putExtra("total_time",gettotalTime(qns));
      QuestionsActivity.this.finish();
      startActivity(i);


  }
    private void showSuccessMessage(String msg){

        LayoutInflater inflater =getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_green,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.cus_toast);
        text.setText(msg);
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    private void showErrorMessage(String msg){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_red,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.cus_toast);
        text.setText(msg);
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    private String attemptedqs(List<QuestionModel> qns){
        int cnt=0;
        for (int z=0;z<qns.size();z++){
            if(qns.get(z).isAttempted()){
                cnt++;
            }
        }
        return  String.valueOf(cnt);
    }
    private String gettotalTime(List<QuestionModel> qns){
        int time=0;
     for(int z=0;z<qns.size();z++){
       time=time+Integer.parseInt(qns.get(z).getTotalTime());
    }
     return String.valueOf(time);

}}
