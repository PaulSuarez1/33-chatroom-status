package com.example.paulsuarez.chatroomstatus;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.MyStatusViewHolder> {

    private List<ConstructorStatus> mStatuses;

    public AdapterStatus(List<ConstructorStatus> allStatuses) {
        mStatuses = allStatuses;
    }

    @Override
    public MyStatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.status_item, parent, false);

        MyStatusViewHolder vh = new MyStatusViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyStatusViewHolder holder, int position) {
        ConstructorStatus status = mStatuses.get(position);
        holder.bind(status);
    }

    @Override
    public int getItemCount() {
        return mStatuses.size();
    }

    public void replaceList(List<ConstructorStatus> statuses) {
        mStatuses = statuses;
    }

    public class MyStatusViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ImageView icon;
        public TextView username;
        public TextView statusText;

        private ConstructorStatus mStatus;

        public MyStatusViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            this.icon = mView.findViewById(R.id.statusIcon);
            this.username = mView.findViewById(R.id.username);
            this.statusText = mView.findViewById(R.id.statusText);
        }

        public void bind(ConstructorStatus status) {
            mStatus = status;

            this.username.setText(status.username);
            this.statusText.setText(status.statusText);

            setIcon();
        }

        public void setIcon() {
            int imageId = R.drawable.red;
            if (mStatus.status.equals("online")) {
                imageId = R.drawable.green;
            } else if (mStatus.status.equals("away")) {
                imageId = R.drawable.yellow;
            } else if (mStatus.status.equals("offline")) {
                imageId = R.drawable.red;
            }

            Drawable drawable = mView.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);
        }
    }




}
