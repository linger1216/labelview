# labelview
Sometimes we need to show a label view above a ImageView or others, labelview will help you, let code easy.

![](./img/img1.png)

# import your project

you can copy LabelView.java file into you project.

# create label

LabelView extended TextView so you can use TextView all method.

```
{
    LabelView label = new LabelView(this);
    label.setText("POP");
    label.setBackgroundColor(0xff03a9f4);
    label.setPadding(40,1,40,1);
    label.setTargetView(findViewById(R.id.text), 10, LabelView.Gravity.LEFT_TOP);
}
```
# setTargetView parameters

    setTargetView(findViewById(R.id.text), 10, LabelView.Gravity.LEFT_TOP)

the second parameter means distance.

![](./img/img2.png)

the third parameter means gravity (only LEFT_TOP and RIGHT_TOP)


