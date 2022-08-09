package com.example.pelecardassignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.pelecardassignment.settings.DisplaySettings;


public class SettingsScreenFragment extends Fragment {

    View view;
    Switch paymentSwitch, currencySwitch, signatureSwitch;
    DisplaySettings viewSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewSettings = DisplaySettings.getInstance();
        view = inflater.inflate(R.layout.fragment_settings_screen, container, false);


        paymentSwitch = view.findViewById(R.id.settings_payment_switch);
        paymentSwitch.setChecked(viewSettings.getPaymentsAllow());
        paymentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewSettings.setPaymentsAllow(paymentSwitch.isChecked());
            }
        });

        currencySwitch = view.findViewById(R.id.settings_currency_switch);
        currencySwitch.setChecked(viewSettings.getCurrencyAllow());
        currencySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewSettings.setCurrencyAllow(currencySwitch.isChecked());
            }
        });

        signatureSwitch = view.findViewById(R.id.settings_signature_switch);
        signatureSwitch.setChecked(viewSettings.getSignatureAllow());
        signatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewSettings.setSignatureAllow(signatureSwitch.isChecked());
            }
        });


        return view;
    }


}