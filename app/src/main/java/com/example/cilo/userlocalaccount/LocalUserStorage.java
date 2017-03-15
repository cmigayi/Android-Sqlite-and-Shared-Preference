package com.example.cilo.userlocalaccount;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by cilo on 3/14/17.
 */

public class LocalUserStorage {
    SharedPreferences store;
    static String STORAGE_NAME = "user";

    public LocalUserStorage(Context c){
        store = c.getSharedPreferences(STORAGE_NAME,0);
    }

    public void storeData(String username, String email,
                          String password){

        SharedPreferences.Editor editor = store.edit();
        editor.putString("username",username);
        editor.putString("email",email);
        editor.putString("pass",password);
        editor.commit();
    }

    public ArrayList<String> getData(){

        String uname = store.getString("username","");
        String email = store.getString("email","");
        String pass = store.getString("pass","");

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(uname);
        arrayList.add(email);
        arrayList.add(pass);

        return arrayList;
    }

    public void setLoggedIn(boolean loggedIn){
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("status",loggedIn);
        editor.commit();
    }

    public boolean getLoggedInStatus(){
        boolean status = store.getBoolean("status",false);
        return status;
    }

    public void clearData(){
        SharedPreferences.Editor editor = store.edit();
        editor.clear();
        editor.commit();
    }

}





















