package com.example.zx.customview.activity.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * // 使用解析
 *         View.animate().xxx().xxx();
 *         // ViewPropertyAnimator的功能建立在animate()上
 *         // 调用animate()方法返回值是一个ViewPropertyAnimator对象,之后的调用的所有方法都是通过该实例完成
 *         // 调用该实例的各种方法来实现动画效果
 *         // ViewPropertyAnimator所有接口方法都使用连缀语法来设计，每个方法的返回值都是它自身的实例
 *         // 因此调用完一个方法后可直接连缀调用另一方法,即可通过一行代码就完成所有动画效果
 *
 * // 以下是例子
 *         mButton = (Button) findViewById(R.id.Button);
 *         // 创建动画作用对象：此处以Button为例
 *
 *         mButton.animate().alpha(0f);
 *         // 单个动画设置:将按钮变成透明状态
 *         mButton.animate().alpha(0f).setDuration(5000).setInterpolator(new BounceInterpolator());
 *         // 单个动画效果设置 & 参数设置
 *         mButton.animate().alpha(0f).x(500).y(500);
 *         // 组合动画:将按钮变成透明状态再移动到(500,500)处
 *
 *         // 特别注意:
 *         // 动画自动启动,无需调用start()方法.因为新的接口中使用了隐式启动动画的功能，只要我们将动画定义完成后，动画就会自动启动
 *         // 该机制对于组合动画也同样有效，只要不断地连缀新的方法，那么动画就不会立刻执行，等到所有在ViewPropertyAnimator上设置的方法都执行完毕后，动画就会自动启动
 *         // 如果不想使用这一默认机制，也可以显式地调用start()方法来启动动画
 *
 * 原文链接：https://blog.csdn.net/carson_ho/article/details/72909894
 */

public class ViewPropertyAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
