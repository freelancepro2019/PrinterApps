package com.dantsu.app_printer.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.dantsu.app_printer.R;
import com.dantsu.app_printer.databinding.ActivitySettingsBinding;
import com.dantsu.app_printer.models.DefaultSettings;
import com.dantsu.app_printer.preferences.Preferences;
import com.dantsu.app_printer.share.Common;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    private DefaultSettings model;
    private Preferences preferences;
    private boolean isDataNull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_settings);
        initView();
    }

    private void initView() {
        preferences = Preferences.getInstance();
        model = preferences.getAppSetting(this);
        if (model==null){
            model = new DefaultSettings();
            isDataNull= true;

        }else {
            isDataNull = false;

        }
        binding.setModel(model);


        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        binding.btnSave.setOnClickListener(view -> {
            if (model.isDataValid(this)){
                Common.CloseKeyBoard(this,binding.edtName);
                preferences.createUpdateAppSetting(this,model);

                if (isDataNull){
                    navigateToMainActivity();
                }else {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}