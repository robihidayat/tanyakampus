package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.CampusNewsModel;
import id.campusin.tanyakampus.model.DepartmentModel;


public class CampusNewAdapter extends RecyclerView.Adapter<CampusNewAdapter.MyViewHolder> {

    private Context mContext;
    private List<CampusNewsModel> departmentList;


    public CampusNewAdapter(Context mContext, List<CampusNewsModel> departmentList){
        this.mContext = mContext;
        this.departmentList = departmentList;
    }

    @Override
    public CampusNewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_campus_new, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CampusNewAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(departmentList.get(i).getTitle());
        viewHolder.description.setText(departmentList.get(i).getDescription());

        String poster = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQr6-IcoaRHEXBo9LDgZ18uE9digJnQ6ytuJ-a0Gjh89tbhjwiX" ; //+ movieList.get(i).getPosterPath()

        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return departmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.TextView_campus_news_title);
            description = view.findViewById(R.id.TextView_campus_news_description);
            thumbnail = view.findViewById(R.id.ImageView_campus_news_thumbnail_department);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    CampusNewsModel clickedDataItem = departmentList.get(pos);
                   /* Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("movies", clickedDataItem );
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    */
                    Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
