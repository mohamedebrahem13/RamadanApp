RamadanApp

Overview

RamadanApp is an Android application built using modern development practices and tools. The app is structured following the MVVM architecture with a clear separation of concerns. It utilizes Jetpack Compose for UI and includes a YouTube player to stream videos. The application is modular, with features organized into separate packages.

Technologies Used

Kotlin: Primary programming language

MVVM Architecture: Ensures separation of concerns and maintainability

Coroutines: For asynchronous operations

Dagger Hilt: Dependency injection framework

Retrofit: API communication

Ktor (Planned): A wrapper interface is available for easy integration

MockK: Unit testing

Turbine: Flow testing

Kotest SDK: Added for future use (requires Android Studio plugin)

Repository Pattern: Data handling and business logic

Separation of Concerns: Clear organization of UI, data, domain, and DI layers

Jetpack Compose: UI development

YouTube Player: Video playback

Resource Class: Used for managing UI states (Loading, Success, Failure)

Type-Safe Navigation with Serialization: Utilized for navigation

Nested Navigation Graphs: For improved back stack handling

Project Structure

The app is structured into distinct modules:

Features

Each feature is separated into:

Data: Handles API calls and local data (to be added as needed)

Domain: Business logic and use cases (to be added as needed)

UI: Jetpack Compose screens and UI components

DI: Dependency injection setup (to be added as needed)

Home Feature (Parent)

The Home feature consists of multiple sub-features:

Home Content

Home Content Screen

Content List Screen

News Content

Settings

Favorites

Downloads

Each of these sub-features currently includes a UI package. Additional Data, Domain, and DI layers will be added as required.

Navigation

Navigation Package: Located under the Home feature, it manages navigation graphs and destinations.

Type-Safe Navigation with Serialization: Ensures safe and structured navigation.

Nested Graphs: Used for better back stack handling.

Common Module

This package includes shared utilities:

UI Components

Dependency Injection (DI)

Data Handling

Domain Logic

Networking

Uses Retrofit for API calls

Future support for Ktor through a wrapper interface

Data Mapping

Utilizes Mapper Classes to convert DTOs into domain models

If an entity is required, it can be easily integrated

State Management

Resource Class: Wraps use cases and emits states (Loading, Success, Failure)

Exception Handling: Currently emits normal exceptions; a wrapper class is planned for better exception management

Testing

MockK: Used for mocking dependencies in unit tests

Turbine: Used to test Kotlin Flows

Kotest SDK: Added but requires additional setup (Android Studio plugin)

Future Enhancements

Implement a wrapper class for exceptions

Extend Ktor integration for networking

Expand feature set with more functionalities
