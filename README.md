# :fire: Alpha Tech :fire:
Evolve your gaming experience :100:

## :rocket: Quickstart 

### :dart: Prerequisites 

- JDK 8+<br>
- Gradle 7.4.2

### :star2: Build 
```bash
gradle desktop:build 
```
or 
```bash
gradle build 
```
### :star: Run the game 
```bash
gradle desktop:run 
```
or 
```bash
gradle run 
```

### :dizzy: Run unit tests 
```bash
gradle test
```
or
```bash
gradle check
```
or 
```bash
gradle :core:test
```

## :zap: Unit-testing artifacts 
Unit-testing report(artifacts) can be downloaded from CICD -> Pipelines 

## :computer: Download 
### :high_brightness: Stable version 
The game (.jar) can be downloaded [here](/desktop/build/libs/desktop-1.0.jar)
or by running `gradle desktop:dist` 
###  :low_brightness: Latest version
The latest version of the game can be downloaded from the `Deploy Job` artifacts in the CI/CD tab.
