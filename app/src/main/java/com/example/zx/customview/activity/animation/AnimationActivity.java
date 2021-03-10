package com.example.zx.customview.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zx.customview.R;

import static android.view.animation.Animation.INFINITE;

/**
 * Created by zhouxin on 2018/11/12.
 *
 * https://www.jianshu.com/p/1007f300f17a Android Transition（Android过渡动画）
 *
 *
 *
 *
 * 一.ViewPropertyAnimator:
 *      view.animate().translate/scale/rotate/alpha
 *
 *
 * 二.帧动画：AnimationDrawable
 *
 *
 * 三.View动画：Translate/Scale/Rotate/Alpha Animation; LayoutAnimation
 *
 *
 * 四.属性动画：Animator
 *
 *      1.ValueAnimator
 *
 *      2.ObjectAnimator
 *
 *      3.PropertyValusHolder
 *          对多个属性同时做动画
 *          PropertyValuesHolder holder = PropertyValuesHolder.ofFloat(propertyName, values...);
 *          ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, holder...)
 *
 *      4.Keyframe
 *          把一个属性拆分成多段，执行更加精细的属性动画
 *          Kerframe keyframe = Keyframe.ofFloat(fraction, value)
 *          PrepertyValuesHolder holder = PrepertyValuesHolder.ofKeyframe(propertyName, keyframes...)
 *
 *      5.AnimatorSet
 *          来同时管理调配多个动画
 *          play(animator)/playTogether(animator)/before(animator)/after(animator)/after(time)/with(animator)
 *
 */

public class AnimationActivity extends Activity implements View.OnClickListener {
    private ImageView imageView;

    /**
     * 帧动画，就是顺序播放一组预先定义好的图片
     *
     * 1、在res/drawable目录下定义一个XML文件，根节点为系统提供的animation-list，然后放入定义好的图片；
     * 2、使用AnimationDrawable类播放第一步定义好的Drawable中的图片，形成动画效果；
     */
    private AnimationDrawable animDrawable;


    /**
     * View动画（补间Tween动画）  Animation https://blog.csdn.net/carson_ho/article/details/72909894
     *
     * 只需要拿到一个view，设定它开始和结束的位置，中间的view会自动由系统补齐
     * 主要支持四种效果：平移、缩放、旋转、透明度变化（渐变） 四种基本效果
     * TranslateAnimation 移动View;
     * ScaleAnimation 放大或缩小View;
     * RotateAnimation 旋转View;
     * AlphaAnimation 改变View的透明的
     *
     * LayoutAnimation 作用于ViewGroup子View都会有已设置的动画，如listview的每一个item;
     * 为ViewGroup指定android:layoutAnimation属性，引用LayoutAnimation的xml文件
     *
     * Activity的切换效果 overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
     *
     * Fragment的切换效果 可以使用FragmentTransaction的setCustomAnimation方法添加切换动画。
     */
    private AnimationSet animationSet;
    private Animation transAnimation;
    private Animation scaleAnimation;
    private Animation rotateAnimation;
    private Animation alphaAnimation;
    private int index;


    /**
     * 属性动画 可以看作是增强版的补间动画  Animator--ValueAnimator/AnimatorSet--ObjectAnimator/TimeAnimator
     *
     * 可以从 XML 资源文件加载该动画也可以直接调用 ValueAnimator 或者 ObjectAnimator 的静态工厂方法创建动画。
     *
     * 补间动画只能定义两个关键帧在透明、旋转、位移和倾斜这四个属性的变换，但是属性动画可以定义任何属性的变化。
     * 补间动画只能对 UI 组件执行动画，但属性动画可以对任何对象执行动画。
     *
     * 在一定时间间隔内，通过不断对值进行改变，并不断将该值赋给对象的属性，从而实现该对象在该属性上的动画效果
     *
     * 插值器（Interpolator） 估值器（TypeEvaluator）
     */
    private ValueAnimator valueAnimator;
    private int duration = 1000;

    private double current;
    private double pre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = findViewById(R.id.imageview);
    }

    @Override
    public void onClick(View v) {
        stopAllAniamtion();
        switch (v.getId()){
            case R.id.button1:
                frameAnimation();
                break;

            case R.id.button2:
                viewAnimation((Button) v);
                break;

            case R.id.button3:
                valueAnimation();
                break;
        }
    }

    private void frameAnimation() {
        imageView.setBackgroundResource(R.drawable.frame_animation);
        animDrawable = (AnimationDrawable) imageView.getBackground();
        animDrawable.start();
    }

    private void viewAnimation(Button button) {
        if(index > 4) {
            index = 0;
        }
        switch (index) {
            case 0:
                button.setText("View（补间）动画开始_scale");
                //transAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.view_animation_translate);
                transAnimation = new TranslateAnimation(0,200,0,600);
                transAnimation.setDuration(1000);
                transAnimation.setInterpolator(new LinearInterpolator());
                imageView.startAnimation(transAnimation);
                break;

            case 1:
                button.setText("View（补间）动画开始_rotate");
                //scaleAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.view_animation_scale);
                scaleAnimation = new ScaleAnimation(0,1,0,1);
                scaleAnimation.setDuration(1000);
                imageView.startAnimation(scaleAnimation);
                break;

            case 2:
                button.setText("View（补间）动画开始_alpha");
                //rotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.view_animation_rotate);
                rotateAnimation = new RotateAnimation(0,180,50,50);
                rotateAnimation.setDuration(1000);
                imageView.startAnimation(rotateAnimation);
                break;

            case 3:
                button.setText("View（补间）动画开始_set");
                //alphaAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.view_animation_alpha);
                alphaAnimation = new AlphaAnimation(0,1);
                alphaAnimation.setDuration(1000);
                imageView.startAnimation(alphaAnimation);
                break;

            case 4:
                button.setText("View（补间）动画开始_trans");
                animationSet = new AnimationSet(true);
                animationSet.setDuration(1000);

                animationSet.addAnimation(transAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(rotateAnimation);
                animationSet.addAnimation(alphaAnimation);

                //个别动画如果设置了无限循环，set集合设置重复模式无用
                animationSet.setRepeatMode(INFINITE);

                imageView.startAnimation(animationSet);
                animationSet.start();
                break;
        }
        index++;
    }

    private void valueAnimation() {
        if(valueAnimator != null) {
            valueAnimator.cancel();
        }
        //Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set_animator);
        valueAnimator = ValueAnimator.ofInt(0, 1000);//ofFloat(); ofObject()
        valueAnimator.setDuration(duration);//动画运行的时长
        valueAnimator.setStartDelay(10);//动画延迟播放时间
        valueAnimator.setRepeatCount(0);//动画重复播放次数 = 重放次数+1; =infinite时,动画无限重复
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);//RESTART(默认):正序重放; REVERSE:倒序回放
        valueAnimator.setInterpolator(new DecelerateInterpolator());//插值器，默认线性

        //将改变的值手动赋值给对象的属性值：通过动画的更新监听器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("动画-----", animation.getAnimatedValue()+"");

                current = System.currentTimeMillis();
                Log.i("动画--间隔---", ((current-pre)/1000)+"s");
                pre = current;
            }
        });
        valueAnimator.start();
    }

    private void stopAllAniamtion() {
        if(animDrawable != null) {
            animDrawable.stop();
        }
        if(valueAnimator != null) {
            valueAnimator.end();//end() 方法会直接跳到动画的最后一帧并停止
            //valueAnimator.cancel();//cancel() 方法直接停止在当前帧
        }
        if(transAnimation != null) {
            transAnimation.cancel();
        }
        if(scaleAnimation != null) {
            scaleAnimation.cancel();
        }
        if(rotateAnimation != null) {
            rotateAnimation.cancel();
        }
        if(alphaAnimation != null) {
            alphaAnimation.cancel();
        }
        if(animationSet != null) {
            animationSet.cancel();
        }
    }
}
