# 🚀 URL Shortener (Spring Boot)

A production-ready **URL Shortening Service** built with **Spring Boot**, featuring:

- 🔗 URL Shortening & Redirection
- ⚡ Redis Caching
- 🛡️ API Rate Limiting
- 🗄️ MySQL Database
- ☕ Java 17 / Spring Boot
- Clean, maintainable REST API design

---

## 📌 Features

### 🔗 URL Shortening
Converts long URLs into short unique codes  
Uses Base62 encoding or hash logic  
Handles HTTP 302 redirects automatically

### ⚡ Redis Caching
Improves redirect performance  
Reduces database lookups  
Configurable TTL

### 🛡️ Rate Limiting
Prevents API abuse  
Configurable requests per minute  
Returns HTTP 429 if the limit is exceeded

### 🗄️ Persistent Storage (MySQL)
Stores:
- Original URL
- Short URL code
- Created timestamps
- Click counts

---

## 🏗️ Project Structure

src/
└── main/
├── java/com/urlshortener/
│ ├── controller/
│ ├── service/
│ ├── model/
│ ├── repository/
│ └── config/
└── resources/
├── application.properties (ignored in Git)
└── application.example.properties


---

## ⚙️ Configuration

### Create this file manually (not committed):


Copy from the template:

Fill your DB & Redis details.

---

## 🚀 Running the Project

### 1️⃣ Start Redis


### 2️⃣ Create MySQL Database
```sql
CREATE DATABASE url_db;
mvn spring-boot:run
📡 API Endpoints
🔧 Shorten URL

POST /api/shorten

Body:{ "url": "https://google.com" }
Response:{ "shortUrl": "http://localhost:8080/abc123" }
🔁 Redirect

GET /{shortCode}
Automatically redirects with HTTP 302.

🛑 Rate Limit Response

If exceeded:

{
  "error": "Too many requests",
  "limit": 10,
  "window": "1 minute"
}
```

🖼️ Architecture & Flow Diagrams
<img width="857" height="316" alt="CacheRedis" src="https://github.com/user-attachments/assets/a02c6a08-b277-4de6-adcd-e4a01ffbe5bb" />
<img width="899" height="507" alt="RateLimitngRedis" src="https://github.com/user-attachments/assets/8d0659f6-ee4b-4dd7-b486-a6e4ec41303b" />
<img width="1918" height="1018" alt="RateLimitor" src="https://github.com/user-attachments/assets/73f0818a-980f-488c-83f9-f948c957412f" />
<img width="1918" height="1021" alt="ResponseFrom Database" src="https://github.com/user-attachments/assets/8f8092b2-838f-47e0-aba5-6479fe735a1f" />
<img width="1918" height="1021" alt="ResponseFromCache" src="https://github.com/user-attachments/assets/b90fb86c-32da-438a-bee7-66a5e8a1abf9" />


