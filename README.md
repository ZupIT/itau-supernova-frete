# Itau Supernova Frete

This project is a Spring Boot application for managing freight services. Below are the instructions to set up and run the project locally.

---

## Prerequisites

- **Java**: Ensure you have Java 21 or higher installed.
- **Maven**: Ensure Maven is installed and configured.
- **IDE**: IntelliJ IDEA or any other Java IDE is recommended.

---

## Running the Application Locally

### 1. Set the Active Profile
To run the application locally, you must set the Spring profile to `local`. This can be done by adding the following argument when starting the application:

```bash
-Dspring.profiles.active=local
```

Alternatively, you can set the profile in your IDE's run configuration.

---

### 2. Environment Variables

The following environment variables must be configured to run the application locally. You can pass them as `VM options` when running the application.

Example configuration:

```bash
-Dspring.profiles.active=local
-DSUPPLIER_URL=https://cartoes-qa.staging.gateway.zup.me/itau-supernova-rom-sds
-DSUPPLIER_READ_TIMEOUT=30000
-DSUPPLIER_CONNECT_TIMEOUT=30000
-DSUPPLIER_X_APPLICATION_KEY=de2094c0f944013906632ea299362860
-DSUPPLIER_CLIENT_ID=9b307d2afbf243bd8bb875ccd42c07c3
-DSUPPLIER_CLIENT_SECRET=b91dc410c1b642e5861d3adbecf60ae8
```

### Steps to Configure in IntelliJ IDEA:

1. Open `Run > Edit Configurations`.
2. Select your application's run configuration.
3. Add the above variables in the **VM options** field.
4. Save and run the application.

---


## Build and Run

To build and run the application, use the following Maven commands:

1. **Build the project**:
   ```bash
   mvn clean install
   ```

2. **Run the application**:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=local -DSUPPLIER_URL=https://cartoes-qa.staging.gateway.zup.me/itau-supernova-rom-sds -DSUPPLIER_READ_TIMEOUT=30000 -DSUPPLIER_CONNECT_TIMEOUT=30000 -DSUPPLIER_X_APPLICATION_KEY=de2094c0f944013906632ea299362860 -DSUPPLIER_CLIENT_ID=9b307d2afbf243bd8bb875ccd42c07c3 -DSUPPLIER_CLIENT_SECRET=b91dc410c1b642e5861d3adbecf60ae8"
   ```

---

## Additional Notes

- Ensure the `application-local.yml` file is properly configured for local development.
