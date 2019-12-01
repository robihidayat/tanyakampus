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


public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<DepartmentModel> departmentList;


    public DepartmentAdapter(Context mContext, List<DepartmentModel> departmentList){
        this.mContext = mContext;
        this.departmentList = departmentList;
    }

    @Override
    public DepartmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_departement, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DepartmentAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(departmentList.get(i).getTitle());
        String vote = Double.toString(departmentList.get(i).getRate());

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
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.textView_title_department);
            thumbnail = view.findViewById(R.id.cardView_department_thumbnail_department);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    DepartmentModel clickedDataItem = departmentList.get(pos);
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
