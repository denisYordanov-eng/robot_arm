# Robot Arm - Spring Boot backend + Arduino

A Spring Boot REST API that controls a robotic arm via a serial link to an Arduino and persists real‑time coordinates and movement logs in a relational database. The project demonstrates backend ↔ hardware integration, data persistence, and an end‑to‑end demo workflow.

## Features
- REST endpoints to send movement commands to the arm
- Serial communication to an Arduino (jSerialComm)
- Persistence of coordinates and movement logs using Spring Data JPA
- Example Arduino sketches included in ArduinoCode/
- Maven wrapper included for reproducible builds

## Stack
- Languages: Java 21 (backend), C++ (Arduino sketches)
- Framework / runtime: Spring Boot (Maven)
- Notable libraries: spring-boot-starter-webmvc, spring-boot-starter-data-jpa, jSerialComm, MySQL connector


## Quickstart - run the backend locally

Prerequisites
- Java 21 JDK
- (Optional) Maven — mvnw is included so not required
- A relational database (MySQL/Postgres). MySQL is configured as an example in pom.xml.


Start the backend
```bash
# Windows (PowerShell)
.\mvnw.cmd spring-boot:run
```

Run tests
```bash
cd backend/robot_arm_demo/demo
./mvnw test
```

Configuration
- Edit `src/main/resources/application.properties` (or create `application.properties.sample`) to set:
  - spring.datasource.url
  - spring.datasource.username
  - spring.datasource.password
  - serial.port (COM3 or COM4)
  - serial.baud (9 600)


## Arduino firmware
Open the sketches in `ArduinoCode/` with the Arduino IDE (or use the Arduino CLI) and upload to the target board. Ensure baud rate and port match the backend configuration.

## Example API usage

To interact with the robot arm, you must first establish a connection to the serial port, and then send movement commands.

### Step 1: Establish Serial Connection
Before sending any movements, connect the backend to your Arduino's serial port.

*   **Endpoint:** `POST /api/serial/connect?portName=COM3` (Replace `COM3` with your actual port or `COM4`)
*   **Request Type:** `POST` (Query Parameter)
*   **Expected Response:** `This serial is connected`

---

### Step 2: Move the Robot Arm
Once connected, you can send movement commands to individual joints.

*   **Endpoint:** `POST /api/servo`
*   **Request Type:** `POST` (JSON Body)
*   **Supported Joints:** `"ELBOW"`, `"GRIPPER"`, `"WRIST_ROLL"`, `"WRIST_PITCH"`, `"SHOULDER"`, `"BASE"`

**Postman Request Body Example:**
```json
{
    "servoMotorName":"ELBOW",
    "angle":10
}
```

## Future Improvements

- **DTO Integration:** Refactor request payloads to use DTOs (Data Transfer Objects) for better validation and architecture.
- **Multiple Joint Control:** Upgrade the API to accept and process multiple joint movements in a single request.
- **Web Frontend:** Build a simple UI with sliders to control the robot arm in real-time.
- **Asynchronous Processing:** Make serial communication asynchronous to prevent blocking the main server threads.
- **Spring Security:** Secure the control endpoints to ensure only authorized users can send commands to the physical hardware.
