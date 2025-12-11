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


