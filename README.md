# Formula One Ergast [![Build Status](https://travis-ci.org/olerom/formula-one-ergast.svg?branch=master)](https://travis-ci.org/olerom/formula-one-ergast)

Java implementation to get a historical record of motor racing data from [Ergast Developer API](http://ergast.com/mrd/).

## Add dependencies

### Gradle
Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories { 
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency:

```groovy
dependencies {
    compile 'com.github.olerom.formula-one-ergast:ergast:0.1.0'
}
```

### Maven

Add the JitPack repository to your build file:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.github.olerom.formula-one-ergast</groupId>
    <artifactId>ergast</artifactId>
    <version>0.1.0</version>
</dependency>
```


## How to use

You have to initialize Ergast object:

```java
Ergast ergast = new Ergast(2016, 100, 2);
```
```java
Ergast ergast = new Ergast();
```

If you use default constructor, season will be set as -1, limit as 30 and offset as 0.
You can get the following objects that satisfy Ergast queries:
* [Season](http://ergast.com/mrd/methods/seasons/)
* [Qualification](http://ergast.com/mrd/methods/qualifying/)
* [Constructor](http://ergast.com/mrd/methods/constructors/)
* [LapTimes](http://ergast.com/mrd/methods/laps/)
* [Schedule](http://ergast.com/mrd/methods/schedule/)
* [DriverStandings, ConstructorStandings](http://ergast.com/mrd/methods/standings/)
* [Circuit](http://ergast.com/mrd/methods/circuits/)
* [RacePitStops](http://ergast.com/mrd/methods/pitstops/)
* [RaceResult](http://ergast.com/mrd/methods/results/)
* [Driver](http://ergast.com/mrd/methods/drivers/)
* [FinishingStatus](http://ergast.com/mrd/methods/status/)

For example, to get information about pit stops at final race of 2016 season with 100 limit: 

```java
Ergast ergast = new Ergast(2016, 100, Ergast.DEFAULT_OFFSET);
ergast.getRacePitStops(21).forEach(System.out::println);
```