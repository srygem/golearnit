package com.tab.quizdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView rev;
    List<CategoryModel> cms;
    CatAdap mAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rev=findViewById(R.id.cat_rv);
        rev.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rev.setHasFixedSize(true);
        cms=new ArrayList<>();
        //Dummy Data
        CategoryModel cm1=new CategoryModel();
        cm1.setCat_text("Practice");
        cm1.setSub_text("Test your knowledge...");
        cm1.setPointsamt("0 pts");
        cm1.setBack_img(R.drawable.ic_sentiment_satisfied_black_24dp);
        cm1.setStatus_text("Available 24x7 !");
        CategoryModel cm2=new CategoryModel();
        cm2.setCat_text("Contest Quiz");
        cm2.setSub_text("Win rewards !");
        cm2.setPointsamt("50 pts");
        cm2.setStatus_text("Not available currently!");
        cm2.setBack_img(R.drawable.ic_sentiment_dissatisfied_black_24dp);
        CategoryModel cm3=new CategoryModel();
        cm3.setCat_text("Bookmarked");
        cm3.setSub_text("Revisit saved questions");
        cm3.setPointsamt("0 pts");
        cm3.setStatus_text("Available 24x7 !");
        cm3.setBack_img(R.drawable.ic_sentiment_satisfied_black_24dp);
        cms.add(cm1);
        cms.add(cm2);
        cms.add(cm3);
        mAdap=new CatAdap(cms,CategoryActivity.this);
        rev.setAdapter(mAdap);





    }
    private class CatAdap extends RecyclerView.Adapter<CatAdap.CatHolder>{
        List<CategoryModel> cats;
        Context context;
        CatAdap(List<CategoryModel> cats, Context context){
            this.cats=cats;
            this.context=context;
        }
        @NonNull
        @Override
        public CatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cat_card,parent,false);
            return new CatHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CatAdap.CatHolder holder, int position) {
            CategoryModel cm=cats.get(position);
            holder.catTv.setText(cm.getCat_text());
            holder.subCatTv.setText(cm.getSub_text());
            holder.statusTv.setText(cm.getStatus_text());
            holder.pointsTv.setText(cm.getPointsamt());
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i=new Intent(CategoryActivity.this,QuizesActivity.class);
                    startActivity(i);
                }
            });
            holder.iv.setImageDrawable(getResources().getDrawable(cm.getBack_img()));


        }

        @Override
        public int getItemCount() {
            return cats.size();
        }

        public class CatHolder extends RecyclerView.ViewHolder{
            TextView catTv,subCatTv,statusTv,pointsTv;
            ImageView iv;
            CardView cv;

            public CatHolder(@NonNull final View itemView) {
                super(itemView);
                catTv=itemView.findViewById(R.id.catTv);
                subCatTv=itemView.findViewById(R.id.sub_cat_tv);
                statusTv=itemView.findViewById(R.id.status_tv);
                pointsTv=itemView.findViewById(R.id.points_tv);
                iv=itemView.findViewById(R.id.status_iv);
                cv=itemView.findViewById(R.id.cat_card);
               /* itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b){
                            cv.animate().scaleX(150).setDuration(300);
                            Animation anim= AnimationUtils.loadAnimation(context,R.anim.scale_in_tv);
                            anim.setDuration(300);
                            itemView.startAnimation(anim);
                            anim.setFillAfter(true);
                        }
                        else{
                            cv.animate().scaleX(100).setDuration(300);
                            Animation anim= AnimationUtils.loadAnimation(context,R.anim.scale_out_tv);
                            anim.setDuration(300);
                            itemView.startAnimation(anim);
                            anim.setFillAfter(true);
                        }
                    }
                });*/

            }
        }
    }

}
