package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.DetailUniversityActivity;
import id.campusin.tanyakampus.model.response.DataUniversityResponse;


public class UniversityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DataUniversityResponse> universityList;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    public UniversityAdapter(Context mContext, List<DataUniversityResponse> universityList){
        this.mContext = mContext;
        this.universityList = universityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        System.out.println("VIEW_TYPE_ITEM " +VIEW_TYPE_ITEM);
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_university, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_university, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    public int getItemViewType(int position) {
        return universityList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MyViewHolder) {
            populateItemRows((MyViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return universityList == null ? 0 : universityList.size();
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
                    DataUniversityResponse clickedDataItem = universityList.get(pos);
                    Intent intent = new Intent(mContext, DetailUniversityActivity.class);
                    intent.putExtra("universityModel", clickedDataItem );
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private void populateItemRows(MyViewHolder viewHolder, int position) {
        viewHolder.title.setText(universityList.get(position).getName());
        Glide.with(mContext)
                .load(universityList.get(position).getAvatar())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public LoadingViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.TextView_card_university_title);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {

    }
}
