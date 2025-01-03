plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.dagger)
    alias(libs.plugins.google)
}

android {
    namespace = "com.dwipal.practice.androidadvancepracticeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dwipal.practice.androidadvancepracticeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    //constrain layout
    implementation(libs.constraintlayout)
   //lifecycle viewmodel & livedata
    implementation(libs.viewmodel)
    implementation(libs.livedata)
    //room database
    implementation(libs.room)
    annotationProcessor(libs.roomannotation)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitgson)
    implementation(libs.retrofit.rxjava.adapter)
    //swipe to refresh
    implementation(libs.swipetorefresh)
    //glide
    implementation(libs.glide)
    annotationProcessor(libs.glidecompiler)
    //paging
    implementation(libs.paging)
    implementation(libs.pagingrxjava)
    //dagger
    implementation(libs.dagger)
    annotationProcessor(libs.daggercompiler)
    //work manager
    implementation(libs.work.manager)
    //navigation
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    //firebase
    implementation(platform(libs.firebase))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database) //real time database
    implementation(libs.firebase.firestore) //cloud firestore


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}