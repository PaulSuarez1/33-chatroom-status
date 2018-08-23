package com.example.paulsuarez.chatroomstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.signin)
    public void signin() {
        String username = mUsername.getText().toString();

        Intent intent = new Intent(this, StatusActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
