# Food delivery - Spring Application

## Introduction

The goal of this exercise is to add **Spring Framework** (Core) to the Food Delivery application.

The following Spring features will be used:

- Bean definition with annotation-based configuration.
- Bean creation and dependency injection.
- Configuration injection.

## Overview

Spring application will be started by a new class: `SpringApplicationStarter` - already created.

- It is responsible for creating the Spring Application context.
- It queries the main bean from the context (Application), that contains the application logic.
- Passes execution to the application `run()` method.
- Closes the context.

`SpringApplicationStarter` relies on a Spring configuration class named `SpringConfig`, which is also available in the project.

The application configuration is stored in a file named **application.properties** (classpath resource),
which is also provided in the template project.
It contains only a single configuration value: **baseDirPath**

## Required changes

### Build

Take your food delivery build solution and copy the source into the template repository
**application**, **service** and **persistence** modules respectively.

Add the following spring dependencies to each module:

- groupId: **org.springframework**
- version: **5.3.23**
- artifacts: **spring-core** and **spring-context**

### Application

`Application` should not create data store, service, or view. These objects will be created by Spring framework
and injected into Application via constructor injection.

Create the following constructor: `public Application(FoodDeliveryService service, DataStore dataStore, View view)` 

Make sure that `Application` has a public `run()` method that executes the application logic.
The first call should be: `dataStore.init()`

Remove method: `public static void main(String[] args)`

### Data Store

`DataStore` interface

- add `init()` method to it.

`FileDataStore`

- It should define the following constructor: `public FileDataStore(@Value("${baseDirPath}") String baseDirPath)`

Mind the injected configuration!

### Spring Beans

Add `@Component` annotation to the following classes

- `Application`
- `FileDataStore`
- `DefaultFoodDeliveryService`
- Your View implementation
