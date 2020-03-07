### Gist Viewer [![Build Status](https://travis-ci.org/demba003/GistViewer.svg?branch=mvp-cache)](https://travis-ci.org/demba003/GistViewer)
Display a list of lastly added [Gists](https://gist.github.com/discover) and open selected one to read it

It's just sample app to demonstrate usage of MVP pattern on Android platform using Kotlin language.

Additionally following libraries were used:
- [OkHttp](https://github.com/square/okhttp)
- [Retrofit](https://github.com/square/retrofit)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [RxKotlin](https://github.com/ReactiveX/RxKotlin)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Glide](https://github.com/bumptech/glide)
- [Mockito-kotlin](https://github.com/nhaarman/mockito-kotlin)

This version additionally uses [Room](https://developer.android.com/topic/libraries/architecture/room) to cache data and [Dagger](https://github.com/google/dagger) for dependency injection (instead of Koin)

You can also see MVVM version of this app [here](https://github.com/demba003/GistViewer/tree/mvvm) (without cache)
