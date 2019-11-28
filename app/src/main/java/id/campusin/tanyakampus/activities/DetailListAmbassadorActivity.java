package id.campusin.tanyakampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import id.campusin.tanyakampus.R;

public class DetailListAmbassadorActivity extends AppCompatActivity {


    private Button buttonMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_ambassador);

        buttonMessage = findViewById(R.id.Button_message_ambassador);

        buttonMessage.setOnClickListener(v->{
            Intent sendMessage = new Intent(getApplicationContext(), PrivateMessageActivity.class);
            startActivity(sendMessage);
        });
    }
}
