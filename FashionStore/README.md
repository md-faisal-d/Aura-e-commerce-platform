# Aura E-Commerce Platform

A premium, high-performance Java web application built on **Jakarta EE (Servlet 6.0/JSP 3.1)** and **MySQL**, designed with a modern glassmorphism purple/white aesthetic, optimized database operations, and rigorous load-testing benchmarks.

---

## 🚀 Key Features

* **Real Authentication System:** Secure user sign-up, login, and session-based logout backed by a relational database.
* **Stateful Shopping Cart:** Persistent shopping carts linked to user accounts. Prevents duplicate cart items and handles quantity updates instantly using AJAX.
* **Checkout & Order Flow:** Dynamic shipping address capture, payment configuration, transaction handling, and auto-clearing carts upon placement.
* **Order History Profile:** A unified profile dashboard displaying past orders and item summaries.
* **Product Reviews & Ratings:** Average star rating calculations and reviews submission directly on product detail pages.
* **Live Search Autocomplete:** A debounced client-side search box that query-binds matching records via a lightweight JSON API.
* **Wishlist System:** Heart-toggle functionality on product cards to add or remove catalog items from user wishlists.

---

## 🛠️ Architecture & Clean Code Patterns

The codebase is built from the ground up to follow enterprise software engineering standards:

* **MVC Design Pattern:** Complete separation of concerns:
  * **Model:** Plain Old Java Objects (POJOs) mapping relational database entities.
  * **View:** Modern JSTL-rendered JSPs utilizing CSS glassmorphism.
  * **Controller:** Jakarta HTTP Servlets routing user traffic and managing API response payloads (JSON/HTML).
* **Data Access Object (DAO) Pattern:** SQL queries are fully decoupled from routing logic and placed in specialized database helper files.
* **Jakarta EE WebFilters:** Security checks are handled globally via a central `AuthFilter` routing unauthenticated session accesses to the login page.
* **Maven Project Structure:** Modular dependency management (Gson, MySQL Connector, JSTL) with a automated `war` build configuration.

---

## 🔒 Security Best Practices

* **SQL Injection Prevention:** Parameterized SQL queries via `PreparedStatement` bind parameters across all query layers, preventing database exploitation.
* **Route Protection:** Global filters restrict private endpoints (`/cart`, `/checkout`, `/place-order`, `/orders`, `/wishlist`) to verified session cookies.
* **Atomic Cart Updates:** Enforces composited unique constraints `UNIQUE KEY (cart_id, variant_id)` in MySQL to prevent race conditions during rapid user clicks.

---

## 📊 Database Schema

![Database ER Diagram](database_diagram.png)

```
                  ┌──────────────┐
                  │    users     │
                  └──────┬───────┘
                         │ 1
                         │
                         │ 1..*
                  ┌──────▼───────┐
                  │   addresses  │
                  └──────┬───────┘
                         │ 1
                         │
                         │ 1..*
   ┌─────────────┐       │       ┌─────────────┐
   │    carts    ◄───────┼───────►   orders    │
   └──────┬──────┘       │       └──────┬──────┘
          │ 1            │              │ 1
          │              │              │
          │ 1..*         │              │ 1..*
   ┌──────▼──────┐       │       ┌──────▼──────┐
   │ cart_items  │       │       │ order_items │
   └──────┬──────┘       │       └──────┬──────┘
          │ 1..*         │              │ 1..*
          └──────────────┼──────────────┘
                         │
                         ▼
                  ┌──────────────┐
                  │   variants   │
                  └──────┬───────┘
                         │ 1..*
                         │
                         │ 1
                  ┌──────▼───────┐
                  │   products   │
                  └──────────────┘
```

---

## ⚡ Performance & Load Testing (JMeter Benchmarks)

The backend DAO layer was audited for N+1 query bottlenecks and stress-tested using **Apache JMeter** on a local execution stack (Tomcat 10.1 and MySQL).

### Key Test Results:

1. **Baseline Load (100 Users):**
   * **Throughput:** 10.2 requests/sec (612 requests/min)
   * **Average Latency:** **25 milliseconds**
   * **Error Rate:** 0.00%
2. **Scaled Warm-up (500 Users):**
   * **Throughput:** 50.0 requests/sec (3,000 requests/min)
   * **Average Latency:** **16 milliseconds** (Dropped due to JVM Just-In-Time compiling and MySQL InnoDB buffer caching).
   * **Error Rate:** 0.00%
3. **Peak Scalability (1,000 Users):**
   * **Throughput:** 100.0 requests/sec (6,000 requests/min)
   * **Average Latency:** **11 milliseconds**
   * **Error Rate:** 0.00%
4. **Stress Limit / Breaking Point (5,000 Users):**
   * **Throughput Limit:** **360.1 requests/sec** (Equivalent to **1.29 Million requests/hour**).
   * **Average Latency:** **2,427 milliseconds** (Tomcat thread limits reached).
   * **Error Threshold:** **8.18%** (Backlog queue socket timeouts).

---

## ⚙️ Setup & Installation

### Prerequisites
* Java Development Kit (JDK) 21
* Apache Tomcat 10.1.x
* MySQL Server 8.x
* Eclipse IDE for Enterprise Java Developers

### Steps
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/FashionStore.git
   ```
2. **Import into Eclipse:**
   * Open Eclipse -> File -> Import -> Existing Maven Projects.
   * Point the directory to your project root.
3. **Set Up Database:**
   * Open MySQL Workbench/Command line.
   * Run the SQL schemas located in `src/main/resources/` (or import your database dump).
   * Update database credentials in `com.fashionstore.util.DBConnection.java`.
4. **Deploy:**
   * Right-click on project -> **Run As** -> **Run on Server** (Select Apache Tomcat 10.1).
   * Navigate to `http://localhost:8080/FashionStore/` in your browser.
