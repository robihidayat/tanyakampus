package id.campusin.tanyakampus.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.model.UniversityModel;

public class DetailUniversityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainTheme);
        setContentView(R.layout.activity_detail_university);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        TextView title = findViewById(R.id.TextView_bottomSheet_title_univ);
        TextView address = findViewById(R.id.textView_bottomSheet_address_univ);
        TextView content = findViewById(R.id.TextView_Bottomsheet_content);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(googleMap -> {
            LatLng sydney = new LatLng(37.4233438, -122.0728817);
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
        });

        UniversityModel universityModel = bundle.getParcelable("universityModel");
        if (universityModel != null) {
//            String poster = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQr6-IcoaRHEXBo9LDgZ18uE9digJnQ6ytuJ-a0Gjh89tbhjwiX" ; //+ movieList.get(i).getPosterPath()
//            Glide.with(getApplicationContext())
//                    .load(poster)
//                    .placeholder(R.drawable.load)
//                    .into(imageTitle);
            title.setText(universityModel.getTitle());
            address.setText(universityModel.getAddress());
            content.setText(universityModel.getDescription());
        }

    }
}
