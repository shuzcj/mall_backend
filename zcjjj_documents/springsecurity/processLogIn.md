### Process Overview of Spring Security Login:

1. **Client Request**: The login process begins when a client sends a request (typically a POST request) to the authentication endpoint with credentials (username and password).

2. **Authentication Filter**:
    - **Class**: `UsernamePasswordAuthenticationFilter`
    - **Role**: This filter intercepts the login request, extracts the credentials, and initiates the authentication process.

3. **Authentication Manager**:
    - **Interface**: `AuthenticationManager`
    - **Role**: The filter passes the credentials to the `AuthenticationManager`, which coordinates the authentication process.

4. **Authentication Provider**:
    - **Interface**: `AuthenticationProvider`
    - **Role**: `AuthenticationManager` delegates the task of authenticating the credentials to one or more `AuthenticationProvider` implementations.

5. **UserDetailsService**:
    - **Interface**: `UserDetailsService`
    - **Role**: Typically implemented by you, this interface loads user-specific data (like username, password, and roles) from a data source (e.g., database).

6. **UserDetails**:
    - **Interface**: `UserDetails`
    - **Role**: `UserDetailsService` returns a `UserDetails` object containing user data, which is then used by `AuthenticationProvider` to verify the credentials.

7. **Password Encoder**:
    - **Interface**: `PasswordEncoder`
    - **Role**: The `AuthenticationProvider` uses this interface to compare the password provided by the user with the one stored in the database.

8. **Authentication Success/Failure**:
    - **Class**: `AuthenticationSuccessHandler` / `AuthenticationFailureHandler`
    - **Role**: If authentication is successful, a `SuccessHandler` processes the outcome (e.g., generating a JWT token). If it fails, a `FailureHandler` handles the response (e.g., returning an error message).

9. **Security Context**:
    - **Class**: `SecurityContextHolder`
    - **Role**: On successful authentication, the authenticated user details are stored in the `SecurityContextHolder`, which is used to manage security context across the application.

### Interfaces You Should Implement:

- **`UserDetailsService`**: To load user-specific data from your database.
- **`UserDetails`**: To define how user data is structured.
- **`AuthenticationProvider`** (Optional): To customize how authentication is handled beyond the default provider (e.g., if you have a custom authentication mechanism).

### Why Spring Doesn’t Implement These:

- **Customization**: Spring Security provides flexibility. It doesn’t implement `UserDetailsService` and `UserDetails` because it allows developers to define how and where user data is retrieved (e.g., from a database, LDAP, etc.), which varies greatly across applications.
- **Extendibility**: By not providing a one-size-fits-all solution, Spring Security encourages you to extend or replace default implementations to suit your specific authentication logic and security requirements.
