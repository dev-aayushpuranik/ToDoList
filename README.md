# To-Do List App
A To-Do List Android application built using Kotlin and following the Clean Architecture pattern. The app allows users to manage tasks with basic CRUD operations. The project demonstrates how to implement a modular and scalable architecture for Android apps using MVVM, Room, Hilt, and Coroutines.

# Features
- Add new tasks with title and description.
- Mark tasks as completed or not completed.
- View a list of all tasks.
- Persistent data storage using Room Database.
- Clean Architecture implementation separating concerns across multiple layers (Data, Domain, and Presentation).
- Dependency injection using Hilt.

# Architecture
The project follows Clean Architecture principles, with the following layers:
- **Domain Layer:** Contains business logic, use cases, and entity models.
- **Data Layer:** Manages data sources (Room database) and repositories.
- **Presentation Layer:** Handles the UI and view logic (using MVVM pattern).

# Tech Stack
- **Kotlin** - Programming language.
- **Room** - For local database storage.
- **Coroutines** - To handle background tasks asynchronously.
- **Hilt** - Dependency Injection.
- **ViewModel** - Manage UI-related data in a lifecycle-conscious way.
- **LiveData** - For observing data changes.
- **Retrofit** - For Network Calls
- **Crashlytics:** - Firebase for crashlytics

# Folder Structure
![image](https://github.com/user-attachments/assets/e1735e22-c817-489e-a284-11226114aee1)


# Screenshots

# Installation
- Clone the repository:
```
git clone https://github.com/aayushpuranik/todoList.git
cd todo-clean-architecture
```
- Open the project in Android Studio.
- Sync Gradle and run the project on an emulator or physical device.

# Running the App
Once you've set up the project in Android Studio, you can run the app by following these steps:
- Open Android Studio.
- Connect an Android device or start an emulator.
- Click on Run or press Shift + F10.

# Key Libraries
- Room - Local database for persisting tasks.
- Hilt - Dependency injection to manage app-level dependencies.
- Coroutines - Simplifies asynchronous programming.
- State Management - Using ViewModel and LiveData to manage UI-related data in a lifecycle-conscious way, ensuring the UI reflects the latest state of the app.
- Retrofit - A type-safe HTTP client for Android and Java to handle network requests and consume REST APIs efficiently.
- Firebase Crashlytics - Real-time crash reporting for monitoring app stability and improving the app's quality by tracking crashes and errors.
