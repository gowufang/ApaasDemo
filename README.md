# ApaasDemo
// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply from: 'config.gradle'

buildscript{
ext{
kotlin_version = '1.4.32'
}
repositories{
mavenCentral()
        maven{url 'https://jitpack.io'}
google()
        maven{url 'https://s01.oss.sonatype.org/content/repositories/snapshots/'}
    }

dependencies{
classpath 'com.android.tools.build:gradle:4.1.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20"
        classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'
        classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
}
}

allprojects{
repositories{
mavenCentral()
        maven{url 'https://jitpack.io'}
google()
        maven{url 'https://s01.oss.sonatype.org/content/repositories/snapshots/'}
    }
}

task clean(type: Delete){
delete rootProject.buildDir
}
