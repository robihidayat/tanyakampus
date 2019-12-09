package id.campusin.tanyakampus.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import id.campusin.tanyakampus.R;

public class DetailListAmbassadorActivity extends AppCompatActivity {


    private Button buttonMessage;

    BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_ambassador);

//        buttonMessage = findViewById(R.id.Button_message_ambassador);
//
//        buttonMessage.setOnClickListener(v->{
//            Intent sendMessage = new Intent(getApplicationContext(), PrivateMessageActivity.class);
//            startActivity(sendMessage);
//        });
    }
}
