## Aroundme

You can download the apk file in the apk folder [Download APK](https://github.com/zainfikrih/android-aroundme/raw/master/apk/app-debug.apk)

<img src="./pictures/apps.jpeg" alt="apps" width="400"/>

## Library References

This repository contains Aroundme apps with:

1. MVVM with Architecture Components
2. Google Maps SDK for Android
3. Repository with mock API
4. Koin (For Dependency Injection)
5. RxJava 2
6. Android ViewBinding
7. ktlint (Kotlin Style Guide Static Code Analysis Tool)
8. Unit test:
   - MockK (For mocking objects and verifying stubs)
   - Truth (For making more readable assertions)
   - AndroidX Testing (For accessing Test helpers to test LiveData)

## Project Structure

- ``/data`` (contains data processing / repositories)
- ``/di`` (contains object dependencies for injection)
- ``/ui`` (contains all views)
- ``/utils`` (contains tools that are used)

## Android Architecture Components

The implementation of this project uses the MVVM (Model-View-ViewModel) architecture pattern based on the Android Architecture Components from Google Developers. References can be found at https://developer.android.com/topic/libraries/architecture

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png "Android Architecture Components")

A full explanation is available at https://developer.android.com/jetpack/docs/guide
