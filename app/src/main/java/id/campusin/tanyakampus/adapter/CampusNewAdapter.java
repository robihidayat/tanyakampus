package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.CampusNewsModel;


public class CampusNewAdapter extends RecyclerView.Adapter<CampusNewAdapter.MyViewHolder> {

    private Context mContext;
    private List<CampusNewsModel> campusList;


    public CampusNewAdapter(Context mContext, List<CampusNewsModel> campusList){
        this.mContext = mContext;
        this.campusList = campusList;
    }

    @Override
    public CampusNewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_campus_new, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CampusNewAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(campusList.get(i).getTitle());
        viewHolder.description.setText(campusList.get(i).getDescription());
        Glide.with(mContext)
                .load(campusList.get(i).getPosterPath())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return campusList.size();
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
                    CampusNewsModel clickedDataItem = campusList.get(pos);
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
