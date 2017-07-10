package com.example.admin.miwokapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
    }
    private String mVersionName;

    private String mVersionNumber;

    private int mImageResourceid;

    public ListItem (String vName,String vNumber,int imageResourceid){
        mVersionName = vName;
        mVersionNumber = vNumber;
        mImageResourceid = imageResourceid;
    }
    public  String getVersionName (){
        return mVersionName;
    }
    public  String getVersionNumber (){
        return  mVersionNumber;
    }

    public int getmImageResourceid() {
        return mImageResourceid;
    }
}
