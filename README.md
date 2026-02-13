# GearFlow

GearFlow is an Android application designed with modern Android development practices, utilizing Clean Architecture and the latest Jetpack libraries.

## 🏗 Architecture

The project follows Clean Architecture principles and is divided into several modules:

- **`:app`**: The Android application module. It contains the UI (Jetpack Compose), ViewModels, and navigation logic.
- **`:domain`**: A pure Kotlin module containing business logic, use cases, and repository interfaces.
- **`:data`**: Handles data persistence and networking. It implements repository interfaces from the domain layer using Room and Retrofit.
- **`:core`**: Contains shared utilities, base classes, and cross-cutting concerns used by other modules.

## 🛠 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for building native UI.
- **Dependency Injection**: [Koin](https://insert-koin.io/) for lightweight dependency injection.
- **Concurrency**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) and Flow.
- **Networking**: [Retrofit](https://square.github.io/retrofit/) with [Moshi](https://github.com/square/moshi) for API communication.
- **Database**: [Room](https://developer.android.com/training/data-storage/room) for local data persistence.
- **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation) with [Accompanist Navigation Animation](https://github.com/google/accompanist/tree/main/navigation-animation).
- **Static Analysis**: [Detekt](https://detekt.dev/) for Kotlin linting and code quality.

## 🚀 Getting Started

### Prerequisites

- Android Studio Flamingo (2022.2.1) or newer.
- JDK 11 or higher.
- Android SDK 33.

### Building the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/alex-shteinle/gear-flow.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the `:app` module on an emulator or physical device.

## 🧪 Testing

The project includes unit tests for business logic and data layers.

To run tests:
```bash
./gradlew test
```

## 🧹 Code Quality

Detekt is used to maintain code quality. To run detekt on all modules:
```bash
./gradlew detektAll
```
To generate a baseline:
```bash
./gradlew detektGenerateBaseline
```

## ⚙️ Project Configuration

- **Min SDK**: 23
- **Target SDK**: 33
- **Compile SDK**: 33
- **Kotlin Version**: 1.7.20
- **Gradle Version**: 8.0.2
