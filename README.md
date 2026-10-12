# Student Management System

A simple CRUD web application for managing student records, built with **Spring MVC**, **Hibernate (JPA)**, **PostgreSQL**, and **JSP**. It is packaged as a WAR and deployed to a servlet container such as Apache Tomcat.

This project is Experiment 09 of a full-stack development lab series. It demonstrates a classic layered architecture (Controller → DAO → Entity) with pure Java-based Spring configuration (no `web.xml` or XML bean files).

## Features

- **Add** a student (register number, name, CGPA)
- **List** all students in a table
- **Search** a student by register number
- **Edit** an existing student (register number is read-only)
- **Delete** a student
- Automatic table creation through Hibernate (`hbm2ddl.auto=update`)

## Tech Stack

| Layer        | Technology                                  |
| ------------ | ------------------------------------------- |
| Language     | Java 17                                     |
| Web          | Spring MVC 5.3.39, JSP, JSTL 1.2            |
| Persistence  | Spring ORM 5.3.39, Hibernate 5.6.15, JPA 2.2 |
| Database     | PostgreSQL (driver 42.7.4)                  |
| Build        | Maven (WAR packaging)                       |
| Server       | Servlet 4.0 container (e.g. Tomcat 9)       |

> **Note:** This project uses the `javax.*` namespace, so it requires **Tomcat 9** (or another Servlet 4.0 container). Tomcat 10+ uses `jakarta.*` and will not work without migration.

## Project Structure

```
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/student/
│       │   ├── config/
│       │   │   ├── WebAppInitializer.java    # Registers the DispatcherServlet (replaces web.xml)
│       │   │   └── WebConfig.java            # DataSource, JPA/Hibernate, transactions, JSP view resolver
│       │   ├── controller/
│       │   │   └── StudentController.java    # Request handlers under /students
│       │   ├── dao/
│       │   │   └── StudentDAO.java           # Data access via EntityManager
│       │   └── entity/
│       │       └── Student.java              # JPA entity mapped to the "student" table
│       └── webapp/WEB-INF/views/
│           ├── index.jsp                     # Home page / add-student form
│           ├── students.jsp                  # Student list with edit/delete links
│           ├── search.jsp                    # Search result
│           └── edit.jsp                      # Update form
└── target/                                   # Maven build output (compiled classes)
```

## Prerequisites

- JDK 17+
- Maven 3.6+
- PostgreSQL 12+ running locally
- Apache Tomcat 9.x

## Getting Started

### 1. Create the database

```sql
CREATE DATABASE studentdb;
```

You do not need to create the table manually. Hibernate generates the `student` table on first startup.

### 2. Configure the database connection

Open `src/main/java/com/student/config/WebConfig.java` and update the credentials to match your PostgreSQL setup:

```java
dataSource.setUrl("jdbc:postgresql://localhost:5432/studentdb");
dataSource.setUsername("postgres");
dataSource.setPassword("your_password");
```

### 3. Build the WAR

```bash
mvn clean package
```

This produces `target/StudentManagement.war`.

### 4. Deploy

Copy the WAR into Tomcat's `webapps/` directory and start Tomcat:

```bash
cp target/StudentManagement.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh        # Windows: startup.bat
```

Alternatively, run it from Eclipse or IntelliJ by adding the project to a configured Tomcat 9 server.

### 5. Open the app

```
http://localhost:8080/StudentManagement/students/
```

## Routes

All routes are prefixed with `/StudentManagement/students`.

| Method | Path             | Description                              |
| ------ | ---------------- | ---------------------------------------- |
| GET    | `/`              | Home page with the add-student form      |
| POST   | `/add`           | Save a new student                       |
| GET    | `/list`          | List all students                        |
| GET    | `/search?regno=` | Look up a student by register number     |
| GET    | `/edit/{regno}`  | Show the edit form                       |
| POST   | `/update`        | Save changes to a student                |
| GET    | `/delete/{regno}`| Delete a student                         |

## Database Schema

Generated automatically from the `Student` entity:

| Column | Type              | Notes       |
| ------ | ----------------- | ----------- |
| regno  | integer           | Primary key |
| name   | varchar(255)      |             |
| cgpa   | double precision  |             |

## Known Limitations and Suggested Improvements

This is a learning project, so a few things are intentionally simple. If you want to extend it:

- **Credentials are hardcoded** in `WebConfig.java`. Move them to environment variables or an external `application.properties` file, and avoid committing real passwords.
- **Navigation links:** the home page's "Search Student" link points to `/students/search` without a `regno` parameter, and the "Back" links on the list page point to `/StudentManagement/`, which is not mapped. Use `/StudentManagement/students/` instead, and add a search form to `index.jsp`.
- **Hardcoded context path:** JSPs use `/StudentManagement/...`. Use `${pageContext.request.contextPath}` so the app works under any WAR name.
- **Delete uses GET**, which is unsafe (links can be triggered by crawlers or prefetching). Switch to a POST form.
- **JSP scriptlets** print values unescaped (XSS risk). Prefer JSTL (`<c:forEach>`, `<c:out>`).
- **No validation or error handling:** adding a duplicate register number or searching for a nonexistent student in the edit view can cause errors. Add Bean Validation and a global exception handler.
- **No tests:** consider adding JUnit and an H2 in-memory database for DAO tests.
- **`DriverManagerDataSource`** does not pool connections. Use HikariCP or Tomcat JDBC pool for anything beyond a demo.

## License

This project is for educational purposes. Add a license of your choice if you plan to publish it.
