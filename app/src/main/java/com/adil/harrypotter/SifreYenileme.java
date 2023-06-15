package com.adil.harrypotter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.adil.harrypotter.databinding.ActivitySifreYenilemeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SifreYenileme extends AppCompatActivity {
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private ActivitySifreYenilemeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySifreYenilemeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void sifreDegistir(View view) {
        String posta = binding.posta.getText().toString();
        if (posta.isEmpty()) {
            Toast.makeText(getApplicationContext(), "E-Posta Giriniz.", Toast.LENGTH_LONG).show();
        } else {
            firebaseAuth.sendPasswordResetEmail(posta).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(SifreYenileme.this,"Email başarıyla gönderildi.",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(SifreYenileme.this,"Email gönderilemedi.",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
