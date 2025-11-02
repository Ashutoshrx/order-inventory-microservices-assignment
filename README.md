# 🧩 Inventory & Order Management - Spring Boot Applications

It includes two main services:
- **Inventory Processing Service** – manages inventory batches and product quantities.
- **Order Processing Service** – handles product's order placement.

---

## 🚀 Getting Started

Follow these steps to **clone**, **build**, and **run** the application locally.

### 1️⃣ Clone the Repository

```bash
https://github.com/Ashutoshrx/order-inventory-microservices-assignment.git
```
## Run Both the Spring Boot Applications

Order Processing is desired to run at port 8081 & Inventory Processing Service is desired to run at 8082. 
Once both are up & running, please refer to the following endpoints

## 🧠 API Endpoints
🏷️ 1. Fetch Inventory Batches

Fetch all inventory batches based on the requested requirement.
```bash
curl --location 'http://localhost:8082/inventory-processing/v1/inventory-batches/3'
```
📦 2. Place an Order

Place an order for a product with the desired quantity.
```bash
curl --location 'http://localhost:8081/order-processing/v1/order' \
--header 'Content-Type: application/json' \
--data '{
    "productId": 3,
    "quantity": 6
}'

```
## 🧰 Database Access (H2 Console)

Each microservice includes its own **in-memory H2 database** for storing data.  
You can inspect tables and data directly using the H2 Console links below:

| Service | Port | H2 Console URL |
|----------|------|----------------|
| 🏭 **Inventory Processing Service** | 8082 | [http://localhost:8082/inventory-processing/h2-console/](http://localhost:8082/inventory-processing/h2-console/) |
| 🛒 **Order Processing Service** | 8081 | [http://localhost:8081/order-processing/h2-console/](http://localhost:8081/order-processing/h2-console/) |

### 🔑 Default Login Credentials for Inventory database

```properties
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:inventory
Username: sa
Password:
```

### 🔑 Default Login Credentials for Orders database

```properties
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:order
Username: sa
Password:
```

## 📦 Default Inventory Data

By default, the inventory database is preloaded with the following data when the application starts.  
This data is defined in the `data.sql` file located under:


You can **update this file** to modify the default records as per your needs.

---

### 🧾 Products Table

| Product ID | Name               | Description              | Date of Creation     | Date of Last Update  |
|-------------|--------------------|--------------------------|----------------------|----------------------|
| 1 | Paracetamol 500mg | Pain relief tablet | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 2 | Amoxicillin 250mg | Antibiotic capsule | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 3 | Vitamin C 1000mg | Immune system booster | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |

---

### 📦 Inventory Batches Table

| Batch ID | Batch No | Quantity Available | Expiry Date | Product ID | Date of Creation | Date of Last Update |
|-----------|-----------|--------------------|--------------|-------------|------------------|----------------------|
| 1 | P500-A1 | 200 | 2025-12-15 | 1 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 2 | P500-A2 | 150 | 2026-02-10 | 1 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 3 | P500-A3 | 100 | 2025-09-30 | 1 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 4 | AMX-B1 | 120 | 2026-03-01 | 2 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 5 | AMX-B2 | 200 | 2025-11-15 | 2 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 6 | VC-C1 | 300 | 2027-01-01 | 3 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 7 | VC-C2 | 250 | 2026-06-10 | 3 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |
| 8 | VC-C3 | 400 | 2025-08-25 | 3 | CURRENT_TIMESTAMP | CURRENT_TIMESTAMP |

---

### 🧑‍🍳 How to Modify Default Data

To change or add new data, open the file:

Please update the data.sql file placed at 
```inventory-processing-service/src/main/resources/data.sql```
