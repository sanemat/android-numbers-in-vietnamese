# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android application for learning Vietnamese numbers. The app displays random numbers and their Vietnamese translations, with text-to-speech functionality for pronunciation practice.

## Architecture

- **Single Activity App**: `MainActivity.kt` is the main and only activity
- **View Binding**: Uses Android's view binding for UI components (`ActivityMainBinding`)
- **External Library**: Uses `jp.sane.numbertovietnamese.numberToVietnamese` from GitHub dependency for number conversion
- **Text-to-Speech**: Integrated Vietnamese TTS for pronunciation
- **SharedPreferences**: Stores user settings (reveal options, auto-play)

## Development Commands

### Build and Test
```bash
# Run tests
./gradlew test

# Run lint checks
./gradlew lint

# Build debug APK
./gradlew assembleDebug

# Build release AAB
./gradlew bundleRelease

# Clean build
./gradlew clean

# Run instrumented tests
./gradlew connectedAndroidTest
```

### Development Setup
- **Target SDK**: 35
- **Min SDK**: 21
- **Java Version**: 21
- **Kotlin Version**: 1.9.25
- **Build Tools**: Android Gradle Plugin 8.7.3, Gradle 8.12

## Key Dependencies
- `com.github.sanemat:maven-number-to-vietnamese:0.5.0` - Core number conversion library
- AndroidX AppCompat and ConstraintLayout for UI
- JUnit and Espresso for testing

## Main Components

### MainActivity.kt
- Single activity handling all UI interactions
- Number generation with weighted randomization (favors smaller numbers)
- Settings persistence via SharedPreferences
- Vietnamese TTS integration with fallback error handling
- Edge-to-edge support preparation for API 35

### API 35 Migration Notes
- Edge-to-edge enforcement handled with `enableEdgeToEdge()` call
- `windowOptOutEdgeToEdgeEnforcement` in `values-v35/styles.xml` for compatibility
- Lint checks ensure API compatibility across SDK versions

### Build Configuration
- `app/build.gradle` - Main app configuration with dependencies
- `build.gradle` - Root project configuration
- View binding enabled for type-safe UI access

## Testing
- Unit tests: Use `./gradlew test`
- Instrumented tests: Use `./gradlew connectedAndroidTest`
- CI runs on GitHub Actions with JDK 21 and uses built-in Gradle caching

## Versioning
Version format: major.minor.patch with versionCode based on major version (e.g., 3.0.0 = versionCode 3000000)