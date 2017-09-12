# FastProgressBar
Fastest and Easiest way to build your progressbar dialog.

## Add to your project
Steps
### Gradle
- Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

- Add the dependency:

```groovy
dependencies {
        compile 'com.github.erfanvaredi:FastProgressBar:0.9.6'
}
```

### Maven
- Add the JitPack repository to your build file:

```groovy
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

- Add the dependency:

```groovy
<dependency>
    <groupId>com.github.erfanvaredi</groupId>
    <artifactId>FastProgressBar</artifactId>
    <version>0.9.6</version>
</dependency>
```

## Introduction
How to use
### Basic FastProgressBar dialog:

```groovy
FPB.with(context).show("Peace");
```

### For single step timer:

```groovy
FPB.with(context).setCancelable(true).setTimer(2000, new IFPBCallback() {
    @Override
    public void onFPBPeriodFinished() {
        Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_LONG).show();
    }
}).show("Timer");
```

### For stepper timer:

- Start without waiting:
```groovy
FPB.with(context).setCancelable(false).setAsyncStepperTimer(5000, 5, new IAsyncFPBCallback() {
    @Override
    public void onFPBPeriodFinished(int step) {
        Log.i("EEE", "Stepper: " + step);
    }
}).show("Stepper");
```
- or fisrt wait, then start:
```groovy
FPB.with(this).setCancelable(false).setAsyncStepperTimer(3000, 5, true, new IAsyncFPBCallback() {
    @Override
    public void onFPBPeriodFinished(int step) {
        Log.i("EEE", "Stepper_skip: " + step);
    }
}).show("Stepper");
```

### For Custom FastProgressBar:

- First define the view with these ids (it's very important that you have <b>fpb_smoothProgressBar</b> and <b>fpb_textView_msg</b> ids as these have been mentioned on parent view.)
<br/>
fpb_custom_view.xml:
<br/>
```groovy
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ir.erfanvaredi.fastprogressbar.views.smoothpb.ESmoothProgressBar
        android:id="@+id/fpb_smoothProgressBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:indeterminate="true"
        android:visibility="visible"
        app:spb_colors="@array/colors_cyrusBit"
        app:spb_mirror_mode="false"
        app:spb_progressiveStart_activated="true"
        app:spb_progressiveStart_speed="1.5"
        app:spb_progressiveStop_speed="3.4"
        app:spb_reversed="false"
        app:spb_sections_count="1"
        app:spb_speed="1.0"
        app:spb_stroke_separator_length="0dp"
        app:spb_stroke_width="6dp" />

    <TextView
        android:id="@+id/fpb_textView_msg"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="text"
        android:padding="16dp"
        android:textAppearance="@style/Base.TextAppearance.AppCompat.Medium"
        android:textColor="#222222"
        android:textStyle="bold" />
</LinearLayout>
```

- Then you can set it by this:
```groovy
FPB.with(this).setView(R.layout.my_custom_fpb).show("Custom View");
```

## License
```groovy
Free to use and develop.
:)
```

## Developer

Delevoped with love by [@erfanvaredi][1]

[1]: https://github.com/erfanvaredi/
