package id.campusin.tanyakampus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.SearchActivity;
import id.campusin.tanyakampus.adapter.CampusNewAdapter;
import id.campusin.tanyakampus.adapter.DepartmentAdapter;
import id.campusin.tanyakampus.adapter.TipsAndTricksAdapter;
import id.campusin.tanyakampus.adapter.UniversityAdapter;
import id.campusin.tanyakampus.model.CampusNewsModel;
import id.campusin.tanyakampus.model.DepartmentModel;
import id.campusin.tanyakampus.model.TipsAndtricksModel;
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
        DepartmentAdapter ambassadorAdapter = new DepartmentAdapter(getContext(), getAmbassadorCampus());
        ambassadorRecycle.setAdapter(ambassadorAdapter);
        ambassadorRecycle.setLayoutManager(ambassadorManager);

        MultiSnapRecyclerView eventNewsRecycle = view.findViewById(R.id.recycler_view_event);
        LinearLayoutManager newsManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        CampusNewAdapter newsAdapter = new CampusNewAdapter(getContext(), getNewCampus());
        eventNewsRecycle.setAdapter(newsAdapter);
        eventNewsRecycle.setLayoutManager(newsManager);


        MultiSnapRecyclerView tipsAndTricsRecycle = view.findViewById(R.id.recycler_view_tips_and_trics);
        LinearLayoutManager tipsAndTricksManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        TipsAndTricksAdapter tipsAndTricksAdapter = new TipsAndTricksAdapter(getContext(), getTipsAndTricks());
        tipsAndTricsRecycle.setAdapter(tipsAndTricksAdapter);
        tipsAndTricsRecycle.setLayoutManager(tipsAndTricksManager);

        searchCard.setOnClickListener( v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });


    }

    private List<DepartmentModel> getDepartment(){
        List<DepartmentModel> models = new ArrayList<>();
        models.add(new DepartmentModel("foto", "Ilmu Komputer", 1));
        models.add(new DepartmentModel("foto", "Elektronika Instrumentasi", 2));
        models.add(new DepartmentModel("foto", "Teknologi Informasi", 2));
        models.add(new DepartmentModel("foto", "Ilmu Pangan", 2));
        models.add(new DepartmentModel("foto", "Ilmu Sosial", 1));
        models.add(new DepartmentModel("foto", "Olah Raga", 2));
        models.add(new DepartmentModel("foto", "Ilmu Hama", 2));
        models.add(new DepartmentModel("foto", "Peternakan", 2));
        return models;
    }


    private List<CampusNewsModel> getNewCampus(){
        List<CampusNewsModel> models = new ArrayList<>();
        models.add(new CampusNewsModel("foto", "Ilmu Komputer","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", 1));
        models.add(new CampusNewsModel("foto", "Elektronika Instrumentasi","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.", 2));
        models.add(new CampusNewsModel("foto", "Teknologi Informasi","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 2));
        models.add(new CampusNewsModel("foto", "Ilmu Pangan","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ", 2));
        models.add(new CampusNewsModel("foto", "Ilmu Sosial","lala lia la", 1));

        return models;
    }

    private List<TipsAndtricksModel> getTipsAndTricks(){
        List<TipsAndtricksModel> models = new ArrayList<>();
        models.add(new TipsAndtricksModel("foto", "Ilmu Komputer","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", 1));
        models.add(new TipsAndtricksModel("foto", "Elektronika Instrumentasi","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.", 2));
        models.add(new TipsAndtricksModel("foto", "Teknologi Informasi","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 2));
        return models;
    }

    private List<DepartmentModel> getAmbassadorCampus(){
        List<DepartmentModel> models = new ArrayList<>();
        models.add(new DepartmentModel("foto", "Nikita Willy", 1));
        models.add(new DepartmentModel("foto", "Anjasmara", 2));
        models.add(new DepartmentModel("foto", "Siti Mariyam", 2));
        models.add(new DepartmentModel("foto", "Restu", 2));
        models.add(new DepartmentModel("foto", "Ridho Ilahi", 1));
        models.add(new DepartmentModel("foto", "Sekar Indah", 2));
        models.add(new DepartmentModel("foto", "Bumi Pertiwi", 2));
        models.add(new DepartmentModel("foto", "Komar", 2));
        return models;
    }

    private List<UniversityModel> getUniversity(){
        List<UniversityModel> university = new ArrayList<>();
        String description = "Universitas ini adalah universitas yang sayang bagus sekali, tapi sampai bagusnya saya sampai tidak tahu harus berkata apa" +
                "semoga kedepannya";
        university.add(new UniversityModel("foto", "Universitas Gadjah Mada", 1, description));
        university.add(new UniversityModel("foto", "Universitas Indonesia", 2,description));
        university.add(new UniversityModel("foto", "Universitas Padjadjaran", 2,description));
        university.add(new UniversityModel("foto", "Universitas Sebelas Maret", 2,description));
        university.add(new UniversityModel("foto", "Universitas Palembang", 2,description));
        university.add(new UniversityModel("foto", "Universitas Negri Cirebon", 2,description));
        university.add(new UniversityModel("foto", "Universitas Negri Semarang", 2,description));

        return university;
    }

}
