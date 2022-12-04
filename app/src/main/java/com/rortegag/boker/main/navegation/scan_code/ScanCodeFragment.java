package com.rortegag.boker.main.navegation.scan_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.rortegag.boker.R;

public class ScanCodeFragment extends Fragment {

    private Button btnEscanear;
    private TextView txtBarCode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scan_code, container, false);

        super.onCreate(savedInstanceState);
        btnEscanear = root.findViewById(R.id.btnScan);
        txtBarCode = root.findViewById(R.id.txtBarcode);

        btnEscanear.setOnClickListener(mOnClickListener);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null){
                txtBarCode.setText("El código de barras es " + result.getContents());
            } else {
                txtBarCode.setText("Error al escanear el código de barras");
            }
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnScan:
                    new IntentIntegrator(getActivity()).initiateScan();
            }
        }
    };


}