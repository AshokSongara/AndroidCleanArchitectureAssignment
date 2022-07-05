# Disney Character App

App to display list of all Disney characters with Searching Functionality

# Architecture Design

The Project is implemented by following Clean Architecture.

- Separation of Concerns

# Libraries Used

- Using Retrofit library for fetching from the API
- Using Dagger/Hilt for Dependency Injection
- Using Room for the Local Persistence
- RXJava to perform API calls  
- Coroutine to perform room database operations. 

# Testing Done in this Project

- UI tests using Expresso Framework
- Instrumentation test for local database
- Testing ViewModels, repositories using MOCKK Library.

# Analytics and Logging

- Used Firebase for analytics.
- Used Timber for logging.