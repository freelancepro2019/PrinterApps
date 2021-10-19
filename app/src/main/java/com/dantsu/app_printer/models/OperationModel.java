package com.dantsu.app_printer.models;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.dantsu.app_printer.BR;
import com.dantsu.app_printer.R;

import java.io.Serializable;

public class OperationModel extends BaseObservable implements Serializable {
    private String total;
    private String vat_value;
    private String vat_percentage;
    private String total_payment;

    public ObservableField<String> error_total = new ObservableField<>();

    public OperationModel() {
        total="";
        vat_value="";
        total_payment="0.0";
        vat_percentage = "0";
    }

    public boolean isDataValid(Context context){
        if (!total.isEmpty()){
            error_total.set(null);
            return true;
        }else {
            error_total.set(context.getString(R.string.field_req));

            return false;
        }
    }

    @Bindable
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
        notifyPropertyChanged(BR.total);
    }

    @Bindable
    public String getVat_value() {
        return vat_value;
    }

    public void setVat_value(String vat_value) {
        this.vat_value = vat_value;
        notifyPropertyChanged(BR.vat_value);
    }

    @Bindable
    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
        notifyPropertyChanged(BR.total_payment);
    }

    public String getVat_percentage() {
        return vat_percentage;
    }

    public void setVat_percentage(String vat_percentage) {
        this.vat_percentage = vat_percentage;
    }

    public String calculate(){
        double vatValue = (Double.parseDouble(vat_percentage)/100)*Double.parseDouble(this.total);
        double totalPayment = Double.parseDouble(this.total)-vatValue;
        setVat_value(String.valueOf(vatValue));
        setTotal_payment(String.valueOf(totalPayment));

        return total_payment;
    }
}
