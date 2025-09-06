# Recipe Manager

A simple Spring Boot + HSQLDB application that simulates a small recipe database.  
You can browse recipes, add new recipes, update existing records—all against an in-memory HyperSQL database.

This reference project demonstrates a clean Controller → Service → Repository layering, externalized SQL scripts (`schema.sql`, `data.sql`), and IntelliJ-friendly setup.  
Created for the IT Crafters Graduation Project.

---

## Table of Contents

1. [Description](#description)
2. [Prerequisites](#prerequisites)
3. [Getting Started](#getting-started)
    - [Clone the repository](#clone-the-repository)
    - [Build & Run (IntelliJ IDEA)](#build--run-intellij-idea)
4. [Swagger UI](#swagger-ui)
5. [Configuration](#configuration)
6. [Database Initialization](#database-initialization)
7. [Database Structure](#database-structure)
8. [Available Endpoints](#available-endpoints)
9. [Project Structure](#project-structure)

---

## Description

This example implements basic CRUD operations for a **Recipe Manager**:

- **MVP**
    - Find a recipe by ID
    - List all recipes
    - Add a recipe
    - Update a recipe
    - Delete a recipe

You’ll see how layers connect, how to externalize SQL scripts (`schema.sql`, `data.sql`), and how to work with an in-memory HSQLDB.

---

## Prerequisites

- Java 21
- Gradle (or use the included `gradlew`)
- IntelliJ IDEA (optional, but recommended)

---

## Getting Started

---

### Clone the repository

Via IntelliJ IDEA

1. Open IntelliJ IDEA.
2. From the Welcome screen (or File menu), choose **Get from Version Control…**
3. In the dialog that appears, paste `https://github.com/laurikadakas/recipemanager.git` into the **URL** field.
4. Select your desired local directory and click **Clone**.
5. Once the clone completes, IntelliJ will open the project—allow it to import/reload the Gradle settings.

Via command line
```bash
git clone https://github.com/laurikadakas/recipemanager.git
```

---

### Build & Run (IntelliJ IDEA)

1. **Open the project**
    - In IntelliJ IDEA, select **File ▸ Open…** and choose the project’s root folder (containing `build.gradle`).
    - IntelliJ will automatically reload the Gradle project. If you encounter any issues, click the “Refresh” icon in the Gradle tool window to force a manual reload.

2. **Run the application**
    - In the **Project** tool window, navigate to `src/main/java/eu/itcrafters/recipemanager/RecipeManagerApplication.java`.
    - Click the green ▶︎ icon next to the `main` method, or right-click the file and choose **Run 'RecipeManagerApplication'**.

3. **Verify startup**
    - The console should show Spring Boot starting on port 8080.
    - Open your browser to Swagger UI page `http://localhost:8080/swagger-ui/index.html` to confirm the server is running.

4. **Stop the server**
    - Click the red ■ icon in the Run tool window, or press **Ctrl + F2** (Windows/Linux) or **⌘ + F2** (macOS).

---

## Swagger UI

After startup, you can browse your OpenAPI docs at: `http://localhost:8080/swagger-ui/index.html`

---
## Configuration

All runtime settings live in `src/main/resources/application.properties`.

---

## Database Initialization

On startup, Spring Boot will automatically run any `schema.sql` and `data.sql` files found on the classpath (i.e. in `src/main/resources`) to build and seed your HSQLDB schema.

### schema.sql

- Defines your tables, constraints, indexes, etc.

### data.sql

- Populates your newly created tables with initial or sample data.
- Executed immediately after `schema.sql`, so all referenced tables already exist.
- Use it to insert lookup values, demo rows, or any seed data your application needs on startup.

---

## Database Structure

![ERD Diagram](/docs/ERD.png)
---

## Available Endpoints

| Method | Path                 | Description                         |
| ------ |----------------------|-------------------------------------|
| GET    | `/recipe/{recipeId}` | Retrieve a single recipe by its ID  |
| GET    | `/recipes`           | List all recipes in the database    |
| POST   | `/recipe`            | Create a new recipe record          |
| PUT    | `/recipe/{recipeId}` | Update an existing recipe’s details |
| DELETE | `/recipe/{recipeId}` | Delete a recipe by its ID           |

---

## Project Structure

```plaintext
recipemanager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── eu.itcrafters.recipemanager/
│   │   │       ├── controller/              # REST controllers & DTOs
│   │   │       │    ├── ingredient/   
│   │   │       │    │   └── dto/            # Data Transfer Object
│   │   │       │    │       └── IngredientDto.java        
│   │   │       │    └── recipe/
│   │   │       │        └── dto/            # Data Transfer Objects
│   │   │       │            ├── RecipeDto.java
│   │   │       │            └── RecipeInfo.java        
│   │   │       ├── infrastructure/          # Infrastructure components
│   │   │       │    ├── db/                 # HSQL database configuration
│   │   │       │    │   └── HsqlServerConfig.java      
│   │   │       │    └── rest/               # REST exception handling
│   │   │       │        ├── error/          # Error components
│   │   │       │        │   ├── ApiError.java
│   │   │       │        │   └── Error.java        
│   │   │       │        ├── exception/      # Custom exceptions
│   │   │       │        │   ├── DataNotFoundException.java
│   │   │       │        │   └── ForbiddenException.java        
│   │   │       │        └── RestExceptionHandler.java
│   │   │       ├── persistence/             # JPA entities, repositories, mappers
│   │   │       │    ├── cuisinetype/        # CuisineType entity and repository
│   │   │       │    │   ├── CuisineType.java
│   │   │       │    │   └── CuisineTypeRepository.java
│   │   │       │    ├── ingredient/         # Ingredient entity and repository
│   │   │       │    │   ├── Ingredient.java
│   │   │       │    │   └── IngredientRepository.java
│   │   │       │    ├── instruction/        # Instruction entity and repository
│   │   │       │    │   ├── Instruction.java
│   │   │       │    │   └── InstructionRepository.java
│   │   │       │    ├── recipe/             # Recipe entity, repository, and mapper
│   │   │       │    │   ├── Recipe.java
│   │   │       │    │   ├── RecipeMapper.java
│   │   │       │    │   └── RecipeRepository.java
│   │   │       │    └── recipeingredient/   # RecipeIngredient entity and repository
│   │   │       │        ├── RecipeIngredient.java
│   │   │       │        └── RecipeIngredientRepository.java
│   │   │       ├── service/                 # Business-logic services
│   │   │       │    └── recipe/             # Recipe service
│   │   │       │        └── RecipeService.java
│   │   │       └── RecipeManagerApplication.java
│   │   └── resources/
│   │       ├── application.properties       # Spring configuration
│   │       ├── schema.sql                   # DDL for HSQLDB schema
│   │       └── data.sql                     # Initial seed data
│   └── test/
│       └── java/
│           └── eu.itcrafters.recipemanager/
│               └── RecipeManagerApplicationTests.java
├── build.gradle                            # Gradle build script
├── settings.gradle                         # Gradle settings
├── gradlew / gradlew.bat                   # Gradle wrapper
├── .gitignore                              # Files to ignore in Git
├── .gitattributes                          # Git attributes
├── README.md                               # This file
└── HELP.md                                 # Project help & notes
```

---