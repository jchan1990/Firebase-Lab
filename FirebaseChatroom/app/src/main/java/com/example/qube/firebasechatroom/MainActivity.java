package com.example.qube.firebasechatroom;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextMessage;
    private Button mButtonSend, mButtonChangeName, mButtonChangeNameColor, mButtonChangeChatroom;
    private ListView mChatList;
    private DatabaseReference mDatabaseRef;
    private FirebaseListAdapter<Messages> firebaseListAdapter;

    public static final String SHARED_PREFERENCES = "firebase_chat_shared_preferences";
    public static final String PREFERENCES_USER_NAME = "user_name";
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextMessage = (EditText) findViewById(R.id.editText_message);
        mButtonSend = (Button) findViewById(R.id.button_send);
        mChatList = (ListView) findViewById(R.id.listView_chatlog);

        mButtonChangeName = (Button) findViewById(R.id.button_change_user_name);
        mButtonChangeNameColor = (Button) findViewById(R.id.button_set_color_user);
        mButtonChangeChatroom = (Button) findViewById(R.id.button_chatroom);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Messages");

        mSharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        if (mSharedPreferences.getString(PREFERENCES_USER_NAME, null) == null) {
            Random random = new Random();
            int rng = random.nextInt();
            String newUserName = "User" + rng;
            mSharedPreferences.edit().putString(PREFERENCES_USER_NAME, newUserName).commit();
        }

        firebaseListAdapter = new FirebaseListAdapter<Messages>(this, Messages.class,
                android.R.layout.simple_list_item_2, mDatabaseRef) {
            @Override
            protected void populateView(View v, Messages model, int position) {
                TextView userName = (TextView) v.findViewById(android.R.id.text1);
                TextView message = (TextView) v.findViewById(android.R.id.text2);

                userName.setText(model.getmUserName());
                message.setText(model.getmMessage());
            }
        };
        mChatList.setAdapter(firebaseListAdapter);

        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextMessage.getText().toString().trim().length() <= 0) {
                    mEditTextMessage.setError("Type something preferably something besides spaces");
                } else {
                    Messages message = new Messages(mSharedPreferences.getString(PREFERENCES_USER_NAME,
                            "No Message"), mEditTextMessage.getText().toString().trim());
                    mDatabaseRef.push().setValue(message);
                    mEditTextMessage.setText("");
                }
            }
        });

        mButtonChangeNameColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorChangeDialog colorChangeDialog = new ColorChangeDialog();
                colorChangeDialog.displayColorChangeDialog(view.getContext());
            }
        });
    }
}
