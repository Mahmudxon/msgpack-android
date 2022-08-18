# MessagePack for android [![](https://jitpack.io/v/Mahmudxon/msgpack-android.svg)](https://jitpack.io/#Mahmudxon/msgpack-android)

Json MessagePack convertor library for android.

### (!)Please, <a href="https://raw.githubusercontent.com/Mahmudxon/msgpack-android/master/msgpack-release.aar">download</a> download library ass .arr file. It has small error with building by jitpack. Sorry, for this! I need a free time to fix it.

## How to
To get a Git project into your build: <br />
**Step 1.** Add the JitPack repository to your build file 
```
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2.** Add the dependency
```
dependencies {
          ...
	   implementation 'com.github.Mahmudxon:msgpack-android:VERSION'
        }
```

## USEGE
You can use with retrofit and Gson :
```
Retrofit.Builder()
            .addConverterFactory(MsgPackConvertorFactory())
	    ...
	    .build()
```
or 
```
  val msgpack = Msgpack()
  val textJson = ANY_JSON
  val msgData = msgpack.convertFromJson(textJson) // MessagePack Data
  val jsonData = msgpack.convertToJson(msgData)
```
