# RestAssured ile API Regresyon Test Otomasyonu

Bu depo, Java tabanlı örnek bir API regresyon test paketini içerir. **RestAssured**, **JUnit 5** ve **Hamcrest** kullanarak RESTful servisler için otomatik testlerin nasıl yapılacağını gösterir.

## 🚀 Özellikler

- **Otomatik GET/POST Testleri**: Kullanıcı getirme ve oluşturma işlemlerini doğrular.
- **Ortam Yönetimi**: `dotenv-java` aracılığıyla `.env` dosyalarını kullanarak API anahtarlarını güvenli bir şekilde işler.
- **Doğrulama Katmanları**:
    - **Durum Kodu (Status Code)**: Doğru HTTP yanıtlarının alındığından emin olur (örn. 200 OK, 201 Created).
    - **Yanıt Gövdesi (Response Body)**: Hamcrest eşleştiricilerini kullanarak JSON alan değerlerini ve türlerini kontrol eder.
    - **Performans**: Yanıt sürelerinin kabul edilebilir sınırlar içinde olduğunu doğrular.
- **Global Yapılandırma**: Temel URI'leri (base URI) ve global istek özelliklerini ayarlamak için `@BeforeAll` kullanır.

---

## 🛠️ Kullanılan Teknolojiler 

- **Dil:** Java 21
- **Test Çerçevesi:** JUnit 5
- **REST API için DSL:** RestAssured
- **Doğrulamalar (Assertions):** Hamcrest
- **Derleme Aracı:** Maven
- **Gizli Veri Yönetimi:** Dotenv-java

---

## 📋 Ön Koşullar

- **JDK 21** yüklü olmalıdır.
- **Maven** yüklü olmalıdır.
- Kök dizinde `.env` adında bir ortam dosyası bulunmalıdır.

---

## ⚙️ Kurulum

1. **Depoyu klonlayın:**
   ```bash
   git clone https://github.com/belerumut/api-regression-test.git
   cd api-regression-test
   ```

2. **Ortam Değişkenlerini Yapılandırın:**
   Projenin kök dizininde bir `.env` dosyası oluşturun ve API anahtarınızı ekleyin:
   
   ```env
   API_KEY=sizin_gizli_api_anahtariniz_buraya
   ```

3. **Bağımlılıkları Yükleyin:**
   ```bash
   mvn clean install
   ```

---

## 🧪 Testleri Çalıştırma

Testleri terminal üzerinden çalıştırmak için aşağıdaki komutu kullanın:
```bash
mvn test
```

`maven-surefire-plugin` testlerin yürütülmesini sağlayacak ve konsolda test sonuçlarının bir özetini sunacaktır.

---

## 📁 Proje Yapısı

```text
├── src
│   └── test
│       └── java
│           └── ApiRegressionTest.java  # Ana test paketi
├── .env                                # API Gizli Anahtarları (Git tarafından yok sayılır)
├── pom.xml                             # Proje bağımlılıkları ve yapılandırma
└── README.md                           # Dokümantasyon
```

---

## 📝 Temel Test Senaryoları

### 1. Tekil Kullanıcı Getirme (`testGetSingleUser`)
- **Uç Nokta (Endpoint)**: `GET /users/11`
- **Doğrulama**:
    - Durum kodu `200` olmalıdır.
    - Kullanıcı ID'si `11` olmalıdır.
    - İlk isim `"George"` olmalıdır.
    - Yanıt süresi `3000ms`'den kısa olmalıdır.

### 2. Kullanıcı Oluşturma (`testCreateUser`)
- **Uç Nokta (Endpoint)**: `POST /users`
- **Veri Yükü (Payload)**: İsim ve meslek içeren JSON.
- **Doğrulama**:
    - Durum kodu `201` olmalıdır.
    - Dönen isim, gönderilen isimle eşleşmelidir.
    - Yeni kayda bir ID atanmış olmalıdır.
    - Yanıt süresi `3000ms`'den kısa olmalıdır.

---

## İletişim 

Geri bildirim ve sorularınız için linkedin üzerinden iletişime geçebilirsiniz.
- [LinkedIn Profilim](https://www.linkedin.com/in/umut-b-b9b691251/)
