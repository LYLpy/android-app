package net.getlearn.getlearn_android;

import android.content.Context;
import android.content.Intent;

import net.getlearn.getlearn_android.activity.GetVerificationCodeActivity;

public class IntentsUtlis {
    //登录页面
    public static void starSignInActivity(Context activity){
        Intent intent = new Intent(activity, GetVerificationCodeActivity.class);
        activity.startActivity(intent);
    }
}
