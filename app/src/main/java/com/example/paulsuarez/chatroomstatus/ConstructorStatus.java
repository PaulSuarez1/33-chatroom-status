package com.example.paulsuarez.chatroomstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConstructorStatus {

    public String username;
    public String status;
    public String statusText;

    public ConstructorStatus(String username, String status, String statusText) {
        this.username = username;
        this.status = status;
        this.statusText = statusText;
}
