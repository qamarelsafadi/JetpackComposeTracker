# Recomposition Tracker for Jetpack Compose

A simple tool to track UI recompositions in real-time for Jetpack Compose. This project allows you to visualize and measure how often your components are recomposed, helping you optimize your UI and understand performance bottlenecks.

Inspired by [React Scanner](https://t.co/jyqyMp9SZ4), this tool shows which composables are being recomposed and how often, with a live count and visual feedback.

## Features

- Real-time recomposition tracking: See when and how your composables are recomposed.
- Live recomposition count: Each component that gets recomposed shows how many times it has been recomposed.
- Visual feedback: Components that are recomposed are outlined with a red border, and their recomposition count is displayed.

# Demo

https://github.com/user-attachments/assets/6b540c1c-b3c0-455d-84dc-f6f1707416ea


## How It Works
This tool uses the ```Modifier.trackRecompositions()``` to track recompositions. When a recomposition occurs, the component will display a red border, and the recomposition count will update.

You can customize the ```trackRecompositions()``` modifier to track different UI components in your app and use this tool to debug and optimize your Jetpack Compose UI.


## Contribution
If you’d like to contribute to this project, feel free to open issues, submit pull requests, or suggest new features. Here's how you can contribute:

- Fork the repository.
- Create a new branch for your feature or fix.
- Make your changes.
- Submit a pull request.





