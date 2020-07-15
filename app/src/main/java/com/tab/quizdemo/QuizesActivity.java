package com.tab.quizdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizesActivity extends AppCompatActivity {
    RecyclerView rev;
    List<QuizModel> qzs;
    QuizAdap mAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizes);
        rev=findViewById(R.id.quiz_rev);
        rev.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rev.setHasFixedSize(true);
        qzs=new ArrayList<>();
        //Dummy Data
        QuizModel qm1=new QuizModel();
        qm1.setQuizh1("Basics");
        qm1.setQuizh2("Introduction");
        QuizModel qm2=new QuizModel();
        qm2.setQuizh1("Basics");
        qm2.setQuizh2("Introduction");
        QuizModel qm3=new QuizModel();
        qm3.setQuizh1("Basics");
        qm3.setQuizh2("Introduction");
        qzs.add(qm1);
        qzs.add(qm2);
        qzs.add(qm3);
        mAdap=new QuizAdap(qzs,QuizesActivity.this);
        rev.setAdapter(mAdap);


    }
    private class QuizAdap extends RecyclerView.Adapter<QuizAdap.QuizHolder>{
        List<QuizModel> qzs;
        Context context;
        QuizAdap(List<QuizModel> qzs, Context context){
            this.context=context;
            this.qzs=qzs;

        }
        @NonNull
        @Override
        public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.quizes_card,parent,false);
            return new QuizHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizHolder holder, int position) {
            QuizModel qz=qzs.get(position);
            holder.head.setText(qz.getQuizh1());
            holder.desc.setText(qz.getQuizh2());
            holder.attempt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openTestdia();
                   /* Intent i=new Intent(QuizesActivity.this,QuestionsActivity.class);
                    startActivity(i);*/
                }
            });
          //  holder.q_icon_iv.setImageDrawable(getResources().getDrawable(qz.getImg()));


        }

        @Override
        public int getItemCount() {
            return qzs.size();
        }

        public class QuizHolder extends RecyclerView.ViewHolder{
            TextView head,desc;
            ImageView q_icon_iv;
            Button attempt;

            public QuizHolder(@NonNull View itemView) {
                super(itemView);
                head=itemView.findViewById(R.id.head);
                desc=itemView.findViewById(R.id.desc);
                q_icon_iv=itemView.findViewById(R.id.q_icon);
                attempt=itemView.findViewById(R.id.attempt);
            }
        }
    }
    public void openTestdia() {
        FragmentManager fm =getSupportFragmentManager();
        TestDia rd = new TestDia ();
        rd.show(fm, "test");

    }
}
