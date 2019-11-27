package id.campusin.tanyakampus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.UniversityModel;

public class DetailUniversityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_university);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        ImageView imageTitle = findViewById(R.id.thumbnail_university_details);
        TextView title = findViewById(R.id.textView_detail_university_title);
        TextView detailText = findViewById(R.id.textView_detail_university_details);

        UniversityModel universityModel = bundle.getParcelable("universityModel");
        if (universityModel!=null) {
            String poster = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQr6-IcoaRHEXBo9LDgZ18uE9digJnQ6ytuJ-a0Gjh89tbhjwiX" ; //+ movieList.get(i).getPosterPath()
            Glide.with(getApplicationContext())
                    .load(poster)
                    .placeholder(R.drawable.load)
                    .into(imageTitle);
            title.setText(universityModel.getTitle());
            detailText.setText(universityModel.getDescription());
        }

    }
}
