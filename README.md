# 📡 USSD Event Processor – Query API Service

This is **Application 2** of the USSD Event Processor system. It provides a **RESTful API** to query records from the `call_detail_records` table based on a given date range, with optional filtering by `MSISDN` or `IMSI`.

---

## 📌 Features

- 🔍 Query USSD call records via a POST API
- 📅 Mandatory date range filters: `record_date_start`, `record_date_end`
- 📱 Optional filters: `msisdn`, `imsi`
- 🧾 Returns a list of matched records with fields: `RECORD_DATE`, `MSISDN`, `IMSI`

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson (for JSON parsing)
- Lombok (optional)

---


