# Recomposition Tracker for Compose Multiplatform

A simple tool to track UI recompositions in real-time for Compose Multiplatform. This project allows you to visualize and measure how often your components are recomposed, helping you optimize your UI and understand performance bottlenecks.

Inspired by [React Scanner](https://t.co/jyqyMp9SZ4), this tool shows which composables are being recomposed and how often, with a live count and visual feedback.

## Features

- Real-time recomposition tracking: See when and how your composables are recomposed.
- Live recomposition count: Each component that gets recomposed shows how many times it has been recomposed.
- Visual feedback: Components that are recomposed are outlined with a red border, and their recomposition count is displayed.
- Production-safe debugging: Use `trackRecompositionsIf()` to leave tracking in your codebase safely — no need to manually add or remove modifiers.

## Demo

https://github.com/user-attachments/assets/da45964c-47ca-41ea-aa03-61b189b1576f

## Installation - for Compose Multiplatform 

Add the dependency to your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.qamarelsafadi:compose-tracker:1.1.0")
}
```

## Installation - for Normal Android 

Add the dependency to your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.github.qamarelsafadi:compose-tracker:1.0.0")
}
```


## Usage

### Basic Tracking
Use `trackRecompositions()` to track recompositions on any Composable:

```kotlin
@Composable
fun MyComposable() {
    Text(
        text = "Hello World",
        modifier = Modifier.trackRecompositions()
    )
}
```

### Production-Safe Tracking
Use `trackRecompositionsIf()` for conditional tracking that's safe to leave in production:

```kotlin
@Composable
fun MyComposable() {
    Text(
        text = "Hello World",
        modifier = Modifier
            .trackRecompositionsIf(BuildConfig.DEBUG) // Only in debug builds
            .padding(16.dp)
    )
}
```

Available options:
```kotlin
// Always disabled (default)
Modifier.trackRecompositionsIf()

// Always enabled
Modifier.trackRecompositionsIf(enabled = true)

// Only in debug builds
Modifier.trackRecompositionsIf(enabled = BuildConfig.DEBUG)

// Custom condition
Modifier.trackRecompositionsIf(enabled = isDebugging)
```

## How It Works

This tool uses the `Modifier.trackRecompositions()` to track recompositions. When a recomposition occurs, the component will display a red border, and the recomposition count will update.

The library provides two main functions:
- `trackRecompositions()`: Always tracks recompositions
- `trackRecompositionsIf(enabled: Boolean)`: Conditionally tracks recompositions based on the enabled parameter

## Contribution

If you'd like to contribute to this project, feel free to open issues, submit pull requests, or suggest new features. Here's how you can contribute:

- Fork the repository.
- Create a new branch for your feature or fix.
- Make your changes.
- Submit a pull request.

## License

```
Copyright 2024 Qamar Safadi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```





