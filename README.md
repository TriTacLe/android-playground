# compose-studies

Seven small Android apps, one per topic. Each one is a standalone Gradle project that does a
single thing, so the code for a topic can be read without wading through an app that also does
five other things.

Kotlin, Jetpack Compose (except the first, which is deliberately views and XML), AGP 8.13.2,
Kotlin 2.3.21, compileSdk 36, minSdk 24.

| App | What it shows |
| --- | --- |
| [`lifecycle-log`](apps/lifecycle-log) | Every Activity lifecycle callback written to logcat, on views and XML rather than Compose |
| [`recipe-card`](apps/recipe-card) | Compose layout: a header image, a `LazyColumn` of cards, Toast feedback, a theme with its own palette and type |
| [`friend-list`](apps/friend-list) | Hoisted state. A stateless screen takes values and lambdas, the owner holds the list, and the state survives rotation |
| [`friend-editor`](apps/friend-editor) | `ViewModel` plus type-safe Navigation routes, with list, detail and edit screens sharing one view model |
| [`number-game`](apps/number-game) | HTTP and coroutines. A guessing game against a remote endpoint, with the session held in a cookie |
| [`socket-chat`](apps/socket-chat) | Raw TCP. The same app is either the `ServerSocket` end or the `Socket` end, and both read and write over the stream |
| [`film-database`](apps/film-database) | Room and DataStore. JSON shipped in `res/raw` is read into three tables at startup, queried with `JOIN`, and a chosen background colour survives a restart |

Each app has a `screenshots/` folder showing it running on an emulator, except
`lifecycle-log`, which has `logs/` with the logcat output for startup, home and back, and
rotation, since that is what there is to see.

## Building

Every app carries its own Gradle wrapper, so build them one at a time:

    cd apps/film-database
    ./gradlew assembleDebug

`local.properties` is not checked in. Add your own `sdk.dir` before building.

## A note on number-game

That one talks to a server hosted elsewhere, and the field names it sends are the ones that server
expects. If the endpoint is unreachable the app still builds and runs, but it cannot play.

## Why these

Each app was built to work out one part of the platform on its own and the set grew in that order,
from the lifecycle through state, navigation, networking and finally storage. The later ones lean
on what the earlier ones settled, which is why they share a layout convention and a theme shape.
