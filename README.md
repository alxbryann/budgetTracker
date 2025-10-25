# BudgetTracker

BudgetTracker is a Java Swing desktop app to organize, track, and visualize your incomes and recurring financial obligations on a monthly calendar. It uses JPA (EclipseLink) with MySQL for persistence and a modern FlatLaf-based UI.

---

## Highlights

- Calendar-first view of transactions (incomes/obligations)
- Weekly or monthly recurring transactions
- Color-coded items and quick at-a-glance month overview
- Modern Swing UI (FlatLaf, MigLayout) with custom title bar
- MySQL persistence via JPA (EclipseLink)

---

## Tech stack

- Language: Java 21+ (built with Maven)
- UI: Swing, FlatLaf, MigLayout, LGoodDatePicker
- Persistence: JPA (EclipseLink), MySQL Connector/J
- Architecture: MVC (Model–View–Controller)

---

## Requirements

- JDK 21 or newer
- Maven 3.8+
- MySQL 8.x running locally

Optional but recommended: an IDE such as NetBeans/IntelliJ IDEA.

---

## Database setup

The default persistence unit is `focPU`. The app connects to a local MySQL instance using the configuration in `src/main/resources/META-INF/persistence.xml`:

```
jdbc:mysql://localhost:3306/foc_db
user: root
password: root
```

Notes:

- By default, schema generation is set to `create` which recreates tables on startup. For preserving data, change it to `update` or similar according to your needs.
- Update credentials/URL in `persistence.xml` to match your environment.

To prepare a fresh database:

1) Create the database in MySQL

```sql
CREATE DATABASE foc_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```

2) Ensure the user has permissions (example for the default `root`)

```sql
GRANT ALL PRIVILEGES ON foc_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

---

## Build and run

Build the shaded jar (includes dependencies):

```powershell
mvn -q -DskipTests package
```

Run the app:

```powershell
java -jar .\foc\target\foc-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Entry point: `com.alxbryann.foc.model.Main`.

If you prefer running from an IDE, set the working directory to the project root and ensure resources are on the classpath.

---

## Configuration

- App icon/resources are under `src/main/resources/img/`.
- JPA configuration is in `src/main/resources/META-INF/persistence.xml`.
- Recurring logic and calendar calculations live under `com.alxbryann.foc.model`.
- Main window and tabs are under `com.alxbryann.foc.view`.

---

## Project structure (simplified)

```
foc/
   pom.xml
   src/
      main/
         java/
            com/alxbryann/foc/
               model/            # Calendar, Day, Transaction, RepetitiveTransaction, Controller, Model, Main
               persistence/      # JPA controllers and persistence facade
               view/             # Swing UI (FlatLaf, custom title bar, tabs)
         resources/
            META-INF/persistence.xml
            img/                # Icons and app logo
```

---

## Contributing

Contributions are welcome:

1. Fork the repo
2. Create a feature branch
3. Commit your changes with clear messages
4. Open a Pull Request

Ideas to help:

- Monthly/annual reports and export (CSV/PDF)
- Notifications/reminders
- More themes/localization
- Tests and documentation

---

## License

No license has been specified yet. If you intend to use or distribute this software, please add a LICENSE file to the repository and update this section accordingly.

---

## A note about recent changes

The codebase was streamlined around Transactions (incomes/obligations) and recurring items. Legacy FinancialObligation classes were removed/renamed, and persistence was consolidated in JPA controllers. The README now reflects the Maven build, Java 21+, EclipseLink JPA with MySQL, and the updated entry point/UI structure.
