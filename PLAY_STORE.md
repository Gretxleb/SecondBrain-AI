# Play Store Publishing

## Setup

1. Create a Google Play Console account.
2. Register your app package `com.secondbrain.ai`.
3. Configure pricing, distribution, and privacy policy.

## Build

- `./gradlew bundleRelease`

## Signing

1. Add a release signing config to `app/build.gradle.kts`.
2. Create a keystore file and secure it.
3. Set environment variables for `STORE_FILE`, `STORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`.

## Upload

1. Upload the generated `.aab` file from `app/build/outputs/bundle/release/`.
2. Provide store listing assets and feature graphics.
3. Enable internal testing, closed testing, or production rollout.

## Release

- Review the release summary and submit for review.
- Monitor crash reports in Firebase Crashlytics and Play Console.
