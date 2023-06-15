package com.adil.harrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.adil.harrypotter.databinding.ActivityAnasayfaBinding;
import com.adil.harrypotter.music.Muzik;
import com.google.firebase.auth.FirebaseAuth;

public class Anasayfa extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ActivityAnasayfaBinding binding;
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.cikis) {
            auth.signOut();
            Intent intent = new Intent(Anasayfa.this,GirisSayfasi.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnasayfaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getApplicationContext().startService(new Intent(getApplicationContext(), Muzik.class));
        binding.muzikdur.setText("Müziği Durdur");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.secenekler,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public  void arkaplanMuzik(View view){
        if (binding.muzikdur.isChecked()) {
            getApplicationContext().stopService(new Intent(getApplicationContext(),Muzik.class));
            binding.muzikdur.setText("Müziği Başlat");
        } else {
            getApplicationContext().startService(new Intent(getApplicationContext(),Muzik.class));
            binding.muzikdur.setText("Müziği Durdur");
        }
    }
    public void level1(View view){
        binding.seviye2.setChecked(false);
        binding.seviye3.setChecked(false);
    }
    public void level2(View view){
        binding.seviye1.setChecked(false);
        binding.seviye3.setChecked(false);
    }
    public void level3(View view){
        binding.seviye1.setChecked(false);
        binding.seviye2.setChecked(false);
    }
    public void ikioyuncu(View view){
        Intent intent = new Intent(Anasayfa.this, CiftOyuncu.class);
        if (binding.seviye1.isChecked()){
            intent.putExtra("seviye","1");
            startActivity(intent);
        } else if (binding.seviye2.isChecked()){
            intent.putExtra("seviye","2");
            startActivity(intent);
        } else if (binding.seviye3.isChecked()){
            intent.putExtra("seviye","3");
            startActivity(intent);
        }else{
            Toast.makeText(Anasayfa.this,"Oyun seviyelerinden birini seçmek zorundasınız.", Toast.LENGTH_LONG).show();
        }
    }
    public void tekoyuncu(View view){
        Intent intent = new Intent(Anasayfa.this, TekOyuncu.class);
        if (binding.seviye1.isChecked()){
            intent.putExtra("seviye","1");
            startActivity(intent);
        } else if (binding.seviye2.isChecked()){
            intent.putExtra("seviye","2");
            startActivity(intent);
        } else if (binding.seviye3.isChecked()){
            intent.putExtra("seviye","3");
            startActivity(intent);
        }else{
            Toast.makeText(Anasayfa.this,"Oyun seviyelerinden birini seçmek zorundasınız.", Toast.LENGTH_LONG).show();
        }
    }
}
