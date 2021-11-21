package com.example.zx.customview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zx.customview.R;
import com.example.zx.customview.bean.PieChartBean;
import com.example.zx.customview.view.View5ChartTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhouxin on 2018/11/25.
 * 图表小练习
 */

public class TestChartActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testchart);

        int[] colors = new int[]{Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY};
        List<PieChartBean> datas = new ArrayList<>();
        int[] nums = new int[6];
        float total = 0f;
        for(int i=0; i<6; i++)
        {
            Random random = new Random();
            Log.i("随机数", "random.nextInt(100)=" + random.nextInt(100));
            int num = random.nextInt(80) % (80 - 10 + 1) + 20;
            nums[i] = num;
            total += num;
        }
        for(int i=0; i<6; i++)
        {
            float num = nums[i] / total * 100;
            PieChartBean bean = new PieChartBean();
            bean.setValue(num);
            bean.setColor(colors[i]);
            datas.add(bean);
        }

        View5ChartTest chartView = new View5ChartTest(this);
        chartView.setDatas(datas);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(chartView, params);
        chartView.postInvalidate();
    }
}
