### **Basic Project Information**
- Title: `Bus Reservation API`
- Version: `v0`
- API Base URL: `http://localhost:8080`

### Available Endpoints:
#### **Bus Schedule**
- **Add Schedule**
    - `POST /api/schedule/add`
    - Adds a new bus schedule.
- **Update Schedule**
    - `PUT /api/schedule/update/{id}`
    - Updates a bus schedule by ID.
- **Get Schedule by Route Name**
    - `GET /api/schedule/{routeName}`
    - Retrieves a bus schedule by the route name.
- **Get All Schedules**
    - `GET /api/schedule/all`
    - Retrieves all bus schedules.

#### **Bus Route**
- **Add Route**
    - `POST /api/route/add`
    - Adds a new bus route.
- **Update Route**
    - `PUT /api/route/update/{id}`
    - Updates a bus route by ID.
- **Get Route by Name**
    - `GET /api/route/{routeName}`
    - Retrieves a bus route by its name.
- **Query Route by Cities**
    - `GET /api/route/query`
    - Retrieves a route based on the departure and destination cities.
- **Get All Routes**
    - `GET /api/route/all`
    - Retrieves all bus routes.

#### **City**
- **Add City**
    - `POST /api/city/add`
    - Adds a new city.
- **Update City**
    - `PUT /api/city/update/{id}`
    - Updates a city by ID.
- **Get All Cities**
    - `GET /api/city/all`
    - Retrieves all cities.
- **Delete City**
    - `DELETE /api/city/delete/{id}`
    - Deletes a city by ID.

#### **Bus**
- **Add Bus**
    - `POST /api/bus/add`
    - Adds a new bus.
- **Update Bus**
    - `PUT /api/bus/update/{id}`
    - Updates a bus by ID.
- **Get All Buses**
    - `GET /api/bus/all`
    - Retrieves all buses.
- **Delete Bus**
    - `DELETE /api/bus/delete/{id}`
    - Deletes a bus by ID.

#### **Reservation**
- **Add Reservation**
    - `POST /api/reservation/add`
    - Adds a new reservation.
- **Get Reservations by Schedule and Date**
    - `GET /api/reservation/query`
    - Retrieves reservations based on the bus schedule ID and departure date.
- **Get All Reservations**
    - `GET /api/reservation/all`
    - Retrieves all reservations.
- **Get Reservations by User**
    - `GET /api/reservation/all/{userName}`
    - Retrieves all reservations made by a specific user.

#### **Authentication**
- **Sign Up**
    - `POST /api/auth/signup`
    - Signs up a new user.
- **Log In**
    - `POST /api/auth/login`
    - Logs in a user.
- **Get Customer**
    - `GET /api/auth/{userName}`
    - Retrieves customer information by username.

---

### API Schemas

#### **Bus**
```json
{
  "busId": 1,
  "busName": "string",
  "busType": "string",
  "totalSeat": 50,
  "busNumber": "string"
}
```

#### **Bus Route**
```json
{
  "routeId": 1,
  "routeName": "string",
  "cityFrom": "string",
  "cityTo": "string",
  "distanceInKm": 120.0
}
```

#### **Bus Schedule**
```json
{
  "scheduleId": 1,
  "bus": {...},
  "busRoute": {...},
  "departureTime": "string",
  "ticketPrice": 20,
  "discount": 5,
  "processingFee": 2
}
```

#### **Reservation**
```json
{
  "reservationId": 1,
  "appUser": {...},
  "busSchedule": {...},
  "timestamp": 1609459200,
  "departureDate": "string",
  "totalSeatBooked": 2,
  "seatNumbers": "string",
  "reservationStatus": "string",
  "totalPrice": 50
}
```

#### **City**
```json
{
  "cityId": 1,
  "cityName": "string"
}
```

#### **AppUsers**
```json
{
  "id": 1,
  "userName": "string",
  "password": "string",
  "role": "string",
  "customerName": "string",
  "mobile": "string",
  "email": "string"
}
```

#### **SignUpRequestModel**
```json
{
  "userName": "string",
  "password": "string",
  "role": "string",
  "customerName": "string",
  "email": "string",
  "mobile": "string"
}
```

#### **Response Models**
Example of a response model used throughout the API:
```json
{
  "statusCode": 200,
  "message": "OK",
  "response": {...}  // Dynamic content based on the schema type
}
```

---

### How to Use this API

1. **Authentication**: You need to sign up and log in using the authentication endpoints.
2. **Bus Scheduling**: Use the bus schedule endpoints to add or query bus schedules.
3. **Bus Routes**: Add, update, or query bus routes.
4. **Reservations**: Make reservations for a given schedule and view or cancel them.

---