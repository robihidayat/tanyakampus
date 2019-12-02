package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.TipsAndtricksModel;


public class TipsAndTricksAdapter extends RecyclerView.Adapter<TipsAndTricksAdapter.MyViewHolder> {

    private Context mContext;
    private List<TipsAndtricksModel> departmentList;


    public TipsAndTricksAdapter(Context mContext, List<TipsAndtricksModel> departmentList){
        this.mContext = mContext;
        this.departmentList = departmentList;
    }

    @Override
    public TipsAndTricksAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tips_and_trics, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TipsAndTricksAdapter.MyViewHolder viewHolder, int i){
//        viewHolder.title.setText(departmentList.get(i).getTitle());
//        viewHolder.description.setText(departmentList.get(i).getDescription());
//
//        String poster = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQr6-IcoaRHEXBo9LDgZ18uE9digJnQ6ytuJ-a0Gjh89tbhjwiX" ; //+ movieList.get(i).getPosterPath()
//
//        Glide.with(mContext)
//                .load(poster)
//                .placeholder(R.drawable.load)
//                .into(viewHolder.thumbnail);
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
//            title = view.findViewById(R.id.TextView_campus_news_title);
//            description = view.findViewById(R.id.TextView_campus_news_description);
//            thumbnail = view.findViewById(R.id.ImageView_campus_news_thumbnail_department);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    TipsAndtricksModel clickedDataItem = departmentList.get(pos);
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