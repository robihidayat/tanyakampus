package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.DepartmentModel;
import id.campusin.tanyakampus.model.FilterSearchModel;


public class FilterSearchAdapter extends RecyclerView.Adapter<FilterSearchAdapter.MyViewHolder> {

    private Context mContext;
    private List<FilterSearchModel> filterSearchModels;


    public FilterSearchAdapter(Context mContext, List<FilterSearchModel> filterSearchModels){
        this.mContext = mContext;
        this.filterSearchModels = filterSearchModels;
    }

    @Override
    public FilterSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_filter_search, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FilterSearchAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(filterSearchModels.get(i).getTitle());
    }

    @Override
    public int getItemCount(){
        return filterSearchModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.editText_filter_search);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    FilterSearchModel clickedDataItem = filterSearchModels.get(pos);
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
