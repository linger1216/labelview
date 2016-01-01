# LabelView

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-LabelView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/1538)

<br />

**2016/1/1 UPDATED**

The new LabelView is coming.

1. Rewrite all logic.
2. Provide the LabelImageView, LabelButtonView, LabelTextView to implement the origin LabelView function.
3. You can easily realize yours view, is not limited to the above.

Sorry, this belated update.

<br />

Sometimes, we need to show a **label** above an ImageView or any other views. Well, **LabelXXView** will be able to help you. It's easy to implement as well!

![](./img/img1.png)

![](./img/img3.png)



# Import your project

#### Gradle

**It will provide gradle way soon**

#### Or

Copy all `java files` and `attr.xml` into your project.



# Create a Label



put xml code in you layout, like follows.



## LabelButtonView



``` java
<com.lid.lib.LabelButtonView
    android:id="@+id/labelbutton"
    android:layout_width="200dp"
    android:layout_height="48dp"
    android:background="#03a9f4"
    android:gravity="center"
    android:text="Button"
    android:textColor="#ffffff"
    app:label_backgroundColor="#C2185B"
    app:label_distance="20dp"
    app:label_height="20dp"
    app:label_orientation="RIGHT_TOP"
    app:label_text="HD"
    app:label_textSize="12sp" />
```



## LabelImageView



``` 
<com.lid.lib.LabelImageView
    android:id="@+id/image1"
    android:layout_width="0dp"
    android:layout_height="match_parent"
    android:layout_weight="1"
    android:scaleType="centerCrop"
    android:src="@mipmap/image1"
    app:label_backgroundColor="#C2185B"
    app:label_orientation="LEFT_TOP"
    app:label_text="CHINA" />
```





## LabelTextView



``` 
<com.lid.lib.LabelTextView
    android:id="@+id/text"
    android:layout_width="wrap_content"
    android:layout_height="48dp"
    android:layout_gravity="center"
    android:layout_marginTop="8dp"
    android:background="#212121"
    android:gravity="center"
    android:padding="16dp"
    android:text="TextView"
    android:textColor="#ffffff"
    app:label_backgroundColor="#03A9F4"
    app:label_distance="15dp"
    app:label_orientation="LEFT_TOP"
    app:label_text="POP"
    app:label_textSize="10sp" />
```





# Parameter Description

![](./img/img2.png)



# If you need Label in your custom View



1. create an new view class extends `YourView`
2. use LabelViewHelper as your `Member objects`
3. In Constructor function and onDraw function call LabelViewHelper method.
4. Call the LabelViewHelper method in other functions

like as follows:

``` java
public class LabelXXXView extends YourView {
    LabelViewHelper utils;
    public LabelXXXView(Context context) {
        this(context, null);
    }
    public LabelXXXView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LabelXXXView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        utils = new LabelViewHelper(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        utils.onDraw(canvas, getMeasuredWidth(), getMeasuredHeight());
    }
    public void setLabelHeight(int height) {
        utils.setLabelHeight(this, height);
    }
    public int getLabelHeight() {
        return utils.getLabelHeight();
    }
    public void setLabelDistance(int distance) {
        utils.setLabelDistance(this, distance);
    }
    public int getLabelDistance() {
        return utils.getLabelDistance();
    }
    public boolean isLabelVisual() {
        return utils.isLabelVisual();
    }
    public void setLabelVisual(boolean enable) {
        utils.setLabelVisual(this, enable);
    }
    public int getLabelOrientation() {
        return utils.getLabelOrientation();
    }
    public void setLabelOrientation(int orientation) {
      	utils.setLabelOrientation(this, orientation);
    }
    public int getLabelTextColor() {
        return utils.getLabelTextColor();
    }
    public void setLabelTextColor(int textColor) {
        utils.setLabelTextColor(this, textColor);
    }
    public int getLabelBackgroundColor() {
        return utils.getLabelBackgroundColor();
    }
    public void setLabelBackgroundColor(int backgroundColor) {
      	utils.setLabelBackgroundColor(this, backgroundColor);
    }
    public String getLabelText() {
        return utils.getLabelText();
    }
    public void setLabelText(String text) {
        utils.setLabelText(this, text);
    }
    public int getLabelTextSize() {
        return utils.getLabelTextSize();
    }
    public void setLabelTextSize(int textSize) {
        utils.setLabelTextSize(this, textSize);
    }
}
```









# Thanks

+ [shaunidiot](https://github.com/shaunidiot) English ReadMe supported

## License

``` 
Copyright 2014 linger1216

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
