package id.campusin.tanyakampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.DetailListAmbassadorActivity;
import id.campusin.tanyakampus.model.AmbassadorModel;


public class AmbassadorAdapter extends RecyclerView.Adapter<AmbassadorAdapter.MyViewHolder> {

    private Context mContext;
    private List<AmbassadorModel> filterSearchModels;


    public AmbassadorAdapter(Context mContext, List<AmbassadorModel> filterSearchModels){
        this.mContext = mContext;
        this.filterSearchModels = filterSearchModels;
    }

    @Override
    public AmbassadorAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_ambassador, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AmbassadorAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(filterSearchModels.get(i).getTitle());
        viewHolder.department.setText(filterSearchModels.get(i).getTitle());

    }

    @Override
    public int getItemCount(){
        return filterSearchModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, department;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.textView_cardAmbassador_name);
            department = view.findViewById(R.id.textView_cardAmbassador_department);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    AmbassadorModel ambassadorModel = filterSearchModels.get(pos);
                    Intent intent = new Intent(mContext, DetailListAmbassadorActivity.class);
                    intent.putExtra("ambassadorModel", ambassadorModel );
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    Toast.makeText(v.getContext(), "You clicked " + ambassadorModel.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
