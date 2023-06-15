# HarryPotterMemoryMasterGame
It's a card pairing game based on Harry Potter character names, houses. 

GİRİŞ
Bu projenin Android platformuna uygulanması istendiğinden Java dili seçilmiştir.Bulunacak kartlar database kısmında saklanması istenmiştir.Bu amaçla seçilen database Firebase’dir. 

Giriş ekranı: Oyun ilk açıldığında ekranda açılacak sayfa giriş ekranı vardır. Kullanıcı bu ekranda, kullanıcı adı ve şifresi ile giriş yapabilmeli, şifre değiştirebilmeli ve kaydolabilmelidir.
Oyun ekranı: Kullanıcı giriş yaptıktan sonra karşısına gelecek ekran oyun ekranı bulunur. 

Oyun başlatıldığında kartlar kapalı şekilde dağıtılmalıdır. Oyundaki kartların her 
birinden birer çift bulunmaktadır. Buradaki amaç açılan kartın diğer çiftini 
bulabilmektir. Oyunda kartlar ilk olarak rastgele dağıtılır.


Burada Tek Oyuncu ve Çoklu Oyuncu Olarak iki farklı seçenek bulunur. Oyun ekranı ilk açıldığında “BAŞLA” butonu bulunmalıdır. Oyuncu BAŞLA butonuna tıkladığında oyun ve süre başlatılır.
 
Tek oyuncuda oyun skoru: Oyun süresi 45 saniyedir. Oyunda her kartın bir puanı ve ait olduğu bir ev bulunmaktadır. Oyun skoru her hamle sonrasında ekranda anlık olarak gösterilecektir.

Çoklu oyuncuda oyun skoru: Oyun süresi 60 saniyedir. Oyunda her kartın bir puanı ve ait olduğu bir ev bulunmaktadır. Her oyuncu sırayla seçim yapar. Doğru bir eşleştirme yapan oyuncu tekrar oynama hakkına sahiptir. Oyun skoru her hamle sonrasında ekranda anlık olarak gösterilecektir.


Oyun zorluk seviyesi: Oyunda 2*2, 4*4 ve 6*6 olmak üzere 3 farklı zorluk seviyesi 
vardır.

Arka plan müziği: Oyun esnasında arka planda bir müzik çalması beklenmektedir. 
Bu müzik oyun oynanırken çalmaya devam eder ve 3 durumda bu müzik değişecektir. 

1) Kartın eşi bulunduğunda farklı bir müzik ile uyarı verilir. (https://www.youtube.com/watch?v=BkBFl151KQI)
2) Oyun süresi bittiği zaman arka fon müziği olumsuz bir uyarı verir. (https://www.youtube.com/watch?v=ttdHX4cyoqQ)
3) Süre bitmeden bütün kartların eşi bulununca arka fon müziği kazandınız anlamında olumlu bir uyarı verir. Ayrıca oyun müziğinin istenilen durumda tamamen kapatılabilmesi için bir buton eklenmelidir. (https://www.youtube.com/watch?v=jgtRU_KBkNM) 


 SONUÇ: 
İstenilen oyun başarıyla tamamlandı.Kartlar databaseden rastgele çekilerek ekranda gösterildi ve skor tanımlandı.

 DENEYSEL SONUÇLAR:

![hp1](https://github.com/potuu/HarryPotterMemoryMasterGame/assets/82321990/2e9c05bd-d86a-4594-bf6f-c86776444fde)

![hp2](https://github.com/potuu/HarryPotterMemoryMasterGame/assets/82321990/8765c04a-bff7-42c3-a6a9-11d7dc58f789)

![hp3](https://github.com/potuu/HarryPotterMemoryMasterGame/assets/82321990/c5457342-9f66-4a1b-849d-c6a26a84cf95)

![hp4](https://github.com/potuu/HarryPotterMemoryMasterGame/assets/82321990/97843127-c841-4e70-9c03-7c2e7d75f00c)

![hp6](https://github.com/potuu/HarryPotterMemoryMasterGame/assets/82321990/74ea00e0-8d8c-4ea0-b4e9-660da743e670)

![hp6](https://github.com/potuu/HarryPotterMemoryMasterGame/assets/82321990/00f8bf79-b722-42ac-b8e0-22cf10539aef)

