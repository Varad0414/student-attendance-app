package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.hbb20.CountryCodePicker;

public class PhoneSign extends AppCompatActivity {
    CountryCodePicker ccp;
    Button btn;
    EditText txt;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_sign);

        ccp = findViewById(R.id.ccp);
        btn = findViewById(R.id.getOtpBtn);
        txt = findViewById(R.id.numberText);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.numberText,"^[+]?[0-9]{10,13}$",R.string.invalid_phone);

        ccp.registerCarrierNumberEditText(txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                    intent.putExtra("mobile", ccp.getFullNumberWithPlus().replace(" ", ""));
                    startActivity(intent);
                }
            }
        });
    }
}