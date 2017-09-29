# Selector

A region selector like WeChat

the db of city is from 高德

# Download

```gradle
implementation 'win.himike:selector:1.1.4'
```

# Usage

just extends BaseRegionActivity

```java
public class RegionActivity extends BaseRegionActivity {

    @Override
    public void onSelect(City city) {
        setResult(RESULT_OK, new Intent().putExtra(SELECTED, city));
        finish();
    }
}
```

or

let your activity which contains **SelectorFragment** implements [CallBack](https://github.com/Hi-Mike/Selector/blob/db/selector/src/main/java/win/himike/selector/CallBack.java) 

# ProGuard

no need

### License

```
Copyright 2017 Mike

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.