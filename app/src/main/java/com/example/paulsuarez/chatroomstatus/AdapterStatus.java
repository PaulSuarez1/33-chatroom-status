package com.example.paulsuarez.chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.MyStatusViewHolder> {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_status);
    }
}
