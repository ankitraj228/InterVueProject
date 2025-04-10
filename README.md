# IntervuePro 🧪

Automated end-to-end testing of [Intervue.io](https://www.intervue.io) using **Selenium WebDriver** and **SafariDriver**, written in **Java**.

This test automates:
- Navigation through the homepage
- Login functionality
- Search interaction
- Logout flow

---

## 📁 Project Structure

```
IntervuePro/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── Main.java         # Optional main class
│   │   │       └── LoginTest.java    # Core Selenium test script
│   │   └── resources/
│   └── test/
├── target/                          # Compiled class files
├── pom.xml                          # Maven project file with dependencies
└── README.md                        # This file
```

---

## 🛠️ Technologies & Tools

- Java 11+
- Selenium WebDriver
- SafariDriver (macOS only)
- Maven for dependency management
- WebDriverWait & Actions API

---

## ⚙️ How to Run

### ✅ Prerequisites

- macOS with Safari browser
- Enable **Remote Automation** in Safari:
  - Open Safari → Develop → Allow Remote Automation
- Install Java JDK (11 or higher)
- Install Maven

### 🔧 Setup

1. **Clone the project**
   ```bash
   git clone https://github.com/ankitraj228/IntervuePro.git
   cd IntervuePro
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run the script**
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.LoginTest"
   ```

---

## 🔐 Login Credentials

Used for testing only:
```
Email:   
Password:
```
> ⚠️ Please **do not share or reuse** credentials for any purpose other than testing automation.

---

## 📌 Flow Summary

1. Launch Intervue.io homepage  
2. Hover over menu items for UX simulation  
3. Click "Login" → Switch to new tab  
4. Perform login  
5. Perform search action  
6. Click profile → Logout  
7. End session

---

## 🧩 Future Enhancements

- Add test assertions with TestNG or JUnit
- Parametrize input values (credentials, URLs)
- Integrate with Allure or ExtentReports
- Cross-browser support (Chrome, Firefox)

---
