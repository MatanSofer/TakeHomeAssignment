package com.example.pelecardassignment.model;

import android.graphics.Bitmap;

import com.example.pelecardassignment.settings.DisplaySettings;

public class Receipt {
    private static Receipt single_instance = null;
    private String amount, currency ,payments;
    private Bitmap signature;

    private Receipt()
    {
        amount = "";
        currency = "";
        payments = "";
    }

    public void setPayments(String newPayments){ payments = newPayments;}
    public void setCurrency(String newCurrency){ currency = newCurrency;}
    public void setAmount(String newAmount){ amount = newAmount;}
    public void setSignature(Bitmap newSignature){ signature = newSignature;}
    public void resetReceipt(){single_instance = null;}

    public String getPayments(){return payments;}
    public String getCurrency(){return currency;}
    public String getAmount(){return amount;}
    public Bitmap getSignature(){return signature;}

    public static Receipt getInstance()
    {
        if (single_instance == null)
            single_instance = new Receipt();
        return single_instance;
    }


}
