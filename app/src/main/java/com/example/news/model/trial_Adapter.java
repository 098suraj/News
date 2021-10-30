package com.example.news.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;

import com.example.news.activities.WebActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class trial_Adapter extends RecyclerView.Adapter<trial_Adapter.trial_viewholder> {
    private final List<Article>articleArrayList;
    private Context context;
    public trial_Adapter(List<Article>articleArrayList){
        this.articleArrayList=articleArrayList;
    }
    @NonNull
    @Override
    public trial_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //+----------------------------------Inflating--------------------------------------------------------+//
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trial_itm,parent,false);
        return new trial_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trial_viewholder holder, int position) {
        final Article articleModel=articleArrayList.get(position);
        String urlimage=articleModel.getUrlToImage();
        String url=articleModel.getUrl();
        if (!TextUtils.isEmpty(articleModel.getTitle())){
            holder.textView.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty((articleModel.getDescription()))){
            holder.desc.setText(articleModel.getDescription());
        }
        if (urlimage!=null){
            Picasso.get().load(urlimage).into(holder.imageView);
        }
        context=holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url!=null) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url",url);

                        context.startActivity(intent);
                }else
                    Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }


    public class trial_viewholder extends RecyclerView.ViewHolder {
        ImageView imageView; TextView textView ,desc;
        CardView car;
        public trial_viewholder(@NonNull View itemView) {
            super(itemView);
            car=itemView.findViewById(R.id.trialcard);
            imageView=itemView.findViewById(R.id.trialimg);
            textView=itemView.findViewById(R.id.tialtitle);
            desc=itemView.findViewById(R.id.tialdescription);
        }

    }
}
