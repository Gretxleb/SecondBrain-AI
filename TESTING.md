# Testing Guide

## Android tests

- `./gradlew test` — run unit tests
- `./gradlew connectedAndroidTest` — run instrumented tests on a connected device or emulator
- `./gradlew lint` — run static analysis

## Backend tests

- `cd backend`
- `npm install`
- `npm test`

## Local development tests

- `docker compose up --build` to start the backend and database
- `./gradlew clean test` to run Android tests after backend changes

## CI validation

- Android CI: `.github/workflows/android.yml`
- Backend CI: `.github/workflows/backend.yml`
