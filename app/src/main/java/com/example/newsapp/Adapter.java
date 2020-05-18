package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

   Context context;
   List<Articles> articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            Articles a=articles.get(position);
            holder.tvTitle.setText(a.getTitle());
            holder.tvSource.setText(a.getSource().getName());
            holder.tvDate.setText(dateTime(a.getPublishedAt()));

            String imageUri=a.getUrlToImage();
            Picasso.with(context).load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvtitle);
            tvSource=itemView.findViewById(R.id.tvSource);
            tvDate=itemView.findViewById(R.id.tvDate);
            imageView=itemView.findViewById(R.id.image_photo);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }

    public String dateTime(String t)
    {
        PrettyTime prettyTime=new PrettyTime(new Locale(getCountry()));
        String time=null;
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:",Locale.ENGLISH);
            Date date=simpleDateFormat.parse(t);
            time=prettyTime.format(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return time;
    }
    public String getCountry()
    {
        Locale locale=Locale.getDefault();
        String Country=locale.getCountry();
        return Country.toLowerCase();
    }
}
