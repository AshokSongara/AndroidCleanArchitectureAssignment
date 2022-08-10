object ApplicationId {
    val id = "com.app.android_clean_architecture_assignment"
}

object Release {
    const val versionCode = 1
    const val versionName = "1.0"
    const val compileSdkVersion = 29
    const val targetSdkVersion = 30
    const val minSdkVersion = 26
}

object Config {
    const val gradle = "com.android.tools.build:gradle:4.2.2"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val navigationGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigationVersion}"
}

object Version {
    // Kotlin based
    const val kotlinVersion = "1.6.10"
    const val kotlinCoreVersion = "1.0.2"

    //RxJava & RxAndroid
    const val rxkotlinVersion = "2.2.0"
    const val rxandroidVersion = "2.1.0"

    //json
    const val gsonVersion = "2.8.5"

    //image
    const val glideVersion = "4.9.0"

    //Networking
    const val retrofitVersion = "2.4.0"
    const val okhttpLoggingVersion = "3.11.0"

    //Android jetpack
    const val appcompatVersion = "1.1.0-alpha05"
    const val constraintLayoutVersion = "2.0.0-alpha3"
    const val navigationVersion = "2.1.0"
    const val lifecycleVersion = "2.0.0"
    const val materialComponentVersion = "1.0.0"
    const val legacySupportVersion = "1.0.0"

    //test
    const val testRunnerVersion = "1.1.1"
    const val junitVersion = "4.12"

    //timber
    const val timber = "4.7.1"

    //multidex
    const val multidexVersion = "2.0.1"

    //unit test
    const val mockitoVersion = "2.21.0"
    const val mockitoInlineVersion = "2.19.0"
    const val coreTestingVersion = "1.1.1"
    const val mockitoKotlinVersion = "2.1.0"
    const val daggerMockVersion = "0.8.5"
    const val kotlinTestVersion = "3.3.2"

    //ui test

    const val espressoVersion = "3.5.0-alpha06"
    const val espressoRuleVersion = "1.4.0"

    //hilt
    const val hiltVersion = "2.37"
    const val hiltViewmodels = "1.0.0-alpha02"
    const val hiltTest = "2.28-alpha"

    //room
    const val room = "2.2.5"

    //firebase nom
    const val firebaseBom = " 30.1.0"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlinVersion}"
}

object Log {
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
}

object Support {
    const val core = "androidx.core:core-ktx:${Version.kotlinCoreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    const val lifeCycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleVersion}"
    const val materialComponent =
        "com.google.android.material:material:${Version.materialComponentVersion}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupportVersion}"
    const val multidex = "androidx.multidex:multidex:${Version.multidexVersion}"

}

object Image {
    const val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
}

object Arch {
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofitVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofitVersion}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLoggingVersion}"
}

object Json {
    const val gson = "com.google.code.gson:gson:${Version.gsonVersion}"
}

object Reactivex {
    const val android = "io.reactivex.rxjava2:rxandroid:${Version.rxandroidVersion}"
    const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Version.rxkotlinVersion}"
}

object TestLibs {
    const val junit = "junit:junit:${Version.junitVersion}"
    const val testRunner = "androidx.test:runner:${Version.testRunnerVersion}"
}

object UnitTest {
    const val mockitoCore = "org.mockito:mockito-core:${Version.mockitoVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoInlineVersion}"
    const val coreTesting = "android.arch.core:core-testing:${Version.coreTestingVersion}"
    const val mockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlinVersion}"
    const val daggerMock =
        "com.github.fabioCollini.daggermock:daggermock-kotlin:${Version.daggerMockVersion}"
    const val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Version.kotlinTestVersion}"
    const val hiltTest = "com.google.dagger:hilt-android-compiler:${Version.hiltTest}"
}

object UiTestCase {
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
    const val espressocontrib = "androidx.test.espresso:espresso-contrib:${Version.espressoVersion}"
    const val espressorunner = "androidx.test:runner:${Version.espressoRuleVersion}"
    const val espressorules = "androidx.test:rules:${Version.espressoRuleVersion}"
}

object Hilt{
    const val hilt = "com.google.dagger:hilt-android:${Version.hiltVersion}"
    const val hiltCompiler =  "com.google.dagger:hilt-android-compiler:${Version.hiltVersion}"

    const val hiltViewModel =   "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltViewmodels}"
    const val hiltViewModelCompiler =   "androidx.hilt:hilt-compiler:${Version.hiltViewmodels}"
}

object Room{
    const val room = "androidx.room:room-ktx:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
}

object Firebase{
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Version.firebaseBom}"
}