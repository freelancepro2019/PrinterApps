package com.dantsu.app_printer.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.dantsu.app_printer.R;
import com.dantsu.app_printer.databinding.ActivityPrintBinding;
import com.dantsu.app_printer.databinding.ActivitySettingsBinding;
import com.dantsu.app_printer.models.DefaultSettings;
import com.dantsu.app_printer.models.OperationModel;
import com.dantsu.app_printer.preferences.Preferences;
import com.dantsu.app_printer.share.Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PrintActivity extends AppCompatActivity {
    private ActivityPrintBinding binding;
    private DefaultSettings model;
    private OperationModel operationModel;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_print);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        operationModel = (OperationModel) intent.getSerializableExtra("data");
    }

    private void initView() {
        preferences = Preferences.getInstance();
        model = preferences.getAppSetting(this);
        binding.setModel(model);
        binding.setModel2(operationModel);
        Calendar calendar = Calendar.getInstance();
        String date = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa", Locale.ENGLISH).format(calendar.getTime());
        binding.setDate(date);
        binding.btnPrint.setOnClickListener(view -> {
            Bitmap bitmap = getBitmap(binding.scrollView);


        });
    }

    private static Bitmap getBitmap (View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

}