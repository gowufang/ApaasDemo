# 源码集成步骤

## 新建一个项目

改成30

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled.png)

复制源码到工程根目录

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%201.png)

拷贝完后

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%202.png)

## gradle.properties

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%203.png)

## project的build.gradle

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%204.png)

```
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
```

## app的build.gradle

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%205.png)

```
implementation project(path: ':AgoraClassSDK')
implementation project(path: ':hyphenate')
implementation project(path: ':AgoraEduUIKit')
implementation project(path: ':AgoraEduCore')
```

## setting.gradle

```
include ':AgoraClassSDK'
if (!readyPublishGithub.toBoolean()) {
    include ':AgoraEduCore'
}
include ':AgoraEduUIKit'
include ':app'
include ':hyphenate'
rootProject.name = "项目名字"
include ':app'
```

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%206.png)

## config.gradle放进来

## 注释`id 'com.github.dcendents.android-maven'`

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%207.png)

然后可以编译成功

## maven集成方式

[https://github.com/fenggit/CloudClass-Android-Maven](https://github.com/fenggit/CloudClass-Android-Maven)

## 常见错误

…/AgoraEduUIKit/src/main/java/com/agora/edu/component/chat/AgoraEduEaseChatWidget.kt: (73, 56): Unresolved reference: ease_chat_layout

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%208.png)

修改如下：（如果有类似的报错，改成对应的全路径）

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%209.png)

或者改头文件 R.文件

`import io.agora.agoraclasssdk.R`

换成

`import io.agora.agoraeduuikit.R`

如下：

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2010.png)

****android studio编程时出现的错误：Cannot get property 'XXXX' on extra properties extension as it does not exist****

用Android Studio中导入第三方库工程的时候出现的问题：
Error:(28, 0) Cannot get property 'junitVersion' on extra properties extension as it does not exist

出现这种问题原因是第三方库工程 引用了 自定义的 junitVersion 这个名字的ext；

因此在该项目的根目录那个build.gradle里的ext加上junitVersion这个即可，如下
**ext** {
junitVersion = '4.12'
}
4.12这个是自定义的，

其他名称的同理。

**[Unable to load class org.gradle.api.publication.maven.internal.MavenPomMetaInfoProvider](https://stackoverflow.com/questions/68612057/unable-to-load-class-org-gradle-api-publication-maven-internal-mavenpommetainfop)**

I'v solve this by deleting one line in `build.gradle:`

```
apply plugin: 'com.github.dcendents.android-maven'
```

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2011.png)

错误

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2012.png)

…AgoraClassSDK/src/main/java/io/agora/classroom/ui/goup/AgoraClassSmallGroupingActivity.kt: (40, 54): Unresolved reference: fcr_group_enter_group

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2013.png)

An exception occurred applying plugin request [id: 'com.android.application']

> Failed to apply plugin 'com.android.internal.application'.
Android Gradle plugin requires Java 11 to run. You are currently using Java 1.8.
> 

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2014.png)

One or more issues found when checking AAR metadata values:

Dependency 'androidx.core:core-ktx:1.7.0' requires 'compileSdkVersion' to be set to 31 or higher.
Compilation target for module ':app' is 'android-30'

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2015.png)

![Untitled](%E6%BA%90%E7%A0%81%E9%9B%86%E6%88%90%E6%AD%A5%E9%AA%A4%20838a095a94b541f8a024899cbf934c84/Untitled%2016.png)