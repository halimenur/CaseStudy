Proje testleri için Postman koleksiyon dosyaları eklenmiştir:

- **Demo Kisi API.postman_collection.json** → API endpoint'lerini içerir  
- **SpringJWT.postman_environment.json** → JWT token değişkenini otomatik olarak yönetir

Kullanım:
1. Postman'de `Import` → `Files` sekmesinden yukarıdaki iki dosyayı içe aktarın.  
2. `SpringJWT` environment’ını seçin.  
3. Önce `/api/auth/register` veya `/api/auth/login` isteğini çalıştırın.  
   - Token otomatik olarak environment’a kaydedilecektir.  
4. Ardından `Meslek`, `Kisi` vb. endpointleri test edebilirsiniz.  

** Not: `USER` rolü yalnızca görüntüleme yapabilir, `ADMIN` rolü ekleme/silme yetkisine sahiptir.
