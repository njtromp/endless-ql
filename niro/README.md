# NIRO QL/QLS Interpreter
An interpreter for the questionnaire language QL

## Students
* Nico Tromp
* Rocco Andela

## How to build
There are two ways to build the application.  
* Create a executable JAR file
* Create a native executable application

### Create executable jar file
Run `mvn jfx:jar` 

### Create executable native application
Run `mvn jfx:native` 

## How to run
Depending on the exact type of executable you have build.

### Executable jar
Locate the jar-file. It should be at `target/jfx/app`. Then run `java -jar <jar-file>`  
For example: `java -jar target/jfx/app/ql-form-creator-0.0.1-SNAPSHOT-jfx.jar`.

### Executable native application
Depending on the platform you are on locate the appropriate installer at `target/jfx/native`.
