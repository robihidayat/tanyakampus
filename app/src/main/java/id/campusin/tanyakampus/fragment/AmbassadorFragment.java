package id.campusin.tanyakampus.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.adapter.AmbassadorAdapter;
import id.campusin.tanyakampus.model.AmbassadorModel;


public class AmbassadorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ambassador, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AmbassadorAdapter firstAdapter = new AmbassadorAdapter(getContext(), getAmbassador());
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.recycler_view_fragment_ambassador);
        LinearLayoutManager firstManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);
    }


    private List<AmbassadorModel> getAmbassador(){
        List<AmbassadorModel> models = new ArrayList<>();
        models.add(new AmbassadorModel("foto", "Ilmu Komputer", 1));
        models.add(new AmbassadorModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new AmbassadorModel("foto", "TETI", 2));
        models.add(new AmbassadorModel("foto", "ILKOM", 2));
        models.add(new AmbassadorModel("foto", "Ilmu Komputer", 1));
        models.add(new AmbassadorModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new AmbassadorModel("foto", "TETI", 2));
        models.add(new AmbassadorModel("foto", "ILKOM", 2));
        models.add(new AmbassadorModel("foto", "Ilmu Komputer", 1));
        models.add(new AmbassadorModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new AmbassadorModel("foto", "TETI", 2));
        models.add(new AmbassadorModel("foto", "ILKOM", 2));
        models.add(new AmbassadorModel("foto", "Ilmu Komputer", 1));
        models.add(new AmbassadorModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new AmbassadorModel("foto", "TETI", 2));
        models.add(new AmbassadorModel("foto", "ILKOM", 2));
        return models;
    }

}

