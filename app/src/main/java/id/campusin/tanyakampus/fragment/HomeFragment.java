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
import id.campusin.tanyakampus.adapter.MovieAdapter;
import id.campusin.tanyakampus.model.Movie;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.first_recycler_view);
        MovieAdapter firstAdapter = new MovieAdapter(getContext(), getMovie());
        LinearLayoutManager firstManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);
    }

    private List<Movie> getMovie(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("test ", false, "robi hidayat", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "sas",
                "sas","sas", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "lah iya", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "sas",
                "sas","sas", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "coba test", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "sas",
                "sas","sas", "sas",
                1.2, 1,
                false, 0.0 ));
        movies.add(new Movie("test path", false, "sembakp", "string", IntStream.iterate(10, x -> x + 10).limit(5)
                .boxed()
                .collect(Collectors.toList()) ,1, "sas",
                "sas","sas", "sas",
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
