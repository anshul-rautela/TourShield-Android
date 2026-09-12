# TourShield

TourShield is an Android tourist safety and emergency assistance application built with modern Android development standards, Jetpack Compose, Material 3, and Material You design principles.

## Features

- **Material 3 / Material You Dark UI**: Clean, accessible design adhering to Android 16 guidelines.
- **Manual SOS Switch**: Prominent stadium/pill-shaped toggle to quickly activate emergency SOS mode.
- **Live Location Sharing Status**: Displays trusted contacts (e.g. Father, Mother) with dynamic countdown timer updating location ping status ("Last shared X minutes ago").
- **Modular Navigation**: Type-safe navigation with Compose Navigation.
- **MVVM Architecture**: State-driven UI with ViewModel, Coroutines, and StateFlow.

## Tech Stack

- **Platform**: Android (Target SDK 36, Min SDK 35)
- **Language**: Kotlin 2.0+
- **UI Toolkit**: Jetpack Compose & Material 3
- **Tooling & Build**: Gradle (Kotlin DSL, Version Catalogs)
- **Java Toolchain**: JDK 17

## Getting Started

### Prerequisites

- Android Studio Ladybug / Meerkat or newer
- Android SDK 35+
- JDK 17

### Build & Run

1. Clone the repository:
   ```bash
   git clone git@github.com:anshul-rautela/TourShield-Android.git
   ```
2. Open the project in Android Studio.
3. Allow Gradle sync to complete.
4. Run the project on an Android device or emulator running API 35+.

Alternatively, build from the command line:
```bash
./gradlew assembleDebug
```

## License

All rights reserved.
