package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {
    EditText et_otp;
    Button btn;
    String phoneNo;
    String otpid;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        et_otp = (EditText) findViewById(R.id.et_OTP);
        btn = (Button) findViewById(R.id.submitBtn);
        phoneNo = getIntent().getStringExtra("mobile").toString();
        mAuth = FirebaseAuth.getInstance();

        initiateOTP();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_otp.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Blank field cannot be processed", Toast.LENGTH_SHORT).show();
                }
                else if(et_otp.getText().toString().length()!=6){
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
                else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, et_otp.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void initiateOTP() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(OTPActivity.this, "SignIn Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}