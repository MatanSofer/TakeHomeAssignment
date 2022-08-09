package com.example.pelecardassignment;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.pelecardassignment.model.Receipt;
import com.example.pelecardassignment.settings.DisplaySettings;

public class MainScreenFragment extends Fragment {

    View view;
    DisplaySettings viewSettings;
    Receipt receiptDetails;
    EditText amountEditText;
    Switch paymentSwitch, signatureSwitch;
    ToggleButton ILSToggle, USDToggle;
    Spinner paymentsNumberSpinner;
    ArrayAdapter<CharSequence> adapter;
    ImageButton settingBtn;
    Button submitBtn, exitBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewSettings = DisplaySettings.getInstance();
        receiptDetails = Receipt.getInstance();
        view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        displayView();
        checkDisplay();

        return view;
    }



    CompoundButton.OnCheckedChangeListener currencyChangeChecker = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (buttonView == USDToggle) {
                    ILSToggle.setChecked(false);
                }
                if (buttonView == ILSToggle) {
                    USDToggle.setChecked(false);
                }
            }
        }
    };

    private void displayView() {
        submitBtn = view.findViewById(R.id.main_screen_submit_btn);
        submitBtn.setOnClickListener((View view) -> onSubmit());

        exitBtn = view.findViewById(R.id.main_screen_exit_btn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        amountEditText = view.findViewById(R.id.amount_edit_text);

        settingBtn = view.findViewById(R.id.settings_image_button);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_settingsScreenFragment);
            }
        });

        paymentsNumberSpinner = view.findViewById(R.id.payment_spinner);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.paymentsNumber, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        paymentsNumberSpinner.setAdapter(adapter);


        paymentSwitch = view.findViewById(R.id.payment_switch);
        paymentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    paymentsNumberSpinner.setEnabled(false);
                    viewSettings.setPaymentsAllow(false);
                } else {
                    paymentsNumberSpinner.setEnabled(true);
                    viewSettings.setPaymentsAllow(true);
                }
            }
        });


        signatureSwitch = view.findViewById(R.id.signature_switch);
        signatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                viewSettings.setSignatureAllow(isChecked);

            }
        });


        USDToggle = view.findViewById(R.id.toggle_btn_USD);
        ILSToggle = view.findViewById(R.id.toggle_btn_ILS);

        USDToggle.setOnCheckedChangeListener(currencyChangeChecker);
        ILSToggle.setOnCheckedChangeListener(currencyChangeChecker);


    }

    private void checkDisplay() {
        if (!viewSettings.getPaymentsAllow()) {
            TextView paymentTextView = view.findViewById(R.id.payment_text_view);
            paymentTextView.setVisibility(View.INVISIBLE);
            paymentSwitch.setVisibility(View.INVISIBLE);
            paymentsNumberSpinner.setVisibility(View.INVISIBLE);
        }
        if (!viewSettings.getCurrencyAllow()) {
            TextView currencyTextView = view.findViewById(R.id.currency_text_view);
            currencyTextView.setVisibility(View.INVISIBLE);
            ILSToggle.setVisibility(View.INVISIBLE);
            USDToggle.setVisibility(View.INVISIBLE);
        }
        if (!viewSettings.getSignatureAllow()) {
            TextView signatureTextView = view.findViewById(R.id.signature_text_view);
            signatureTextView.setVisibility(View.INVISIBLE);
            signatureSwitch.setVisibility(View.INVISIBLE);
        }
    }


    private void onSubmit() {

        String amount = amountEditText.getText().toString();
        receiptDetails.setAmount(amount);

        if (paymentSwitch.isChecked()) {
            String payments = paymentsNumberSpinner.getSelectedItem().toString();
            receiptDetails.setPayments(payments);
        }

        if (USDToggle.isChecked()) {
            receiptDetails.setCurrency("USD");
        } else if (ILSToggle.isChecked()) {
            receiptDetails.setCurrency("ILS");
        }

        if (!TextUtils.isEmpty(amount) ) {
            if (viewSettings.getSignatureAllow() ) {
                Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_signatureScreenFragment);
            } else {
                Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_receiptScreenFragment);
            }
        } else {
            Toast.makeText(getActivity(), " Oops Something is missing , Try Again!", Toast.LENGTH_SHORT).show();
        }


    }

}