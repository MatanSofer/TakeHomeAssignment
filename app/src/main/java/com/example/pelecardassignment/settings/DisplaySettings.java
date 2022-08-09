package com.example.pelecardassignment.settings;

public class DisplaySettings {
    private static DisplaySettings single_instance = null;
    private Boolean allowPayments, allowCurrency ,allowSignature;

    private DisplaySettings()
    {
        allowPayments = true;
        allowCurrency = true;
        allowSignature = true;
    }

    public void setPaymentsAllow(Boolean bool){ allowPayments = bool;}
    public void setCurrencyAllow(Boolean bool){ allowCurrency = bool;}
    public void setSignatureAllow(Boolean bool){ allowSignature = bool;}
    public void resetSettings(){single_instance = null;}

    public Boolean getPaymentsAllow(){return allowPayments;}
    public Boolean getCurrencyAllow(){return allowCurrency ;}
    public Boolean getSignatureAllow(){return allowSignature ;}


    public static DisplaySettings getInstance()
    {
        if (single_instance == null)
            single_instance = new DisplaySettings();
        return single_instance;
    }
}
