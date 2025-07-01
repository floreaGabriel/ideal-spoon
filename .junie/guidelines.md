# KODES_HELP_APP Development Guidelines

This document provides essential information for developers working on the KODES_HELP_APP project.

## Build/Configuration Instructions

### Project Overview
KODES_HELP_APP is a Kotlin Multiplatform project with Compose Multiplatform for UI, targeting Android, iOS, and desktop platforms.

### Prerequisites
- JDK 11 or higher
- Android Studio or IntelliJ IDEA with Kotlin Multiplatform Mobile plugin
- Xcode (for iOS development)
- Gradle 8.0+ (included in the wrapper)

### Building the Project
1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd KODES_HELP_APP
   ```

2. **Build the project**:
   ```
   ./gradlew build
   ```

3. **Run on specific platforms**:
   - **Android**: 
     ```
     ./gradlew :composeApp:assembleDebug
     ```
   - **iOS**: Open the Xcode project in the `iosApp` directory and run it from Xcode
   - **Desktop**: 
     ```
     ./gradlew :composeApp:run
     ```

4. **Create distribution packages**:
   ```
   ./gradlew :composeApp:createDistributable
   ```

## Testing Information

### Test Structure
- Tests are organized in the `commonTest`, `androidTest`, `iosTest`, and `desktopTest` source sets
- Common tests are written using the Kotlin Test library
- Platform-specific tests can use platform-specific testing frameworks

### Running Tests
1. **Run all tests**:
   ```
   ./gradlew allTests
   ```

2. **Run tests for a specific platform**:
   ```
   ./gradlew desktopTest        # Desktop tests
   ./gradlew iosSimulatorArm64Test  # iOS simulator tests
   ./gradlew androidTest        # Android tests
   ```

3. **Run a specific test class**:
   ```
   ./gradlew :composeApp:testDebugUnitTest --tests "com.learn.kodes_help_app.GreetingTest"
   ```

### Adding New Tests
1. Create a new test file in the appropriate source set:
   - `composeApp/src/commonTest/kotlin/com/learn/kodes_help_app/` for common tests
   - `composeApp/src/androidTest/kotlin/com/learn/kodes_help_app/` for Android-specific tests
   - `composeApp/src/desktopTest/kotlin/com/learn/kodes_help_app/` for desktop-specific tests
   - `composeApp/src/iosTest/kotlin/com/learn/kodes_help_app/` for iOS-specific tests

2. Use the `kotlin.test` package for assertions and test annotations:
   ```kotlin
   class MyTest {
       @Test
       fun testSomething() {
           // Test implementation
           assertTrue(condition, "Error message")
       }
   }
   ```

3. For debugging tests, use the `[DEBUG_LOG]` prefix in print statements:
   ```kotlin
   println("[DEBUG_LOG] Test value: $value")
   ```

### Example Test
Here's a simple test for the `Greeting` class:

```kotlin
package com.learn.kodes_help_app

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingTest {

    @Test
    fun testGreetingContainsHello() {
        val greeting = Greeting()
        val result = greeting.greet()
        println("[DEBUG_LOG] Greeting result: $result")
        assertTrue(result.contains("Hello"), "Greeting should contain 'Hello'")
    }
}
```

## Additional Development Information

### Project Structure
- `composeApp/` - Main module containing all platform implementations
  - `src/commonMain/` - Common code shared across all platforms
  - `src/androidMain/` - Android-specific code
  - `src/iosMain/` - iOS-specific code
  - `src/desktopMain/` - Desktop-specific code
  - `src/commonTest/` - Common tests
  - `src/androidTest/` - Android-specific tests
  - `src/iosTest/` - iOS-specific tests
  - `src/desktopTest/` - Desktop-specific tests

### Code Style
- Follow Kotlin coding conventions
- Use `expect/actual` declarations for platform-specific implementations
- Keep platform-specific code to a minimum, preferring common code when possible

### Dependency Management
- Dependencies are managed in the `gradle/libs.versions.toml` file
- Add new dependencies to this file and reference them using the `libs` catalog

### Hot Reload
The project supports Compose Hot Reload for faster development:
```
./gradlew :composeApp:developDesktop
```

### Troubleshooting
- If you encounter build issues, try cleaning the project:
  ```
  ./gradlew clean
  ```
- For iOS build issues, ensure Xcode command line tools are installed:
  ```
  xcode-select --install
  ```
- For Gradle sync issues, try invalidating caches in Android Studio/IntelliJ IDEA
