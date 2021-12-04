package com.example.zx.customview;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);

        // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
        Executors.newFixedThreadPool(1);

        // 建一个可定期或者延时执行任务的定长线程池，支持定时及周期性任务执行。
        Executors.newScheduledThreadPool(1);

        // newSingleThreadExecutor 一次只能执行一个任务， 任务排队执行
        Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
    }
}