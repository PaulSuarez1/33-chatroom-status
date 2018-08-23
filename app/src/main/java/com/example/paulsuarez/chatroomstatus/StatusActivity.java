package com.example.paulsuarez.chatroomstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusActivity extends AppCompatActivity {
    @BindView(R.id.setStatus)
    EditText mEditText;

    @BindView(R.id.list)
    RecyclerView list;
    private LinearLayoutManager linearLayoutManager;
    private AdapterStatus adapterStatus;

    private List<ConstructorStatus> allStatuses;

    FirebaseDatabase mDatabase;
    DatabaseReference mUsers;

    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status2);

        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance();
        mUsers = mDatabase.getReferences("users");

        attachListeners();
        initializeUsername();

        allStatuses = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        adapterStatus = new AdapterStatus(allStatuses);

        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapterStatus);

    }

    private void initializeUsername() {
        Intent data = getIntent();
        mUsername = data.getStringExtra("username");

        mUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(mUsername)) {
                    DatabaseReference userRef = mUsers.child(mUsername);
                    userRef.child("status").setValue("online");
                    userRef.child("statusText").setValue("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void attachListeners() {
        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ConstructorStatus> statuses = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String username = child.getKey();
                    String status = child.child("status").getValue(String.class);
                    String statusText = child.child("statusText").getValue(String.class);

                    ConstructorStatus userStatus = new ConstructorStatus(username, status, statusText);
                    statuses.add(userStatus);
                }

                adapterStatus.replaceList(statuses);
                adapterStatus.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.setOnline)
    public void setOnline() {
        setStatus("online");
    }

    @OnClick(R.id.setAway)
    public void setAway() {
        setStatus("away");
    }

    @OnClick(R.id.setOffline)
    public void setOffline() {
        setStatus("offline");
    }

    public void setStatus(String status) {
        String username = mUsername;
        String statusText = mEditText.getText().toString();

        DatabaseReference user = mUsers.child(username);
        user.child("status").setValue(status);
        user.child("statusText").setValue(statusText);
    }




}
