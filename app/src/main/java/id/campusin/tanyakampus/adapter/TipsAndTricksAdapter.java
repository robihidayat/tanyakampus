package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.TipsAndtricksModel;


public class TipsAndTricksAdapter extends RecyclerView.Adapter<TipsAndTricksAdapter.MyViewHolder> {

    private Context mContext;
    private List<TipsAndtricksModel> tipsAndtricksModels;


    public TipsAndTricksAdapter(Context mContext, List<TipsAndtricksModel> tipsAndtricksModels){
        this.mContext = mContext;
        this.tipsAndtricksModels = tipsAndtricksModels;
    }

    @Override
    public TipsAndTricksAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tips_and_trics, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TipsAndTricksAdapter.MyViewHolder viewHolder, int i){
        Glide.with(mContext)
                .load(tipsAndtricksModels.get(i).getPosterPath())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return tipsAndtricksModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            thumbnail = view.findViewById(R.id.ImageView_card_university_thumbnail);
            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    TipsAndtricksModel clickedDataItem = tipsAndtricksModels.get(pos);
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
