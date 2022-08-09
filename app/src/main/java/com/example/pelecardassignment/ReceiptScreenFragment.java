package com.example.pelecardassignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pelecardassignment.model.Receipt;
import com.example.pelecardassignment.settings.DisplaySettings;


public class ReceiptScreenFragment extends Fragment {

    View view;
    DisplaySettings viewSettings;
    Receipt receiptDetails;
    Button finishBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewSettings = DisplaySettings.getInstance();
        receiptDetails = Receipt.getInstance();
        view = inflater.inflate(R.layout.fragment_receipt_screen, container, false);

        displayView();
        checkDisplay();

        return view;
    }



    private void displayView(){
        finishBtn = view.findViewById(R.id.receipt_screen_finish_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSettings.resetSettings();
                receiptDetails.resetReceipt();
                Toast.makeText(getActivity(), "Your reciept complete :), try another one ", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_receiptScreenFragment_to_mainScreenFragment);
            }
        });

    }
    private void checkDisplay() {
        TextView amountTextView = view.findViewById(R.id.receipt_amount_text_view);
        amountTextView.setText(receiptDetails.getAmount());

        TextView paymentTextView = view.findViewById(R.id.receipt_payments_text_view);
        TextView paymentTextView2 = view.findViewById(R.id.receipt_payments_text_view2);
        if (!viewSettings.getPaymentsAllow()) {
            paymentTextView.setVisibility(View.INVISIBLE);
            paymentTextView2.setVisibility(View.INVISIBLE);
        } else {
            paymentTextView2.setText(receiptDetails.getPayments());
        }

        TextView currencyTextView = view.findViewById(R.id.receipt_currency_text_view);
        TextView currencyTextView2 = view.findViewById(R.id.receipt_currency_text_view2);
        if (!viewSettings.getCurrencyAllow()) {
            currencyTextView.setVisibility(View.INVISIBLE);
            currencyTextView2.setVisibility(View.INVISIBLE);
        } else {
            currencyTextView2.setText(receiptDetails.getCurrency());
        }

        TextView receiptTextView = view.findViewById(R.id.receipt_signature_text_view);
        ImageView signatureImageView = view.findViewById(R.id.receipt_signature_image_view);
        if (!viewSettings.getSignatureAllow()) {
            receiptTextView.setVisibility(View.INVISIBLE);
            signatureImageView.setVisibility(View.INVISIBLE);
        } else {
            signatureImageView.setImageBitmap(receiptDetails.getSignature());
        }
    }
}