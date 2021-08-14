package com.codewithsandy.skt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codewithsandy.skt.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;



public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationID;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog pd;

    private  static final String TAG="MAIN_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         firebaseAuth = FirebaseAuth.getInstance();

         pd=new ProgressDialog(this);
         pd.setTitle("Please wait...");
         pd.setCanceledOnTouchOutside(false);

         mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
             @Override
             public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                 signInWithPhoneAuthCredential(phoneAuthCredential);

             }

             @Override
             public void onVerificationFailed(@NonNull FirebaseException e) {
                 pd.dismiss();
                 Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

             }

             @Override
             public void onCodeSent(@NonNull String verificationId, @NonNull  PhoneAuthProvider.ForceResendingToken token) {
                 super.onCodeSent(verificationId, forceResendingToken);

                 Log.d(TAG,"onCodeSent:"+verificationId);
                 mVerificationID=verificationId;
                 forceResendingToken=token;
                 pd.dismiss();
                 Toast.makeText(LoginActivity.this,"Verification sent...",Toast.LENGTH_SHORT).show();

             }
         };

         binding.idBtncontinue.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String phone=binding.idEdtPhoneNumber.getText().toString().trim();
                 if(TextUtils.isEmpty(phone))
                 {
                     Toast.makeText(LoginActivity.this,"Please enter phone number...",Toast.LENGTH_SHORT).show();
                 }
                 else {
                     startPhoneNumberVerification(phone);
                 }

             }
         });
        binding.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=binding.idEdtPhoneNumber.getText().toString().trim();
                if (TextUtils.isEmpty(phone))
                {
                    Toast.makeText(LoginActivity.this,"Please enter phone number...",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    resendVerificationCode(phone,forceResendingToken);
                }

            }
        });

         binding.idBtnVerify.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String code=binding.idEdtOtp.getText().toString().trim();
                 if (TextUtils.isEmpty(code))
                 {
                     Toast.makeText(LoginActivity.this,"Please enter phone number...",Toast.LENGTH_SHORT).show();

                 }
                 else
                 {
                     verifyPhoneNumberWithCode(mVerificationID,code);
                 }

             }
         });
    }


    private void startPhoneNumberVerification(String phone)
    {
        pd.setMessage("Verifying Phone Number");
        pd.show();
        PhoneAuthOptions options=
                PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void resendVerificationCode(String phone,PhoneAuthProvider.ForceResendingToken token)
    {
        pd.setMessage("Resending code...");
        pd.show();
        PhoneAuthOptions options=
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L,TimeUnit.SECONDS)
                        .setActivity(this)
                        .setForceResendingToken(token)
                        .setCallbacks(mCallbacks)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);


    }
    private void verifyPhoneNumberWithCode(String verificationId,String code)
    {
        pd.setMessage("Verifying Code");
        pd.show();

        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
        signInWithPhoneAuthCredential(credential);


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        pd.setMessage("Logging in...");
        firebaseAuth.signInWithCredential(credential).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        pd.dismiss();
                        String phone=firebaseAuth.getCurrentUser().getPhoneNumber();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        pd.dismiss();
                        Toast.makeText(LoginActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

}