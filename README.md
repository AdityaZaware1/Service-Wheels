# 🚗 Service-Wheels

**Service-Wheels** is a microservices-based vehicle repair service booking platform that allows users to:
- Discover mechanic shops
- View available services
- Book appointments
- Make secure payments via Razorpay
- Enjoy secure access using Keycloak
- Communicate via Kafka for asynchronous event processing

---

## 🧱 Microservices Architecture

This system is built using Spring Boot microservices and includes the following services:

| Service Name      | Description                                    | DB         | Port |
|-------------------|------------------------------------------------|------------|------|
| User Service      | Manages user data and authentication roles     | MySQL      | 8081 |
| Shop Service      | Handles mechanic shop details and registration | PostgreSQL | 8082 |
| SERVICES OFFERING | Lists services offered by mechanic shops       | MySQL      | 8083 |
| Booking Service   | Handles appointment scheduling                 | PostgreSQL | 8084 |
| Payment Service   | Processes payments using Razorpay              | MySQL      | 8085 |
| Gateway Service   | API Gateway using Spring Cloud Gateway         | N/A        | 8090 |
| Auth Server       | Keycloak for OAuth2 authentication             | PostgreSQL | 9090 |
| Eureaka Server    | Registers all Services                         | N/A        | 8763 |

---

## 🧰 Tech Stack

- **Spring Boot (Microservices)**
- **Spring Cloud Gateway**
- **Spring Security + Keycloak**
- **Razorpay API** for Payment Integration
- **Apache Kafka** for event-driven communication
- **Docker & Docker Compose**
- **MySQL & PostgreSQL** as Databases

---

## 📦 Kafka Topics

- `booking-events`: Emitted by Booking Service when a new appointment is booked
- `payment-status`: Emitted by Payment Service when payment status updates

## 🐳 Docker Setup

Use the following command to spin up all containers:

```bash
docker-compose up --d
```

---

## 🔐 Security

- Keycloak secures all endpoints via OAuth2 and JWT.
- Users are assigned roles: `ROLE_USER`, `ROLE_MECHANIC`, `ROLE_ADMIN`.

---

## 📂 Folder Structure

```
service-wheels/
├── user-service/
├── shop-service/
├── available-service/
├── booking-service/
├── payment-service/
├── gateway-service/
├── auth-server/ (Keycloak)
├── docker-compose.yml
├── README.md
```

---

## 🛠️ How to Run

1. Clone the repo
2. Configure environment variables (Razorpay, DB, Kafka)
3. Start services using Docker Compose
4. Access:
    - API Gateway: `http://localhost:8090`
    - Eureka Server: `http://localhost:8761/eureka`
    - Keycloak: `http://localhost:9090`
    - Services via Gateway endpoints

---

## 🚀 Future Enhancements

- Add service ratings & reviews
- Email/SMS notification support
- Admin dashboard for analytics
- Mobile app integration

---

## 📧 Contact

Feel free to reach out if you'd like to contribute or need help setting it up!

- Email: zawareaditya04@gmail.com
- LinkedIn: https://www.linkedin.com/in/aditya-zaware-310904259/

---