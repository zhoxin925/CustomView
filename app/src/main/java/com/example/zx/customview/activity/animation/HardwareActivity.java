package com.example.zx.customview.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 记录一些硬件加速相关的信息
 *
 * Android里，专指把View中绘制计算工作交给GPU处理在
 *
 * 如果你的绘制操作不支持硬件加速，你需要手动关闭硬件加速来绘制界面，关闭的方式是通过这行代码：
 * view.setLayerType(LAYER_TYPE_SOFTWARE, null);
 *
 * 优点：
 *      1.用了GPU，绘制变快了
 *      2.绘制机制的改变，导致界面内容改变时的刷新效率极大提高
 * 限制：
 *      有些方法失效或不正常，如canvas.clipPath(在api > 18时才有效)
 *
 * View.setLayerType(type)
 *      这个方法的本来作用并不是用来开关硬件加速的，只是当它的参数为 LAYER_TYPE_SOFTWARE 的时候，可以「顺便」把硬件加速关掉而已；
 *      并且除了这个方法之外，Android 并没有提供专门的 View 级别的硬件加速开关，所以它就「顺便」成了一个开关硬件加速的方法。
 *
 *      View.LAYER_TYPE_SOFTWARE：使用软件来绘制 View Layer，绘制到一个 Bitmap，并顺便关闭硬件加速；
 *      View.LAYER_TYPE_HARDWARE：使用 GPU 来绘制 View Layer，绘制到一个 OpenGL texture（如果硬件加速关闭，那么行为和 VIEW_TYPE_SOFTWARE 一致）；
 *      View.LAYER_TYPE_NONE：关闭 View Layer。
 *
 * View Layer 可以加速无 invalidate() 时的刷新效率，但对于需要调用 invalidate() 的刷新无法加速。
 * View Layer 绘制所消耗的实际时间是比不使用 View Layer 时要高的，所以要慎重使用。
 *
 */
public class HardwareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = new View(this);

        //不过一定要注意，只有你在对 translationX translationY rotation alpha 等无需调用 invalidate() 的属性做动画的时候
        // ，这种方法才适用，因为这种方法本身利用的就是当界面不发生时，缓存未更新所带来的时间的节省。所以简单地说——
        //
        //**这种方式不适用于基于自定义属性绘制的动画。**一定记得这句话。

        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 180);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        animator.start();


        //使用 ViewPropertyAnimator
        view.animate()
                .rotationY(90)
                .withLayer(); // withLayer() 可以自动完成上面这段代码的复杂操作





        //另外，除了用于关闭硬件加速和辅助属性动画这两项功能外，Layer 还可以用于给 View 增加一些绘制效果
        // ，例如设置一个 ColorMatrixColorFilter 来让 View 变成黑白的：
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        //另外，由于设置了 View Layer 后，View 在初次绘制时以及每次 invalidate() 后重绘时，需要进行两次的绘制工作
        // （一次绘制到 Layer，一次从 Layer 绘制到显示屏），所以其实它的每次绘制的效率是被降低了的。所以一定要慎重使用 View Layer，在需要用到它的时候再去使用。
    }
}
