package com.dantsu.app_printer.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.dantsu.app_printer.models.DefaultSettings;
import com.google.gson.Gson;

public class Preferences {

    private static Preferences instance = null;

    private Preferences() {
    }

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }





    public void createUpdateAppSetting(Context context, DefaultSettings settings) {
        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String data = gson.toJson(settings);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("settings", data);
        editor.apply();
    }

    public DefaultSettings getAppSetting(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        DefaultSettings settings = gson.fromJson(preferences.getString("settings", ""), DefaultSettings.class);

        return settings;
    }





}
