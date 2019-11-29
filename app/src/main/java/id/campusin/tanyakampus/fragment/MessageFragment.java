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
import java.util.Date;
import java.util.List;

import id.campusin.tanyakampus.R;
import id.campusin.tanyakampus.adapter.AmbassadorAdapter;
import id.campusin.tanyakampus.adapter.MessageListAdapter;
import id.campusin.tanyakampus.model.User;
import id.campusin.tanyakampus.model.UserMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MessageListAdapter messageListAdapter = new MessageListAdapter(getContext(),getMessageList() );
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.MultiSnapRecyclerView_message_inbox);
        LinearLayoutManager firstManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(messageListAdapter);
    }


    private List<UserMessage> getMessageList() {
        List<UserMessage> messages = new ArrayList<>();
        User userRobi = new User();
        userRobi.setNickname("robi");
        userRobi.setProfileUrl("profile");
        userRobi.setUserId("2");
        UserMessage messageRobi = new UserMessage("Hello, this is message", userRobi, new Date().getTime());
        messages.add(messageRobi);

        User userPenerima = new User();
        userPenerima.setNickname("penerima");
        userPenerima.setProfileUrl("profile");
        userPenerima.setUserId("3");
        UserMessage messagePenerima = new UserMessage("Hello, this is message", userPenerima, new Date().getTime());
        messages.add(messagePenerima);


        User ronni = new User();
        ronni.setNickname("ronni");
        ronni.setProfileUrl("profile");
        ronni.setUserId("3");
        UserMessage messageRonni = new UserMessage("Hello, this is message", ronni, new Date().getTime());
        messages.add(messageRonni);


        return messages;
    }

}
