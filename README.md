# reboundx

Android wrapper around facebook rebound library https://github.com/facebook/rebound

<img src="/present.gif" alt="sample" title="sample" />

### Installation

Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    ...	
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```
dependencies {
  implementation 'com.github.palatin:reboundx:0.1'
}
```

Currently library supports:

+ translate
+ scale
+ alpha
+ rotate

### Simple Usage

Library can be usage through view's extension:
```
val animator = view.rebound().scale()
animator.start()
```

### Animation properties
class ```ReboundController``` is responsible for setting animation parametrs. ```ReboundController``` can be instantiate by ```view.rebound()``` or ```view.rebound(tension: Double, friction : Double)```

```ReboundController``` contains next options:

| Option name        |    Description   |
| ------------- | ------------- |
| startValue         | Initial value of animation    |
| endValue           | Final value of the animation  |
| listener           | ```ReboundListener``` object that notify of the begining and the end of animation  |
| restSpeedThreshold | Value that determines the minimum speed at which the animation is considered complete  |
| restDisplacementThreshold | Value that determines the minimum difference between the final value of the animation and the current one when the animation is considered complete.  |

### LICENSE

```
MIT License

Copyright (c) 2019 Ihor Shamin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
