package com.tab.quizdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView rv;
    SubAdap mAdap;
    List<SubjectModel> subs;
    @Override
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACk again to exit\n"
                , Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        rv=findViewById(R.id.sub_rv);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setHasFixedSize(true);
        subs=new ArrayList<>();
        SubjectModel sub1=new SubjectModel();
        sub1.setSub_text("Data Structure");
        sub1.setImg(R.drawable.def_icon);
        SubjectModel sub2=new SubjectModel();
        sub2.setSub_text("Data Structure");
        sub2.setImg(R.drawable.def_icon);
        SubjectModel sub3=new SubjectModel();
        sub3.setSub_text("Data Structure");
        sub3.setImg(R.drawable.def_icon);
        SubjectModel sub4=new SubjectModel();
        sub4.setSub_text("Data Structure");
        sub4.setImg(R.drawable.def_icon);
        SubjectModel sub5=new SubjectModel();
        sub5.setSub_text("Data Structure");
        sub5.setImg(R.drawable.def_icon);
        SubjectModel sub6=new SubjectModel();
        sub6.setSub_text("Data Structure");
        sub6.setImg(R.drawable.def_icon);
        SubjectModel sub7=new SubjectModel();
        sub7.setSub_text("Data Structure");
        sub7.setImg(R.drawable.def_icon);
        SubjectModel sub8=new SubjectModel();
        sub8.setSub_text("Data Structure");
        sub8.setImg(R.drawable.def_icon);
        SubjectModel sub9=new SubjectModel();
        sub9.setSub_text("Data Structure");
        sub9.setImg(R.drawable.def_icon);
        subs.add(sub1);
        subs.add(sub2);
        subs.add(sub3);
        subs.add(sub4);
        subs.add(sub5);
        subs.add(sub6);
        subs.add(sub7);
        subs.add(sub8);
        subs.add(sub9);
        mAdap=new SubAdap(subs,SubjectActivity.this);
        rv.setAdapter(mAdap);

    }
    private class SubAdap extends RecyclerView.Adapter<SubAdap.SubHolder>{
        List<SubjectModel> subs;
        Context context;
        SubAdap(List<SubjectModel> subs, Context context){
            this.subs=subs;
            this.context=context;

        }
        @NonNull
        @Override
        public SubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_card,null,false);
            return new SubHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SubHolder holder, int position) {
            SubjectModel sb=subs.get(position);
            holder.tv.setText(sb.getSub_text());
            holder.iv.setImageDrawable(getResources().getDrawable(sb.getImg()));
            holder.sub_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(SubjectActivity.this,CategoryActivity.class);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return subs.size();
        }

        public class SubHolder extends RecyclerView.ViewHolder{
            ImageView iv;
            TextView tv;
            CardView sub_card;

            public SubHolder(@NonNull View itemView) {
                super(itemView);
                iv=itemView.findViewById(R.id.sub_iv);
                tv=itemView.findViewById(R.id.sub_tv);
                sub_card=itemView.findViewById(R.id.sub_card);
            }
        }
    }
}
