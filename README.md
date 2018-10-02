# musicapp
Test project with modularisation. Instant app support MVP architecture (Dagger, Rx, Retrofit, Picasso). Mix between Kotlin and Java.

The API behind the project is LastFM Api [https://www.last.fm/api]

## RUN UI TESTS

### To run all Stable UI tests execute : 

./gradlew connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.sniper.music.SmokeTest

### To run all Smoke UI tests execute : 

./gradlew connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.sniper.music.SmokeTest

### To run all Home screen UI tests execute : 

./gradlew connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.sniper.music.HomeScreenTest

### To run all Details screen UI tests execute : 

./gradlew connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.sniper.music.DetailsScreenTest

## RUN UNIT TESTS

### To run all unit tests execute : 

./gradlew precommit

## RUN CODE CHECK STYLE (Kotlin and Java)

### To run all checks for the code style execute : 

./gradlew checkstyleMain detektCheck lintDebug




