# Nearby Backend

Backend pentru aplicația iOS Nearby, dezvoltat în Java Spring Boot.

## Tehnologii

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (pentru dezvoltare)
- **Maven**

## Structura Proiectului

```
src/main/java/dev/nearby/backend/
├── NearbyBackendApplication.java    # Clasa principală
├── controller/
│   └── UserController.java          # API endpoints
├── service/
│   └── UserService.java             # Business logic
├── repository/
│   └── UserRepository.java          # Database operations
├── model/
│   └── User.java                    # Entity
├── dto/
│   ├── CreateUserRequest.java       # Request DTO
│   └── CreateUserResponse.java      # Response DTO
└── exception/
    └── GlobalExceptionHandler.java  # Error handling
```

## API Endpoints

### Create User
- **POST** `/api/users`
- **Body:**
```json
{
  "username": "johnny123",
  "fullName": "John Doe",
  "age": 25,
  "gender": "MALE",
  "bio": "Hello, I'm John!",
  "interests": ["Hiking", "Photography", "Music"],
  "profilePictureUrl": "https://example.com/photo.jpg",
  "bluetoothEnabled": true
}
```

### Get User by ID
- **GET** `/api/users/{id}`

### Get User by Username
- **GET** `/api/users/username/{username}`

### Health Check
- **GET** `/api/users/health`

## Rularea Aplicației

### Cerințe
- Java 17 sau mai nou
- Maven

### Comenzi

1. **Compilează proiectul:**
```bash
mvn clean compile
```

2. **Rulează aplicația:**
```bash
mvn spring-boot:run
```

3. **Sau construiește JAR-ul:**
```bash
mvn clean package
java -jar target/nearby-backend-0.0.1-SNAPSHOT.jar
```

## Acces

- **API:** http://localhost:8080/api/users
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: `password`

## Testare

### Cu curl:
```bash
# Create user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "nickname": "test_user",
    "age": 25,
    "gender": "MALE",
    "bio": "Test bio",
    "interests": ["Hiking", "Music"]
  }'

# Get user by ID
curl http://localhost:8080/api/users/1

# Health check
curl http://localhost:8080/api/users/health
```

## Integrare cu iOS App

Pentru a integra cu aplicația iOS, înlocuiește TODO-ul din `OnboardingSummaryView.swift`:

```swift
// În OnboardingSummaryView.swift, înlocuiește:
// TODO: Add create account request aici

// Cu:
createAccountRequest()
```

Și adaugă funcția:

```swift
private func createAccountRequest() {
    isLoading = true
    
    let url = URL(string: "http://localhost:8080/api/users")!
    var request = URLRequest(url: url)
    request.httpMethod = "POST"
    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
    
    let requestData: [String: Any] = [
        "nickname": viewModel.data.nickname,
        "age": viewModel.data.age ?? 18,
        "gender": viewModel.data.gender?.rawValue.uppercased() ?? "PREFER_NOT_TO_SAY",
        "bio": viewModel.data.bio,
        "interests": viewModel.data.interests,
        "bluetoothEnabled": viewModel.data.bluetoothEnabled,
        "locationEnabled": viewModel.data.locationEnabled
    ]
    
    request.httpBody = try? JSONSerialization.data(withJSONObject: requestData)
    
    URLSession.shared.dataTask(with: request) { data, response, error in
        DispatchQueue.main.async {
            self.isLoading = false
            
            if let error = error {
                print("Error: \(error)")
                return
            }
            
            if let httpResponse = response as? HTTPURLResponse,
               httpResponse.statusCode == 201 {
                self.showSuccess = true
            } else {
                print("Failed to create account")
            }
        }
    }.resume()
}
```

## Deployment

Pentru deployment, modifică `application.properties` pentru a folosi o bază de date reală (PostgreSQL, MySQL, etc.) în loc de H2. 