package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.adapter.FilterSearchAdapter;
import id.campusin.tanyakampus.model.FilterSearchModel;

public class SearchActivity extends AppCompatActivity{

    ImageView imageViewSearch, backPress;
    EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextSearch = findViewById(R.id.editText_activity_search);
        imageViewSearch = findViewById(R.id.imageView_halaman_pencarian);
        backPress = findViewById(R.id.imageView_back_search);

        MultiSnapRecyclerView departmentRecyclerView = findViewById(R.id.recycler_view_search_filter);
        LinearLayoutManager departmentManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        FilterSearchAdapter filterSearchAdapter = new FilterSearchAdapter(getApplicationContext(), getFilterSearch());
        departmentRecyclerView.setLayoutManager(departmentManager);
        departmentRecyclerView.setAdapter(filterSearchAdapter);
        backPress.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }


    private List<FilterSearchModel> getFilterSearch(){
            List<FilterSearchModel> models = new ArrayList<>();
            models.add(new FilterSearchModel("foto", "Jurusan", 1));
            models.add(new FilterSearchModel("foto", "Kampus", 2));
            models.add(new FilterSearchModel("foto", "Ambassador", 2));
            models.add(new FilterSearchModel("foto", "Ambassador", 2));
            models.add(new FilterSearchModel("foto", "Jurusan", 1));
            models.add(new FilterSearchModel("foto", "Kampus", 2));
            models.add(new FilterSearchModel("foto", "Ambassador", 2));
            models.add(new FilterSearchModel("foto", "Ambassador", 2));
            return models;
        }
}
