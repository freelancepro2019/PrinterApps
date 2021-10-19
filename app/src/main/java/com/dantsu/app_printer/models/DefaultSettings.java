package com.dantsu.app_printer.models;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.dantsu.app_printer.R;

import java.io.Serializable;

public class DefaultSettings extends BaseObservable implements Serializable {
    private String company_name;
    private String address;
    private String zip_code;
    private String vat_percentage;
    private String tell;
    public ObservableField<String> error_company_name = new ObservableField<>();
    public ObservableField<String> error_address = new ObservableField<>();
    public ObservableField<String> error_zip_code = new ObservableField<>();
    public ObservableField<String> error_vat_percent = new ObservableField<>();
    public ObservableField<String> error_tell = new ObservableField<>();

    public boolean isDataValid(Context context){
        if (!company_name.isEmpty()&&
                !address.isEmpty()&&
                !zip_code.isEmpty()&&
                !vat_percentage.isEmpty()&&
                !tell.isEmpty()
        ){
            error_company_name.set(null);
            error_address.set(null);
            error_zip_code.set(null);
            error_vat_percent.set(null);
            error_tell.set(null);

            return true;
        }else {

            if (company_name.isEmpty()){
                error_company_name.set(context.getString(R.string.field_req));
            }else {
                error_company_name.set(null);
            }

            if (address.isEmpty()){
                error_address.set(context.getString(R.string.field_req));
            }else {
                error_address.set(null);
            }


            if (zip_code.isEmpty()){
                error_zip_code.set(context.getString(R.string.field_req));
            }else {
                error_zip_code.set(null);
            }

            if (vat_percentage.isEmpty()){
                error_vat_percent.set(context.getString(R.string.field_req));
            }else {
                error_vat_percent.set(null);
            }
            if (tell.isEmpty()){
                error_tell.set(context.getString(R.string.field_req));
            }else {
                error_tell.set(null);
            }
            return false;
        }
    }
    public DefaultSettings() {
        company_name="";
        address="";
        zip_code="";
        vat_percentage="0";
        tell="";
    }

    @Bindable
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.company_name);
    }


    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.address);
    }

    @Bindable
    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.zip_code);

    }


    @Bindable
    public String getVat_percentage() {
        return vat_percentage;
    }

    public void setVat_percentage(String vat_percentage) {
        this.vat_percentage = vat_percentage;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.vat_percentage);

    }

    @Bindable
    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
        notifyPropertyChanged(BR.tell);

    }
}
