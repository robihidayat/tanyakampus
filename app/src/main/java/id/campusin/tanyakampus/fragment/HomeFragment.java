package id.campusin.tanyakampus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.adapter.DepartmentAdapter;
import id.campusin.tanyakampus.adapter.MovieAdapter;
import id.campusin.tanyakampus.model.DepartmentModel;
import id.campusin.tanyakampus.model.Movie;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        MultiSnapRecyclerView departmentRecyclerView = view.findViewById(R.id.recycler_view_department);
        LinearLayoutManager departmentManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        DepartmentAdapter departmentAdapter = new DepartmentAdapter(getContext(), getDepartment());
        departmentRecyclerView.setLayoutManager(departmentManager);
        departmentRecyclerView.setAdapter(departmentAdapter);

        MovieAdapter firstAdapter = new MovieAdapter(getContext(), getMovie());
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

    }

    private List<DepartmentModel> getDepartment(){
        List<DepartmentModel> models = new ArrayList<>();
        models.add(new DepartmentModel("foto", "Ilmu Komputer", 1));
        models.add(new DepartmentModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new DepartmentModel("foto", "TETI", 2));
        models.add(new DepartmentModel("foto", "ILKOM", 2));
        return models;
    }

    private List<Movie> getMovie(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("test ", false, "robi hidayat", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "Universitas Gadjah Mada",
                "sas","Universitas Gadjah Mada", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "lah iya", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "Institute Teknologi Bandung",
                "sas","Institute Teknologi Bandung", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "coba test", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "Universitas Indonesia",
                "sas","Universitas Indonesia", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "sembakp", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "Universitas Padjajaran",
                "sas","Universitas Padjajaran", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "test aja", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "sas",
                "sas","sas", "sas",
                1.2, 1,
                false, 0.0 ));

        return movies;
    }

}
