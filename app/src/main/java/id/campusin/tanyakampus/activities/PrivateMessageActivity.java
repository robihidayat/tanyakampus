package id.campusin.tanyakampus.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.adapter.MessageListAdapter;
import id.campusin.tanyakampus.model.User;
import id.campusin.tanyakampus.model.UserMessage;

public class PrivateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);
        MessageListAdapter messageListAdapter = new MessageListAdapter(getApplicationContext(),getMessageList() );
        MultiSnapRecyclerView firstRecyclerView = findViewById(R.id.reyclerview_message_list);
        LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(messageListAdapter);
    }

    private List<UserMessage> getMessageList() {
        List<UserMessage> messages = new ArrayList<>();
        User userRobi = new User();
        userRobi.setNickname("robi");
        userRobi.setProfileUrl("profile");
        userRobi.setUserId("1");
        UserMessage messageRobi = new UserMessage("Hello, this is message", userRobi, new Date().getTime());
        messages.add(messageRobi);

        User userPenerima = new User();
        userPenerima.setNickname("penerima");
        userPenerima.setProfileUrl("profile");
        userPenerima.setUserId("2");
        UserMessage messagePenerima = new UserMessage("Hello, this is message", userPenerima, new Date().getTime());
        messages.add(messagePenerima);

    return messages;
    }
}
