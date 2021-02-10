# DD2480 Group 7: CI Server

This program is a Continuous Integration (CI) Server that is able to build itself after being triggered by GitHub. It will run the included tests and the build process, save the build into a database, and report the success or failure of the build back to GitHub.

## Build requirements

A recent version of the Java JDK (at least 11) is necessary for building and testing the project. Ensure that environment variables are set correctly for automatic detection of the JDK.

For Github status checks, a personal access token (https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token) registered for one of the github collaborators is required for github authentication.

## Build instructions

Building the project is automated using Gradlew. From the root directory of the project, running `./gradlew build` (on Linux or Mac OS X) or `.\gradlew.bat build` (on Windows) will compile the code in `src/main`. The resulting executable `jar` will be stored in the directory `build/libs`. 

For authentication, the registered bearer token needs to be activated in the local environment, which is done differently for different OS.
Linux/Mac:export CI_TOKEN=exampletoken123, which will set it for all following commands in the terminal. Alternatively, the environment variable can be prepended before a single command, for example CI_TOKEN=abcdefg ./gradlew build
Windows: using Command Line, one can use set CI_TOKEN=abcdefg in the same way as export. Using PowerShell, it is necessary to use $Env:CI_TOKEN="exampletoken123". Note that this has to be done in the same terminal that runs the build!


## Build instructions

Building the project is automated using Gradlew. From the root directory of the project, running `./gradlew build` (on Linux or Mac OS X) or `.\gradlew.bat build` (on Windows) will compile the code in `src/main`. The resulting executable `jar` will be stored in the directory `build/libs`. 

For authentication, the registered bearer token needs to be activated in the local environment, which is done differently for different OS.
Linux/Mac:export CI_TOKEN=exampletoken123, which will set it for all following commands in the terminal. Alternatively, the environment variable can be prepended before a single command, for example CI_TOKEN=abcdefg ./gradlew build
Windows: using Command Line, one can use set CI_TOKEN=abcdefg in the same way as export. Using PowerShell, it is necessary to use $Env:CI_TOKEN="exampletoken123". Note that this has to be done in the same terminal that runs the build!

## Test instructions

All tests in `src/test` will be executed as part of the build process in the previous section. To rerun all tests, `./gradlew test --rerun-tasks` (on Linux or Mac OS X) or `.\gradlew.bat test --rerun-tasks` (on Windows) can be ran from the root directory of the project.

## Contributions

In general, everyone was helpfull in all areas of the project. Continuous dialog was held through meetings and discord. Major contributions of all members are mentioned below.

**Martijn Atema** (atema@kth.se)

- Decided the structure of the software and setting up Gradle
- Help where needed

**Piere Colson** (coslon@kth.se)

- Implemented functionality to clone a repository and its tests

**Hugo Heyman** (hheyman@kth.se)

- Implemented functionality to submit GitHub status and receive the response through GitHub API.

**Hafsa Aoutir** (hafsaa@kth.se)

- Implemented functionality to run a build and its tests

**Linnea Bonnevier** (lmebo@kth.se)

- Implemented functionality to receive a request and its tests
