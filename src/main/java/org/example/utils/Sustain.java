package org.example.utils;

import java.time.Duration;

/**
 * 保持逻辑的运行
 *
 * @author tangjialin on 2021-01-27.
 */
public class Sustain {
    /**
     * 保持指定的逻辑运行指定次数后结束
     *
     * @param count    需要运行的次数
     * @param runnable 需要运行的逻辑
     */
    public static void run(long count, Runnable runnable) {
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
    }

    /**
     * 保持指定的逻辑运行指定时长后结束
     *
     * @param duration 需要运行的时长
     * @param runnable 需要运行的逻辑
     */
    public static void run(Duration duration, Runnable runnable) {
        long timeMillis = System.currentTimeMillis() + duration.toMillis();
        while (System.currentTimeMillis() < timeMillis) {
            runnable.run();
        }
    }
}
