package id.campusin.tanyakampus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.SearchActivity;
import id.campusin.tanyakampus.adapter.DepartmentAdapter;
import id.campusin.tanyakampus.adapter.UniversityAdapter;
import id.campusin.tanyakampus.model.DepartmentModel;
import id.campusin.tanyakampus.model.UniversityModel;

public class HomeFragment extends Fragment {

    private CardView searchCard;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        searchCard = view.findViewById(R.id.cardView_search);

        MultiSnapRecyclerView departmentRecyclerView = view.findViewById(R.id.recycler_view_department);
        LinearLayoutManager departmentManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        DepartmentAdapter departmentAdapter = new DepartmentAdapter(getContext(), getDepartment());
        departmentRecyclerView.setLayoutManager(departmentManager);
        departmentRecyclerView.setAdapter(departmentAdapter);

        UniversityAdapter firstAdapter = new UniversityAdapter(getContext(), getUniversity());
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.recycler_view_university);
        LinearLayoutManager firstManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        MultiSnapRecyclerView ambassadorRecycle = view.findViewById(R.id.recycler_view_ambassador);
        LinearLayoutManager ambassadorManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        DepartmentAdapter ambassadorAdapter = new DepartmentAdapter(getContext(), getDepartment());
        ambassadorRecycle.setAdapter(ambassadorAdapter);
        ambassadorRecycle.setLayoutManager(ambassadorManager);

        MultiSnapRecyclerView newsRecycle = view.findViewById(R.id.recycler_view_event);
        LinearLayoutManager newsManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        DepartmentAdapter newsAdapter = new DepartmentAdapter(getContext(), getDepartment());
        newsRecycle.setAdapter(newsAdapter);
        newsRecycle.setLayoutManager(newsManager);

        searchCard.setOnClickListener( v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });


    }

    private List<DepartmentModel> getDepartment(){
        List<DepartmentModel> models = new ArrayList<>();
        models.add(new DepartmentModel("foto", "Ilmu Komputer", 1));
        models.add(new DepartmentModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new DepartmentModel("foto", "TETI", 2));
        models.add(new DepartmentModel("foto", "ILKOM", 2));
        return models;
    }

    private List<UniversityModel> getUniversity(){
        List<UniversityModel> university = new ArrayList<>();
        String description = "Universitas ini adalah universitas yang sayang bagus sekali, tapi sampai bagusnya saya sampai tidak tahu harus berkata apa" +
                "semoga kedepannya";
        university.add(new UniversityModel("foto", "Ilmu Komputer", 1, description));
        university.add(new UniversityModel("foto", "Elektronika Instrumentasi", 2,description));
        university.add(new UniversityModel("foto", "TETI", 2,description));
        university.add(new UniversityModel("foto", "ILKOM", 2,description));
        return university;
    }

}
