# Android Demo - API Communication

This is a demo project to show how you can use an Android Application to communicate with an API. 
For this project we will use the following dependencies:

## API Communication Dependencies 

- OK HTTP - Check documentation [here](https://square.github.io/okhttp/)
- Retrofit - Check documentation [here](https://square.github.io/retrofit/)
- Moshi, Decoding/Encoding - Check documentation [here](https://github.com/square/moshi)
- Android Coroutines - Check documentation [here](https://developer.android.com/kotlin/coroutines)

## Android Manifest

In the manifest we need to declare that the application will use the Internet, so we need to add the permission:
```xml
<manifest 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <!-- Permissions -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <!-- Other stuff here, like application declaration -->
    <!-- ... -->
</manifest>
```

## Testing

In order to decompile the build to check for unsecure stuff you can upload your apk to this.
