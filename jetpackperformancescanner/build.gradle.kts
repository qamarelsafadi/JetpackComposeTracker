plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
    id("signing")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

android {
    namespace = "com.qamar.jetpack_performance_scanner"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.ui)
    implementation(libs.ui.tooling)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Maven Central Publishing Configuration
mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    
    signAllPublications()
    
    coordinates("io.github.qamarelsafadi", "compose-tracker", "1.0.0")
    
    pom {
        name.set("Jetpack Compose Tracker")
        description.set("A simple tool to track UI recompositions in real-time for Jetpack Compose. Helps visualize and measure how often your components are recomposed.")
        inceptionYear.set("2024")
        url.set("https://github.com/qamarelsafadi/JetpackComposeTracker/")
        
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        
        developers {
            developer {
                id.set("qamarelsafadi")
                name.set("Qamar Safadi")
                url.set("https://github.com/qamarelsafadi/")
            }
        }
        
        scm {
            url.set("https://github.com/qamarelsafadi/JetpackComposeTracker/")
            connection.set("scm:git:git://github.com/qamarelsafadi/JetpackComposeTracker.git")
            developerConnection.set("scm:git:ssh://git@github.com/qamarelsafadi/JetpackComposeTracker.git")
        }
    }
}