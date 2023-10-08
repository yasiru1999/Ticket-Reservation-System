package com.example.ticketreservation.managers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.util.Consumer;

import com.example.ticketreservation.models.logins.LoginRequestBody;
import com.example.ticketreservation.models.logins.LoginResponse;
import com.example.ticketreservation.models.logins.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    private static LoginManager singleton;
    private LoginService loginService;
    private final String loginStateFile = "loginstate";
    private final String isLoggedInKey = "logged_in";

    public static LoginManager getInstance() {
        if (singleton == null)
            singleton = new LoginManager();

        return singleton;
    }

    private LoginManager() {
        loginService = NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateCredentials(String nic, String password) {
        if (nic == null || nic.length() == 0)
            return false;

        if (password == null || password.length() == 0)
            return false;

        return true;
    }

    public void login(
            String nic,
            String password,
            Runnable onSuccess,
            Consumer<String> onError
    ){
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connectivity");
            return;
        }
        LoginRequestBody body = new LoginRequestBody(nic, password);
        loginService.login(body)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.body().success){
                            onSuccess.run();
                        }
                        else {
                            onError.accept("NIC or Password is incorrect");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        onError.accept("Unknown error occurred while logging in");
                    }
                });
    }
    public void setLoggedInState(boolean isLoggedIn){
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putBoolean(isLoggedInKey, isLoggedIn);
        editor.apply();
    }

    public boolean getIsLoggedIn(){
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);
        return prefs.getBoolean(isLoggedInKey, false);
    }
}
