# Shoe Shopping App Frontend


## Overview

The Shoe Shopping App Frontend is an Android application designed to provide users with a seamless shopping experience for shoes. It fetches product data from Firebase Realtime Database, allowing users to browse various shoes, view their details, and add them to a cart.

## Features

- Browse a diverse collection of shoes fetched from Firebase Realtime Database

- View detailed information about each shoe, including images, descriptions, sizes, and colors

- Add shoes to a cart for easy purchasing

- Utilizes ViewPager2 and DotsIndicator to provide an interactive image slider for showcasing product images

- Implements NestedScrollView for smooth scrolling through shoe details and specifications

- User-friendly interface with responsive layouts for optimal viewing on various devices

## Technologies Used

- Kotlin programming language
Android SDK

- Android Jetpack components (ViewModel, LiveData, Navigation, etc.)

- RecyclerView for displaying lists of shoes

- ViewPager2 for the image slider functionality

- DotsIndicator for indicating the current position in the ViewPager2

- ConstraintLayout for flexible and responsive layouts

- NestedScrollView for scrolling content within a layout

- Glide for efficient loading and caching of shoe images



## Installation

1. Clone the repository to your local machine.
```kotlin
git clone https://github.com/your-username/shoe-shopping-app.git
```
2. Open the project in Android Studio.

3. Configure Firebase Realtime Database by adding your Firebase project's google-services.json file to the app directory.

4. Build and run the project on an Android emulator or a physical device.

## Usage

- Upon launching the app, users will be presented with a selection of shoes fetched from Firebase Realtime Database.

- Users can browse shoes, view details, and select their preferred size and color options.

- The app utilizes ViewPager2 and DotsIndicator to showcase multiple images of each shoe in an interactive image slider.

- NestedScrollView allows users to scroll through detailed information about each shoe, including descriptions and specifications.

- Users can add shoes to a cart for convenient purchasing.

## Contributing

If you would like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with descriptive commit messages.
4. Push your changes to your fork.
5. Submit a pull request to the main repository's master branch.
