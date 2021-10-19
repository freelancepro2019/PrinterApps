package com.dantsu.app_printer.uis;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dantsu.app_printer.R;
import com.dantsu.app_printer.databinding.ActivityMainBinding;
import com.dantsu.app_printer.models.OperationModel;
import com.dantsu.app_printer.preferences.Preferences;
import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.DeviceConnection;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection;
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections;
import com.dantsu.escposprinter.connection.tcp.TcpConnection;
import com.dantsu.escposprinter.connection.usb.UsbConnection;
import com.dantsu.escposprinter.connection.usb.UsbPrintersConnections;
import com.dantsu.escposprinter.exceptions.EscPosBarcodeException;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;
import com.dantsu.escposprinter.exceptions.EscPosEncodingException;
import com.dantsu.escposprinter.exceptions.EscPosParserException;
import com.dantsu.escposprinter.textparser.PrinterTextParserImg;
import com.dantsu.app_printer.async.AsyncBluetoothEscPosPrint;
import com.dantsu.app_printer.async.AsyncEscPosPrinter;
import com.dantsu.app_printer.async.AsyncTcpEscPosPrint;
import com.dantsu.app_printer.async.AsyncUsbEscPosPrint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Preferences preferences;
    private OperationModel model;
    private ActivityResultLauncher<Intent> launcher;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView() {
        preferences = Preferences.getInstance();
        if (preferences.getAppSetting(this)==null){
            navigateToSettingActivity();
        }
        model = new OperationModel();
        model.setVat_percentage(preferences.getAppSetting(this).getVat_percentage());
        binding.setModel(model);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode()==RESULT_OK){
                model.setVat_percentage(preferences.getAppSetting(MainActivity.this).getVat_percentage());
                binding.setModel(model);
                if (model.isDataValid(this)){
                    binding.btnPrint.setVisibility(View.VISIBLE);
                    model.calculate();
                }
            }
        });
        binding.btnCalculate.setOnClickListener(view -> {
            if (model.isDataValid(this)){
                binding.btnPrint.setVisibility(View.VISIBLE);
                model.calculate();
                //binding.tvResult.setText(model.getTotal_payment());

            }
        });

        binding.btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(this,SettingsActivity.class);
            launcher.launch(intent);
        });
        binding.btnPrint.setOnClickListener(view -> {
            Intent intent = new Intent(this,PrintActivity.class);
            intent.putExtra("data",model);
            startActivity(intent);
        });
    }

    private void navigateToSettingActivity() {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
        finish();
    }


    /*==============================================================================================
    ======================================BLUETOOTH PART============================================
    ==============================================================================================*/



}
