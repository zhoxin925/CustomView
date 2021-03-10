package com.example.zx.customview.animation;

import android.animation.TypeEvaluator;
import android.graphics.Color;

/**
 * 针对特殊的属性来做属性动画
 *
 * TypeEvaluator 最经典的用法是使用 ArgbEvaluator 来做颜色渐变的动画
 *
 * 1、HSV(Hue, Saturation, Value)是根据颜色的直观特性由A. R. Smith在1978年创建的一种颜色空间, 也称六角锥体模型(Hexcone Model)。这个模型中颜色的参数分别是：色调（H），饱和度（S），明度（V）。
 *
 * 色调H
 * 用角度度量，取值范围为0°～360°，从红色开始按逆时针方向计算，红色为0°，绿色为120°,蓝色为240°。它们的补色是：黄色为60°，青色为180°,品红为300°；
 *
 * 饱和度S
 * 饱和度S表示颜色接近光谱色的程度。一种颜色，可以看成是某种光谱色与白色混合的结果。其中光谱色所占的比例愈大，颜色接近光谱色的程度就愈高，颜色的饱和度也就愈高。饱和度高，颜色则深而艳。
 * 光谱色的白光成分为0，饱和度达到最高。通常取值范围为0%～100%，值越大，颜色越饱和。
 *
 * 明度V
 * 明度表示颜色明亮的程度，对于光源色，明度值与发光体的光亮度有关；对于物体色，此值和物体的透射比或反射比有关。通常取值范围为0%（黑）到100%（白）。
 *
 * 2、 //右移动24位， ARGB 一共32位，每8位代表一个属性，依次代表透明度(alpha)、红色(red)、绿色(green)、蓝色(blue)。
 * int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);
 *
 * 3、 ofObject() 里，可以提及，上面的
 * ObjectAnimator animator = ObjectAnimator.ofInt(view, "color", 0xffff0000, 0xff00ff00);
 * animator.setEvaluator(new HsvEvaluator()); // 使用自定义的 HsvEvaluator
 * 能被替换成
 * ObjectAnimator animator = ObjectAnimator.ofObject(view, "color",
 * new HsvEvaluator(), 0xffff0000, 0xff00ff00);
 * 也能成功使用。
 *
 * 4、PropertyValuesHolders.ofKeyframe()讲的不是很细致，下面文章讲的很不错，可以借鉴引用一下。
 * http://blog.csdn.net/xuefu_78/article/details/52415673
 *
 */
public class HsvEvaluator implements TypeEvaluator<Integer> {
    float[] startHsv = new float[3];
    float[] endHsv = new float[3];
    float[] outHsv = new float[3];

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        // 把 ARGB 转换成 HSV
        Color.colorToHSV(startValue, startHsv);
        Color.colorToHSV(endValue, endHsv);

        // 计算当前动画完成度（fraction）所对应的颜色值
        if (endHsv[0] - startHsv[0] > 180) {
            endHsv[0] -= 360;
        } else if (endHsv[0] - startHsv[0] < -180) {
            endHsv[0] += 360;
        }
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
        if (outHsv[0] > 360) {
            outHsv[0] -= 360;
        } else if (outHsv[0] < 0) {
            outHsv[0] += 360;
        }
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

        // 计算当前动画完成度（fraction）所对应的透明度
        int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);

        // 把 HSV 转换回 ARGB 返回
        return Color.HSVToColor(alpha, outHsv);
    }
}