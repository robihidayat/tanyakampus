package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.DetailUniversityActivity;
import id.campusin.tanyakampus.model.UniversityModel;


public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.MyViewHolder> {

    private Context mContext;
    private List<UniversityModel> universityList;


    public UniversityAdapter(Context mContext, List<UniversityModel> universityList){
        this.mContext = mContext;
        this.universityList = universityList;
    }

    @Override
    public UniversityAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_university, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UniversityAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(universityList.get(i).getTitle());
        Glide.with(mContext)
                .load(universityList.get(i).getPosterPath())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return universityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.TextView_card_university_title);
            thumbnail = view.findViewById(R.id.ImageView_card_university_thumbnail);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    UniversityModel clickedDataItem = universityList.get(pos);
                    Intent intent = new Intent(mContext, DetailUniversityActivity.class);
                    intent.putExtra("universityModel", clickedDataItem );
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
