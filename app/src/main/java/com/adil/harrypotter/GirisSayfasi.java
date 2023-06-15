package com.adil.harrypotter;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.adil.harrypotter.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GirisSayfasi extends AppCompatActivity {
    private final FirebaseFirestore veritaban = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(GirisSayfasi.this, Anasayfa.class);
            startActivity(intent);
            finish();
        }
    }

    public void giris (View view) {
        final int[] sayac = {0};
        String kullanici = binding.kullaniciadi.getText().toString();
        String posta = binding.posta.getText().toString();
        String sifre = binding.sifre.getText().toString();
        if(kullanici.isEmpty() || posta.isEmpty() || sifre.isEmpty() ){
            Toast.makeText(GirisSayfasi.this,"Bütün bilgileri tam doldurduğunuzdan emin olunuz.",Toast.LENGTH_LONG).show();
        }else {
            veritaban.collection("Kullanıcılar").addSnapshotListener((queryDocumentSnapshots, e) -> {
                if (e != null) {
                    Toast.makeText(GirisSayfasi.this, e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                if (queryDocumentSnapshots != null) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        Map<String,Object> data = snapshot.getData();
                        String kullaniciAl = (String) data.get("Kullanıcı Adı");
                        String postaAl = (String) data.get("E-Posta");
                        if(Objects.equals(kullaniciAl, kullanici) && Objects.equals(postaAl, posta)){
                            sayac[0] +=1;
                        }
                    }
                }
                if(sayac[0] >0){
                    firebaseAuth.signInWithEmailAndPassword(posta, sifre).addOnSuccessListener(authResult -> {
                        Toast.makeText(getApplicationContext(),"Hoşgeldiniz: " + posta,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(GirisSayfasi.this, Anasayfa.class);
                        startActivity(intent);
                        finish();
                    }).addOnFailureListener(r -> Toast.makeText(GirisSayfasi.this, r.getLocalizedMessage(), Toast.LENGTH_LONG).show());
                }else {
                    Toast.makeText(GirisSayfasi.this,"Böyle bir kullanıcı bulunmamaktadır.",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void kaydol (View view) {
        String kullanici = binding.kullaniciadi.getText().toString();
        String posta = binding.posta.getText().toString();
        String sifre = binding.sifre.getText().toString();
        if(kullanici.isEmpty() || posta.isEmpty() || sifre.isEmpty() ){
            Toast.makeText(GirisSayfasi.this,"Bütün bilgileri tam doldurduğunuzdan emin olunuz.",Toast.LENGTH_LONG).show();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(posta,sifre).addOnSuccessListener(authResult -> {
                HashMap<String, Object> postData = new HashMap<>();
                postData.put("Kullanıcı Adı",kullanici);
                postData.put("E-Posta",posta);
                veritaban.collection("Kullanıcılar").add(postData).addOnSuccessListener(documentReference -> {
                    Toast.makeText(GirisSayfasi.this,"Başarıyla kaydoldunuz. Giriş yapabilirsiniz.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GirisSayfasi.this,Anasayfa.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(GirisSayfasi.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show());
            }).addOnFailureListener(e -> Toast.makeText(GirisSayfasi.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show());
        }
    }

    public void unuttum(View view){
        Intent intent = new Intent(GirisSayfasi.this,SifreYenileme.class);
        startActivity(intent);
        finish();
    }
}