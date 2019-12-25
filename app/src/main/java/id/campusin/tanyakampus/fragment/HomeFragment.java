package id.campusin.tanyakampus.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.activities.PrivateMessageActivity;
import id.campusin.tanyakampus.activities.SearchActivity;
import id.campusin.tanyakampus.adapter.CampusNewAdapter;
import id.campusin.tanyakampus.adapter.DepartmentAdapter;
import id.campusin.tanyakampus.adapter.TipsAndTricksAdapter;
import id.campusin.tanyakampus.adapter.UniversityAdapter;
import id.campusin.tanyakampus.helper.ApiInterfaceService;
import id.campusin.tanyakampus.helper.RetrofitUtils;
import id.campusin.tanyakampus.model.CampusNewsModel;
import id.campusin.tanyakampus.model.DepartmentModel;
import id.campusin.tanyakampus.model.TipsAndtricksModel;
import id.campusin.tanyakampus.model.response.DataUniversityResponse;
import id.campusin.tanyakampus.model.response.UniversityModelResponse;
import id.campusin.tanyakampus.utils.managers.SessionManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private CardView searchCard;
    private ImageView imageViewSearch;
    private ApiInterfaceService apiInterfaceService;
    private SessionManager session;
    private List<DataUniversityResponse> dataUniversityResponses;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        searchCard = view.findViewById(R.id.cardView_search);
        imageViewSearch = view.findViewById(R.id.Image_view_message);
        apiInterfaceService = RetrofitUtils.apiService();
        session = new SessionManager(getContext());
        getUniversity(view);

        MultiSnapRecyclerView departmentRecyclerView = view.findViewById(R.id.recycler_view_department);
        LinearLayoutManager departmentManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        DepartmentAdapter departmentAdapter = new DepartmentAdapter(getContext(), getDepartment());
        departmentRecyclerView.setLayoutManager(departmentManager);
        departmentRecyclerView.setAdapter(departmentAdapter);



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

        imageViewSearch.setOnClickListener( v-> {
            Intent intent = new Intent(getContext(), PrivateMessageActivity.class);
            startActivity(intent);
        });

    }

    private List<DepartmentModel> getDepartment(){
        List<DepartmentModel> models = new ArrayList<>();
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_ilmu_komputer.jpeg?alt=media&token=67691bdc-0f89-478f-9d32-75e277dfe012", "Ilmu Komputer", 1));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_elins.jpeg?alt=media&token=a5dd9e3b-cac7-4fd7-9160-ebe0168548cc", "Elektronika Instrumentasi", 2));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_ilmu_pangan.jpeg?alt=media&token=04e788a5-c2bd-42a4-841b-83bd36c7e5e5", "Ilmu Pangan", 2));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_ilmu_social.jpeg?alt=media&token=acbf77a3-68bd-4e1c-a919-a54331e33ecd", "Ilmu Sosial", 1));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_ilmu_komputer.jpeg?alt=media&token=67691bdc-0f89-478f-9d32-75e277dfe012", "Ilmu Komputer", 1));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_elins.jpeg?alt=media&token=a5dd9e3b-cac7-4fd7-9160-ebe0168548cc", "Elektronika Instrumentasi", 2));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_ilmu_pangan.jpeg?alt=media&token=04e788a5-c2bd-42a4-841b-83bd36c7e5e5", "Ilmu Pangan", 2));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/department_flayer%2Fgambar_ilmu_social.jpeg?alt=media&token=acbf77a3-68bd-4e1c-a919-a54331e33ecd", "Ilmu Sosial", 1));
        return models;
    }


    private List<CampusNewsModel> getNewCampus(){
        List<CampusNewsModel> models = new ArrayList<>();
        models.add(new CampusNewsModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/campus_logo%2Flogo_unnes.png?alt=media&token=910f9e64-e021-4367-ac79-13c90d2e36ec", "Ilmu Komputer","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", 1));
        models.add(new CampusNewsModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/campus_logo%2Flogo_ugm.png?alt=media&token=44393b09-25fd-44c1-b142-f6b76c3022d6", "Elektronika Instrumentasi","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.", 2));
        models.add(new CampusNewsModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/campus_logo%2Flogo_ui.png?alt=media&token=6bc3d198-fafb-49a2-b727-8b1f1c282a79", "Teknologi Informasi","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 2));
        models.add(new CampusNewsModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/campus_logo%2Flogo_unnes.png?alt=media&token=910f9e64-e021-4367-ac79-13c90d2e36ec", "Ilmu Pangan","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ", 2));

        return models;
    }

    private List<TipsAndtricksModel> getTipsAndTricks(){
        List<TipsAndtricksModel> models = new ArrayList<>();
        models.add(new TipsAndtricksModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/tips%2Ftips%20kuliah.png?alt=media&token=b7e13014-2675-42fb-a16e-79817df87d04", "Ilmu Komputer","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", 1));
        models.add(new TipsAndtricksModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/tips%2Ftips%20snmptn.png?alt=media&token=9540eb57-116e-4eca-bf83-c85ae4e4be41", "Elektronika Instrumentasi","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.", 2));
        models.add(new TipsAndtricksModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/tips%2Ftips%20usbn.png?alt=media&token=50ecdb8d-6ae7-4171-96b8-6634413bdcaa", "Teknologi Informasi","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 2));
        return models;
    }

    private List<DepartmentModel> getAmbassadorCampus(){
        List<DepartmentModel> models = new ArrayList<>();
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/ambassador_flayer%2Fgambar_restu.jpeg?alt=media&token=5390fd0b-dd44-403b-9907-481ca2d4fb73", "Nikita Willy", 1));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/ambassador_flayer%2Fgambar_ridho.jpeg?alt=media&token=6a07ab86-1692-4fe6-93e7-b01b7c477e54", "Anjasmara", 2));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/ambassador_flayer%2Fgambar_robi.jpeg?alt=media&token=be15c2e2-db21-4849-97c4-c5c5ec2e5de3", "Siti Mariyam", 2));
        models.add(new DepartmentModel("https://firebasestorage.googleapis.com/v0/b/tanyakampus-38f8a.appspot.com/o/ambassador_flayer%2Fgambar_siti.jpeg?alt=media&token=4cba75d3-6aef-4ed8-a115-c6af4e6ba819", "Restu", 2));
        return models;
    }

    private void handleResults(List<DataUniversityResponse> response, View view){
        UniversityAdapter firstAdapter = new UniversityAdapter(getContext(), response);
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.recycler_view_university);
        LinearLayoutManager firstManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);
    }

    private void handleError(Throwable t) {
        System.out.println("handleError disini ");
    }

    private void getUniversity(View view){
        Observable<UniversityModelResponse> response = apiInterfaceService.universityListRequestObservable(session.getToken());
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UniversityModelResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UniversityModelResponse response) {
                        handleResults(null, view);
                        dataUniversityResponses = response.getData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onComplete() {
                        handleResults(dataUniversityResponses, view);
                    }
                });
    }

}
