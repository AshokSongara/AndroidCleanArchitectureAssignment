# Hotel Menu App

App to display list of Menu items of Hotel.

# Architecture Design

The Project is implemented by following Clean Architecture.

- Separation of Concerns
- SOLID principles.

# Libraries Used

- Using Retrofit library for fetching from the API
- Using Dagger/Hilt for Dependency Injection
- Using Room for the Local Persistence
- Used MVVM  Binding Adapters for handling view and data
- RXJava to perform API calls  
- Coroutine to perform room database operations. 

# Testing Done in this Project

- UI tests using Expresso Framework
- Instrumentation test for local database
- Testing ViewModels, repositories using MOCKK Library.

# Analytics and Logging

- Used Firebase for analytics.
- Used Timber for logging.