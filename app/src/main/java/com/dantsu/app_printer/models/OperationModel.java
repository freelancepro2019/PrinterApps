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
    private String vat;
    private String total_payment;

    public ObservableField<String> error_total = new ObservableField<>();
    public ObservableField<String> error_vat = new ObservableField<>();

    public OperationModel() {
        total="";
        vat="";
        total_payment="0.0";
    }

    public boolean isDataValid(Context context){
        if (!total.isEmpty()&&!vat.isEmpty()){
            error_total.set(null);
            error_vat.set(null);
            return true;
        }else {
            if (total.isEmpty()){
                error_total.set(context.getString(R.string.field_req));
            }else {
                error_total.set(null);

            }


            if (vat.isEmpty()){
                error_vat.set(context.getString(R.string.field_req));
            }else {
                error_vat.set(null);

            }
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
    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
        notifyPropertyChanged(BR.vat);
    }

    @Bindable
    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
        notifyPropertyChanged(BR.total_payment);
    }

    public String calculate(){
        double total = Double.parseDouble(this.total)+Double.parseDouble(this.vat);
        setTotal_payment(String.valueOf(total));
        return total_payment;
    }
}
