package com.example.pelecardassignment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pelecardassignment.model.Receipt;
import com.github.gcacace.signaturepad.views.SignaturePad;


public class SignatureScreenFragment extends Fragment {

    View view;
    Receipt receiptDetails;
    Button submitBtn, cancelBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        receiptDetails = Receipt.getInstance();
        view = inflater.inflate(R.layout.fragment_signature_screen, container, false);

        SignaturePad signaturePad = view.findViewById(R.id.signature_pad);

        cancelBtn = view.findViewById(R.id.signature_screen_cancel_btn);
        cancelBtn.setOnClickListener((View view) -> signaturePad.clear());

        submitBtn = view.findViewById(R.id.signature_screen_submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = signaturePad.getSignatureBitmap();
                receiptDetails.setSignature(bitmap);
                signaturePad.clear();

                Navigation.findNavController(view).navigate(R.id.action_signatureScreenFragment_to_receiptScreenFragment);
            }
        });

        return view;
    }
}