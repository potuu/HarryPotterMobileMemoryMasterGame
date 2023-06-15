package com.adil.harrypotter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.adil.harrypotter.databinding.ActivityTekOyuncuBinding;
import com.adil.harrypotter.music.Muzik;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class TekOyuncu extends AppCompatActivity {
    ActivityTekOyuncuBinding binding;
    FirebaseFirestore veritaban = FirebaseFirestore.getInstance();
    Handler handler = new Handler(Looper.getMainLooper());
    ArrayList<Kart> genelKartlar = new ArrayList<>();//Kartların bilgisi
    ArrayList<Integer> kartlarinYeri = new ArrayList<>();//Kartların yerleri
    ArrayList<ImageView> resimListe1 = new ArrayList<>();//Gösterilecek resimler
    ArrayList<ImageView> resimListe2 = new ArrayList<>();//Gösterilmeyecek resimler
    ArrayList<Boolean> resimListe3 = new ArrayList<>();//Resimlerin Açık mı değil mi kontrolü
    ArrayList<Integer> acikResimler = new ArrayList<>();//Açık olan Resimler
    ArrayList<Integer> evler = new ArrayList<>();//Açık olan resimlerin evleri
    ArrayList<Integer> puanlar = new ArrayList<>();//Açık olan resimlerin puanları
    ArrayList<String> isimler =  new ArrayList<>();//Açık olan resimlerin isimleri
    boolean durma = false,animasyon = false;//Oyuncu sırası belirleme, Zaman bitmesi, animasyon varken tıklamayı engelleme
    Integer puan =0;//Elde edilen puan
    Integer aRS = 0;//Açık Resim Sayacı
    OyunMuzikleri mzk = new OyunMuzikleri();//Bitince, esleşince ve kazanınca çalan müzikler
    Runnable runnable;
    private CountDownTimer gerisayac;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.kartlar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.kartlar) {
            for(int i=0;i<genelKartlar.size();i++){
                int k1 = kartlarinYeri.get(2*i)+1;
                int k2 = kartlarinYeri.get(2*i+1)+1;
                String q = genelKartlar.get(i).isim+" "+genelKartlar.get(i).ev+" "+genelKartlar.get(i).puan+" -> ("+k1+","+k2+")";
                System.out.println(q);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTekOyuncuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toplamPuan.setText("Puan: 0");
        oyunuBaslat();
    }

    private void oyunuBaslat(){
        resimListe2.clear();
        resimListe1.clear();
        Intent intent = getIntent();
        if (intent.getStringExtra("seviye").equals("1")){
            resimListe2.add(binding.kart1);
            resimListe2.add(binding.kart2);
            resimListe2.add(binding.kart3);
            resimListe2.add(binding.kart4);
            resimListe2.add(binding.kart5);
            resimListe2.add(binding.kart6);
            resimListe2.add(binding.kart7);
            resimListe2.add(binding.kart8);
            resimListe2.add(binding.kart9);
            resimListe2.add(binding.kart10);
            resimListe2.add(binding.kart11);
            resimListe2.add(binding.kart12);
            resimListe2.add(binding.kart13);
            resimListe2.add(binding.kart14);
            resimListe2.add(binding.kart17);
            resimListe2.add(binding.kart18);
            resimListe2.add(binding.kart19);
            resimListe2.add(binding.kart20);
            resimListe2.add(binding.kart23);
            resimListe2.add(binding.kart24);
            resimListe2.add(binding.kart25);
            resimListe2.add(binding.kart26);
            resimListe2.add(binding.kart27);
            resimListe2.add(binding.kart28);
            resimListe2.add(binding.kart29);
            resimListe2.add(binding.kart30);
            resimListe2.add(binding.kart31);
            resimListe2.add(binding.kart32);
            resimListe2.add(binding.kart33);
            resimListe2.add(binding.kart34);
            resimListe2.add(binding.kart35);
            resimListe2.add(binding.kart36);
            resimListe1.add(binding.kart15);
            resimListe1.add(binding.kart16);
            resimListe1.add(binding.kart21);
            resimListe1.add(binding.kart22);
            for(int i = 0; i < resimListe2.size(); i++){
                resimListe2.get(i).setVisibility(View.INVISIBLE);
            }
            for(int i = 0; i < resimListe2.size()+resimListe1.size(); i++){
                resimListe3.add(false);
            }
        }
        if (intent.getStringExtra("seviye").equals("2")){
            resimListe2.add(binding.kart1);
            resimListe2.add(binding.kart2);
            resimListe2.add(binding.kart3);
            resimListe2.add(binding.kart4);
            resimListe2.add(binding.kart5);
            resimListe2.add(binding.kart6);
            resimListe2.add(binding.kart7);
            resimListe2.add(binding.kart12);
            resimListe2.add(binding.kart13);
            resimListe2.add(binding.kart18);
            resimListe2.add(binding.kart19);
            resimListe2.add(binding.kart24);
            resimListe2.add(binding.kart25);
            resimListe2.add(binding.kart30);
            resimListe2.add(binding.kart31);
            resimListe2.add(binding.kart32);
            resimListe2.add(binding.kart33);
            resimListe2.add(binding.kart34);
            resimListe2.add(binding.kart35);
            resimListe2.add(binding.kart36);
            resimListe1.add(binding.kart8);
            resimListe1.add(binding.kart9);
            resimListe1.add(binding.kart10);
            resimListe1.add(binding.kart11);
            resimListe1.add(binding.kart14);
            resimListe1.add(binding.kart15);
            resimListe1.add(binding.kart16);
            resimListe1.add(binding.kart17);
            resimListe1.add(binding.kart20);
            resimListe1.add(binding.kart21);
            resimListe1.add(binding.kart22);
            resimListe1.add(binding.kart23);
            resimListe1.add(binding.kart26);
            resimListe1.add(binding.kart27);
            resimListe1.add(binding.kart28);
            resimListe1.add(binding.kart29);
            for(int i = 0; i < resimListe2.size(); i++){
                resimListe2.get(i).setVisibility(View.INVISIBLE);
            }
            for(int i = 0; i < resimListe2.size()+resimListe1.size(); i++){
                resimListe3.add(false);
            }
        }
        if (intent.getStringExtra("seviye").equals("3")){
            resimListe1.add(binding.kart1);
            resimListe1.add(binding.kart2);
            resimListe1.add(binding.kart3);
            resimListe1.add(binding.kart4);
            resimListe1.add(binding.kart5);
            resimListe1.add(binding.kart6);
            resimListe1.add(binding.kart7);
            resimListe1.add(binding.kart8);
            resimListe1.add(binding.kart9);
            resimListe1.add(binding.kart10);
            resimListe1.add(binding.kart11);
            resimListe1.add(binding.kart12);
            resimListe1.add(binding.kart13);
            resimListe1.add(binding.kart14);
            resimListe1.add(binding.kart15);
            resimListe1.add(binding.kart16);
            resimListe1.add(binding.kart17);
            resimListe1.add(binding.kart18);
            resimListe1.add(binding.kart19);
            resimListe1.add(binding.kart20);
            resimListe1.add(binding.kart21);
            resimListe1.add(binding.kart22);
            resimListe1.add(binding.kart23);
            resimListe1.add(binding.kart24);
            resimListe1.add(binding.kart25);
            resimListe1.add(binding.kart26);
            resimListe1.add(binding.kart27);
            resimListe1.add(binding.kart28);
            resimListe1.add(binding.kart29);
            resimListe1.add(binding.kart30);
            resimListe1.add(binding.kart31);
            resimListe1.add(binding.kart32);
            resimListe1.add(binding.kart33);
            resimListe1.add(binding.kart34);
            resimListe1.add(binding.kart35);
            resimListe1.add(binding.kart36);
            for(int i = 0; i < resimListe1.size(); i++){
                resimListe3.add(false);
            }
        }
        ArrayList<Kart> h0 = new ArrayList<>();
        ArrayList<Kart> h1 = new ArrayList<>();
        ArrayList<Kart> h2 = new ArrayList<>();
        ArrayList<Kart> h3 = new ArrayList<>();
        ArrayList<Kart> h4 = new ArrayList<>();
        veritaban.collection("Kartlar").addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                Toast.makeText(TekOyuncu.this, Objects.requireNonNull(e.getLocalizedMessage()),Toast.LENGTH_LONG).show();
            }
            if (queryDocumentSnapshots != null) {
                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                    Map<String,Object> data = snapshot.getData();
                    int puan = Integer.parseInt((String) Objects.requireNonNull(data.get("point")));
                    String isim = (String) data.get("name");
                    String resim = (String) data.get("image");
                    String ev = (String) data.get("house");
                    Bitmap res = BitmapFactory.decodeByteArray(Base64.decode(resim, Base64.DEFAULT),0, Base64.decode(resim, Base64.DEFAULT).length);
                    h0.add(new Kart(isim,ev,puan,res));
                    if (ev.equals("gryffindore")){h1.add(new Kart(isim,ev,puan,res));}
                    if (ev.equals("slytherin")){h2.add(new Kart(isim,ev,puan,res));}
                    if (ev.equals("hufflepuff")){h3.add(new Kart(isim,ev,puan,res));}
                    if (ev.equals("ravenclaw")){h4.add(new Kart(isim,ev,puan,res));}
                }
                Random ran = new Random();
                ArrayList<Integer> rastgele1 = new ArrayList<>();
                boolean devamet = true;
                int sayac = 0;
                while (devamet){
                    int rand = ran.nextInt(resimListe1.size());
                    if (!rastgele1.contains(rand)) {rastgele1.add(rand);sayac+=1;}
                    if (sayac==resimListe1.size()/2){devamet = false;}
                }
                ArrayList<Integer> rastgele2 = new ArrayList<>();
                for(int i = 0; i < resimListe1.size(); i++){
                    if (!rastgele1.contains(i)) {
                        rastgele2.add(i);
                    }
                }
                if(resimListe1.size()==4){
                    for(int i = 0; i < rastgele1.size(); i++){
                        Kart rastgele = h0.get(ran.nextInt(h0.size()));
                        int cr = i;
                        resimListe1.get(rastgele1.get(i)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(cr)), rastgele, rastgele1.get(cr)));
                        resimListe1.get(rastgele2.get(i)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(cr)), rastgele, rastgele2.get(cr)));
                        genelKartlar.add(rastgele);
                        kartlarinYeri.add(rastgele1.get(i));
                        kartlarinYeri.add(rastgele2.get(i));
                    }
                }
                if(resimListe1.size()==16){
                    Kart kart1 = h1.get(ran.nextInt(h1.size()));
                    Kart kart2 = h1.get(ran.nextInt(h1.size()));
                    Kart kart3 = h2.get(ran.nextInt(h2.size()));
                    Kart kart4 = h2.get(ran.nextInt(h2.size()));
                    Kart kart5 = h3.get(ran.nextInt(h3.size()));
                    Kart kart6 = h3.get(ran.nextInt(h3.size()));
                    Kart kart7 = h4.get(ran.nextInt(h4.size()));
                    Kart kart8 = h4.get(ran.nextInt(h4.size()));
                    resimListe1.get(rastgele1.get(0)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(0)), kart1, rastgele1.get(0)));
                    resimListe1.get(rastgele2.get(0)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(0)), kart1, rastgele2.get(0)));
                    resimListe1.get(rastgele1.get(1)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(1)), kart2, rastgele1.get(1)));
                    resimListe1.get(rastgele2.get(1)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(1)), kart2, rastgele2.get(1)));
                    resimListe1.get(rastgele1.get(2)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(2)), kart3, rastgele1.get(2)));
                    resimListe1.get(rastgele2.get(2)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(2)), kart3, rastgele2.get(2)));
                    resimListe1.get(rastgele1.get(3)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(3)), kart4, rastgele1.get(3)));
                    resimListe1.get(rastgele2.get(3)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(3)), kart4, rastgele2.get(3)));
                    resimListe1.get(rastgele1.get(4)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(4)), kart5, rastgele1.get(4)));
                    resimListe1.get(rastgele2.get(4)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(4)), kart5, rastgele2.get(4)));
                    resimListe1.get(rastgele1.get(5)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(5)), kart6, rastgele1.get(5)));
                    resimListe1.get(rastgele2.get(5)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(5)), kart6, rastgele2.get(5)));
                    resimListe1.get(rastgele1.get(6)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(6)), kart7, rastgele1.get(6)));
                    resimListe1.get(rastgele2.get(6)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(6)), kart7, rastgele2.get(6)));
                    resimListe1.get(rastgele1.get(7)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(7)), kart8, rastgele1.get(7)));
                    resimListe1.get(rastgele2.get(7)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(7)), kart8, rastgele2.get(7)));
                    genelKartlar.add(kart1);
                    genelKartlar.add(kart2);
                    genelKartlar.add(kart3);
                    genelKartlar.add(kart4);
                    genelKartlar.add(kart5);
                    genelKartlar.add(kart6);
                    genelKartlar.add(kart7);
                    genelKartlar.add(kart8);
                    kartlarinYeri.add(rastgele1.get(0));
                    kartlarinYeri.add(rastgele2.get(0));
                    kartlarinYeri.add(rastgele1.get(1));
                    kartlarinYeri.add(rastgele2.get(1));
                    kartlarinYeri.add(rastgele1.get(2));
                    kartlarinYeri.add(rastgele2.get(2));
                    kartlarinYeri.add(rastgele1.get(3));
                    kartlarinYeri.add(rastgele2.get(3));
                    kartlarinYeri.add(rastgele1.get(4));
                    kartlarinYeri.add(rastgele2.get(4));
                    kartlarinYeri.add(rastgele1.get(5));
                    kartlarinYeri.add(rastgele2.get(5));
                    kartlarinYeri.add(rastgele1.get(6));
                    kartlarinYeri.add(rastgele2.get(6));
                    kartlarinYeri.add(rastgele1.get(7));
                    kartlarinYeri.add(rastgele2.get(7));
                }
                if(resimListe1.size()==36){
                    Kart kart1 = h1.get(ran.nextInt(h1.size()));
                    Kart kart2 = h1.get(ran.nextInt(h1.size()));
                    Kart kart3 = h1.get(ran.nextInt(h1.size()));
                    Kart kart4 = h1.get(ran.nextInt(h1.size()));
                    Kart kart5 = h1.get(ran.nextInt(h1.size()));
                    Kart kart6 = h2.get(ran.nextInt(h2.size()));
                    Kart kart7 = h2.get(ran.nextInt(h2.size()));
                    Kart kart8 = h2.get(ran.nextInt(h2.size()));
                    Kart kart9 = h2.get(ran.nextInt(h2.size()));
                    Kart kart10 = h2.get(ran.nextInt(h1.size()));
                    Kart kart11 = h3.get(ran.nextInt(h3.size()));
                    Kart kart12 = h3.get(ran.nextInt(h3.size()));
                    Kart kart13 = h3.get(ran.nextInt(h3.size()));
                    Kart kart14 = h3.get(ran.nextInt(h3.size()));
                    Kart kart15 = h4.get(ran.nextInt(h4.size()));
                    Kart kart16 = h4.get(ran.nextInt(h4.size()));
                    Kart kart17 = h4.get(ran.nextInt(h4.size()));
                    Kart kart18 = h4.get(ran.nextInt(h4.size()));
                    resimListe1.get(rastgele1.get(0)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(0)), kart1, rastgele1.get(0)));
                    resimListe1.get(rastgele2.get(0)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(0)), kart1, rastgele2.get(0)));
                    resimListe1.get(rastgele1.get(1)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(1)), kart2, rastgele1.get(1)));
                    resimListe1.get(rastgele2.get(1)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(1)), kart2, rastgele2.get(1)));
                    resimListe1.get(rastgele1.get(2)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(2)), kart3, rastgele1.get(2)));
                    resimListe1.get(rastgele2.get(2)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(2)), kart3, rastgele2.get(2)));
                    resimListe1.get(rastgele1.get(3)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(3)), kart4, rastgele1.get(3)));
                    resimListe1.get(rastgele2.get(3)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(3)), kart4, rastgele2.get(3)));
                    resimListe1.get(rastgele1.get(4)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(4)), kart5, rastgele1.get(4)));
                    resimListe1.get(rastgele2.get(4)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(4)), kart5, rastgele2.get(4)));
                    resimListe1.get(rastgele1.get(5)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(5)), kart6, rastgele1.get(5)));
                    resimListe1.get(rastgele2.get(5)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(5)), kart6, rastgele2.get(5)));
                    resimListe1.get(rastgele1.get(6)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(6)), kart7, rastgele1.get(6)));
                    resimListe1.get(rastgele2.get(6)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(6)), kart7, rastgele2.get(6)));
                    resimListe1.get(rastgele1.get(7)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(7)), kart8, rastgele1.get(7)));
                    resimListe1.get(rastgele2.get(7)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(7)), kart8, rastgele2.get(7)));
                    resimListe1.get(rastgele1.get(8)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(8)), kart9, rastgele1.get(8)));
                    resimListe1.get(rastgele2.get(8)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(8)), kart9, rastgele2.get(8)));
                    resimListe1.get(rastgele1.get(9)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(9)), kart10, rastgele1.get(9)));
                    resimListe1.get(rastgele2.get(9)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(9)), kart10, rastgele2.get(9)));
                    resimListe1.get(rastgele1.get(10)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(10)), kart11, rastgele1.get(10)));
                    resimListe1.get(rastgele2.get(10)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(10)), kart11, rastgele2.get(10)));
                    resimListe1.get(rastgele1.get(11)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(11)), kart12, rastgele1.get(11)));
                    resimListe1.get(rastgele2.get(12)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(11)), kart12, rastgele2.get(11)));
                    resimListe1.get(rastgele1.get(12)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(12)), kart13, rastgele1.get(12)));
                    resimListe1.get(rastgele2.get(12)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(12)), kart13, rastgele2.get(12)));
                    resimListe1.get(rastgele1.get(13)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(13)), kart14, rastgele1.get(13)));
                    resimListe1.get(rastgele2.get(13)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(13)), kart14, rastgele2.get(13)));
                    resimListe1.get(rastgele1.get(14)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(14)), kart15, rastgele1.get(14)));
                    resimListe1.get(rastgele2.get(14)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(14)), kart15, rastgele2.get(14)));
                    resimListe1.get(rastgele1.get(15)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(15)), kart16, rastgele1.get(15)));
                    resimListe1.get(rastgele2.get(15)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(15)), kart16, rastgele2.get(15)));
                    resimListe1.get(rastgele1.get(16)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(16)), kart17, rastgele1.get(16)));
                    resimListe1.get(rastgele2.get(16)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(16)), kart17, rastgele2.get(16)));
                    resimListe1.get(rastgele1.get(17)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele1.get(17)), kart18, rastgele1.get(17)));
                    resimListe1.get(rastgele2.get(17)).setOnClickListener(v -> kartCevir(resimListe1.get(rastgele2.get(17)), kart18, rastgele2.get(17)));
                    genelKartlar.add(kart1);
                    genelKartlar.add(kart2);
                    genelKartlar.add(kart3);
                    genelKartlar.add(kart4);
                    genelKartlar.add(kart5);
                    genelKartlar.add(kart6);
                    genelKartlar.add(kart7);
                    genelKartlar.add(kart8);
                    genelKartlar.add(kart9);
                    genelKartlar.add(kart10);
                    genelKartlar.add(kart11);
                    genelKartlar.add(kart12);
                    genelKartlar.add(kart13);
                    genelKartlar.add(kart14);
                    genelKartlar.add(kart15);
                    genelKartlar.add(kart16);
                    genelKartlar.add(kart17);
                    genelKartlar.add(kart18);
                    kartlarinYeri.add(rastgele1.get(0));
                    kartlarinYeri.add(rastgele2.get(0));
                    kartlarinYeri.add(rastgele1.get(1));
                    kartlarinYeri.add(rastgele2.get(1));
                    kartlarinYeri.add(rastgele1.get(2));
                    kartlarinYeri.add(rastgele2.get(2));
                    kartlarinYeri.add(rastgele1.get(3));
                    kartlarinYeri.add(rastgele2.get(3));
                    kartlarinYeri.add(rastgele1.get(4));
                    kartlarinYeri.add(rastgele2.get(4));
                    kartlarinYeri.add(rastgele1.get(5));
                    kartlarinYeri.add(rastgele2.get(5));
                    kartlarinYeri.add(rastgele1.get(6));
                    kartlarinYeri.add(rastgele2.get(6));
                    kartlarinYeri.add(rastgele1.get(7));
                    kartlarinYeri.add(rastgele2.get(7));
                    kartlarinYeri.add(rastgele1.get(8));
                    kartlarinYeri.add(rastgele2.get(8));
                    kartlarinYeri.add(rastgele2.get(9));
                    kartlarinYeri.add(rastgele2.get(9));
                    kartlarinYeri.add(rastgele1.get(10));
                    kartlarinYeri.add(rastgele2.get(10));
                    kartlarinYeri.add(rastgele1.get(11));
                    kartlarinYeri.add(rastgele2.get(11));
                    kartlarinYeri.add(rastgele1.get(12));
                    kartlarinYeri.add(rastgele2.get(12));
                    kartlarinYeri.add(rastgele1.get(13));
                    kartlarinYeri.add(rastgele2.get(13));
                    kartlarinYeri.add(rastgele1.get(14));
                    kartlarinYeri.add(rastgele2.get(14));
                    kartlarinYeri.add(rastgele1.get(15));
                    kartlarinYeri.add(rastgele2.get(15));
                    kartlarinYeri.add(rastgele1.get(16));
                    kartlarinYeri.add(rastgele2.get(16));
                    kartlarinYeri.add(rastgele1.get(17));
                    kartlarinYeri.add(rastgele2.get(17));
                }
                gerisayac = new CountDownTimer(45500,1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(!durma){
                            binding.zamanbilgisi.setText("Time: " + millisUntilFinished/1000);
                        }
                    }
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onFinish() {
                        if(!durma){
                            getApplicationContext().stopService(new Intent(getApplicationContext(), Muzik.class));
                            binding.zamanbilgisi.setText("Time: 0");
                            handler.removeCallbacks(runnable);
                            mzk.bitince(getApplicationContext());
                            AlertDialog.Builder alert = new AlertDialog.Builder(TekOyuncu.this);
                            alert.setTitle("OYUN BİTTİ");
                            alert.setMessage("Yeniden Oynamak İster misiniz?");
                            alert.setPositiveButton("Evet", (dialog, which) -> {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                getApplicationContext().startService(new Intent(getApplicationContext(),Muzik.class));
                            });
                            alert.setNegativeButton("Hayır", (dialog, which) -> {
                                Intent intent = new Intent(getApplicationContext(), Anasayfa.class);
                                startActivity(intent);
                                finish();
                            });
                            alert.show();
                            durma = true;
                        }
                    }
                }.start();
            }
        });
    }

    private void kartCevir(ImageView imageView,Kart kart, int i) {
        if (!durma) {
            if (!animasyon) {
                if (!resimListe3.get(i)) {
                    kartiAc(imageView, kart, i);
                } else {
                    kartiKapat(imageView, i);
                }
            }
        }
    }

    private void kartiAc(@NonNull ImageView imageView, Kart kart, int  i) {
        Animation anm1 = AnimationUtils.loadAnimation(TekOyuncu.this, R.anim.animasyon1);
        imageView.startAnimation(anm1);
        anm1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation a) {animasyon = true;}
            @Override
            public void onAnimationEnd(Animation a) {
                Animation anm2 = AnimationUtils.loadAnimation(TekOyuncu.this, R.anim.animasyon2);
                imageView.startAnimation(anm2);
                imageView.setImageBitmap(kart.resim);
                anm2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation a) {}
                    @SuppressLint({"DefaultLocale", "SetTextI18n"})
                    @Override
                    public void onAnimationEnd(Animation a) {
                        animasyon = false;
                        resimListe3.set(i,true);
                        acikResimler.add(i);
                        isimler.add(kart.isim);
                        binding.kartbilgisi.setText(kart.isim+" "+kart.puan+" "+kart.ev);
                        if (kart.ev.equals("gryffindore") || kart.ev.equals("slytherin")){
                            evler.add(2);
                        }
                        else{
                            evler.add(1);
                        }
                        puanlar.add(kart.puan);
                        aRS += 1;
                        if (aRS == 2) {
                            if (isimler.get(0).equals(isimler.get(1))) {
                                mzk.eslesince(getApplicationContext());
                                resimListe1.get(acikResimler.get(0)).setVisibility(View.INVISIBLE);
                                resimListe1.get(acikResimler.get(1)).setVisibility(View.INVISIBLE);
                                aRS = 0;
                                int kalanZaman1 = Integer.parseInt(binding.zamanbilgisi.getText().toString().replace("Time: ",""));
                                puan += (2 * puanlar.get(0) * evler.get(0))*(kalanZaman1/10);
                                binding.toplamPuan.setText(String.format("Puan: %2d", puan));
                                int sayac = 0;
                                for (ImageView image : resimListe1) {
                                    if (image.getVisibility() == View.INVISIBLE) {
                                        sayac += 1;
                                    }
                                }
                                if (sayac == resimListe1.size()) {
                                    durma = true;
                                    gerisayac.cancel();
                                    AlertDialog.Builder alert = new AlertDialog.Builder(TekOyuncu.this);
                                    mzk.yenince(getApplicationContext());
                                    alert.setTitle("Tebrikler. Oyunu kazandınız!!");
                                    alert.setMessage("Yeniden Oynamak İster misiniz?");
                                    alert.setPositiveButton("Evet", (dialog, which) -> {
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    });
                                    alert.setNegativeButton("Hayır", (dialog, which) -> {
                                        Intent intent = new Intent(getApplicationContext(), Anasayfa.class);
                                        startActivity(intent);
                                        finish();
                                    });
                                    alert.show();
                                }
                            }
                            else if (!isimler.get(0).equals(isimler.get(1)) && evler.get(0).equals(evler.get(1))) {
                                int kalanZaman2 = Integer.parseInt(binding.zamanbilgisi.getText().toString().replace("Time: ",""));
                                puan -= ((puanlar.get(0) + puanlar.get(1))/evler.get(1))*(kalanZaman2/10);
                                binding.toplamPuan.setText(String.format("Puan: %2d", puan));
                                kartiKapat(resimListe1.get(acikResimler.get(0)), acikResimler.get(0));
                                kartiKapat(resimListe1.get(acikResimler.get(1)), acikResimler.get(1));
                            }
                            else {
                                int kalanZaman3 = Integer.parseInt(binding.zamanbilgisi.getText().toString().replace("Time: ",""));
                                puan -= (evler.get(0)*evler.get(1)*((puanlar.get(0) + puanlar.get(1)) / 2))*(kalanZaman3/10);
                                binding.toplamPuan.setText(String.format("Puan: %2d", puan));
                                kartiKapat(resimListe1.get(acikResimler.get(0)), acikResimler.get(0));
                                kartiKapat(resimListe1.get(acikResimler.get(1)), acikResimler.get(1));
                            }
                            isimler.clear();
                            evler.clear();
                            puanlar.clear();
                            acikResimler.clear();
                        }
                    }
                    @Override
                    public void onAnimationRepeat(Animation a) {}
                });
            }
            @Override
            public void onAnimationRepeat(Animation a) {}}
        );
        imageView.setClickable(false);
    }

    private void kartiKapat(@NonNull ImageView imageView, int  i) {
        Animation anm1 = AnimationUtils.loadAnimation(TekOyuncu.this, R.anim.animasyon1);
        imageView.startAnimation(anm1);
        anm1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation a) {animasyon = true;}
            @Override
            public void onAnimationEnd(Animation a) {
                Animation anm2 = AnimationUtils.loadAnimation(TekOyuncu.this, R.anim.animasyon2);
                imageView.startAnimation(anm2);
                imageView.setImageResource(R.drawable.kartarka);
                anm2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation a) {}
                    @Override
                    public void onAnimationEnd(Animation a) {animasyon = false;}
                    @Override
                    public void onAnimationRepeat(Animation a) {}
                });
            }
            @Override
            public void onAnimationRepeat(Animation a) {}}
        );
        resimListe3.set(i,false);
        aRS -= 1;
        imageView.setClickable(true);
    }

}