# Spring Boot CORS Configuration Fix

Your Spring Boot backend needs these changes to fix the CORS error:

## Issue 1: JwtRequestFilter - Wrong Path Matching
The filter checks for `/register` but your API uses `/api/v1.0/register`

**Fix:** Update `JwtRequestFilter.java`

Change:
```java
private static final List<String> PUBLIC_URLS = List.of("/login", "/register", "/send-reset-otp", "/reset-password", "/reset-password", "logout");
```

To:
```java
private static final List<String> PUBLIC_URLS = List.of(
    "/api/v1.0/login", 
    "/api/v1.0/register", 
    "/api/v1.0/send-reset-otp", 
    "/api/v1.0/reset-password", 
    "/api/v1.0/logout"
);
```

## Issue 2: SecurityConfig - CORS Not Properly Tied to Configuration Source
Change in `SecurityConfig.java`:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Pass the config source
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/v1.0/login", "/api/v1.0/register", "/api/v1.0/send-reset-otp", "/api/v1.0/reset-password", "/api/v1.0/logout").permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ✅ allow preflight
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex.authenticationEntryPoint(customauthenticationEntryPoint));

    return http.build();
}
```

## Issue 3: SimpleCorsFilter
Keep it as is - it will handle preflight OPTIONS requests.

---

**After making these changes:**
1. Restart your Spring Boot backend
2. Refresh your React app
3. The CORS error should be gone!

If still getting errors, check browser console for actual error message.
