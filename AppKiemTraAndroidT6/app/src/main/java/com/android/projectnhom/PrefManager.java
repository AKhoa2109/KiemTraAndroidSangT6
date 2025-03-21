package com.android.projectnhom;

import android.content.Context;
import android.content.SharedPreferences;

/*Nguyễn Hoàng Anh Khoa - 22110352*/
public class PrefManager {
    Context context;
    public PrefManager(Context context){
        this.context = context;
    }

    public void saveLoginDetails(String email, String password, int userId){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email",email);
        editor.putString("Password",password);
        editor.putInt("userId", userId);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }
    public void logout(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply(); // hoặc editor.commit();
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email","");
    }
    public String getPassword() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }

    public int getUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId", 0);
    }

    public boolean isUserLogOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        return !isLoggedIn;
    }
}
